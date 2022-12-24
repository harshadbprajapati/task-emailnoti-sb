package com.springbootbackend.springbootbackend.StudentDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentDetails {
	
		// TODO Auto-generated method stub
	    @Id
	    @Column(name="student_id")
		private String studentId;
	    
	    public StudentDetails() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Column(name="student_name")
		private String studentName;
	    
	    @Column(name="student_contact_number")
		private long studentContactNumber;
	    
	    @Column(name="student_email")
		private String studentEmail;

		public String getStudentId() {
			return studentId;
		}

		public void setStudentId(String studentId) {
			this.studentId = studentId;
		}

		public String getStudentName() {
			return studentName;
		}

		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}

		public long getStudentContactNumber() {
			return studentContactNumber;
		}

		public void setStudentContactNumber(long studentContactNumber) {
			this.studentContactNumber = studentContactNumber;
		}

		public String getStudentEmail() {
			return studentEmail;
		}

		public void setStudentEmail(String studentEmail) {
			this.studentEmail = studentEmail;
		}

		public StudentDetails(String studentId, String studentName, long studentContactNumber, String studentEmail) {
			super();
			this.studentId = studentId;
			this.studentName = studentName;
			this.studentContactNumber = studentContactNumber;
			this.studentEmail = studentEmail;
		}
		
		
	
		
		
		
	

}
