package com.hospital.mgt.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Appointment")
public class AppointmentDTO extends BaseDTO {

	@Column(name="First_Name", length=225)
	private String firstName;
	@Column(name = "LAST_NAME", length = 225)
	private String lastName;
	
	@Column(name="DoctorId")
	private long doctorId;
	
	@Column(name = "DOCTOR_NAME", length = 225)
	private String doctorName;
	
	@Column(name = "PHONE_NO", length = 225)
	private String phoneNo;
	
	@Column(name = "UserId")
	private long userId;
	
	@Column(name = "Status", length = 225)
	private String status;
	
	@Column(name = "Day", length = 225)
	private String day;
	@Column(name = "Time", length = 225)
	private String time;
	
	@Column(name = "Date")
	private Date date;
	
	
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public long getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getDoctorName() {
		return doctorName;
	}


	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	

	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	

	


	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	
	@Override
	public String getValue() {
		return firstName+" "+lastName;
	}

}
