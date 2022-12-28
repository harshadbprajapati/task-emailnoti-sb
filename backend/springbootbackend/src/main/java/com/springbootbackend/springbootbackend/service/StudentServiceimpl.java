package com.springbootbackend.springbootbackend.service;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.springbootbackend.springbootbackend.StudentDetails.StudentDetails;
import com.springbootbackend.springbootbackend.dao.StudentDao;
import com.springbootbackend.springbootbackend.email_service.EmailSenderService;
import com.springbootbackend.springbootbackend.exceptions.EmailAlreadyRegistered;

import jakarta.mail.MessagingException;
import java.util.regex.*;  
@Service("studentServiceimpl")
public class StudentServiceimpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private EmailSenderService service;

	@Override
	public List<StudentDetails> getAllStudentDetails() {
		// TODO Auto-generated method stub
		
		return studentDao.findAll();
	}

	@Override
	public Optional<StudentDetails> getStudent(int studentId) {
		// TODO Auto-generated method stub
		return studentDao.findById(studentId);
	}

	@Override
	@KafkaListener(topics = "student-registration")
	public StudentDetails addStudent(StudentDetails student) throws EmailAlreadyRegistered {
		String studentMail=student.getStudentEmail();
		try {
				studentDao.save(student);
				triggerMail(studentMail);
				System.out.println("Entry successfull");
			
		}
		catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println("Email Not Sent");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StudentDetails updateStudent(StudentDetails student) {
		// TODO Auto-generated method stub
		
		StudentDetails newStudent=new StudentDetails(student.getStudentId(),student.getStudentName(),student.getStudentContactNumber(), student.getStudentEmail());
		
		return studentDao.save(newStudent);
	}



	@Override
	public void deleteStudent(int parseInt) {
		// TODO Auto-generated method stub
		StudentDetails toDelete=studentDao.getReferenceById(parseInt);
		studentDao.delete(toDelete);
	}
	
	public void triggerMail(String studentMail) throws MessagingException {

//		service.sendEmailWithAttachment(studentMail,
//				"You have registered Successfully!",
//				"Email with Attachment",
//				"./hello-world.gif");
		service.sendSimpleEmail(studentMail, "You have registered Successfully!", "Registration Completed");
	}
	
	@Override
	public boolean emailExists(String studentEmail) {
		return studentDao.findByEmail(studentEmail) != null;
	}

}





