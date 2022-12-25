package com.springbootbackend.springbootbackend.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.springbootbackend.springbootbackend.StudentDetails.StudentDetails;
import com.springbootbackend.springbootbackend.dao.StudentDao;
import com.springbootbackend.springbootbackend.email_service.EmailSenderService;
import com.springbootbackend.springbootbackend.exceptions.EmailNotValid;

import jakarta.mail.MessagingException;
import java.util.regex.*;  
@Service
public class StudentServiceimpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private EmailSenderService service;

	@Autowired
	private KafkaTemplate<String, StudentDetails> kafkaTemplate;

	@Value("${kafka.topic}")
	private String topic;
	
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
	@KafkaListener(topics = "${kafka.topic}")
	public StudentDetails addStudent(StudentDetails student){
		// TODO Auto-generated method stub
		String studentMail=new String(student.getStudentEmail());
		studentDao.save(student);
//		triggerMail(studentMail);
//		kafkaTemplate.send(topic, String.valueOf(student.getStudentId()), student);
		System.out.println("Entry successfull");
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
//	public void registerStudent(StudentDetails student) {
//		kafkaTemplate.send(topic, student.getStudentId(), student);
//	}
	public boolean emailValidation(String studentMail) throws EmailNotValid{   
	        String regex = "^(.+)@(.+)$"; 
	        Pattern pattern = Pattern.compile(regex);  
	        Matcher matcher = pattern.matcher(studentMail);  
	        if(matcher.matches()) {
	        	return true;
	        }
	        return false;
	}  

}





