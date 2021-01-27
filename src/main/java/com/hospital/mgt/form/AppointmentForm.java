package com.hospital.mgt.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.hospital.mgt.dto.AppointmentDTO;
import com.hospital.mgt.dto.BaseDTO;
import com.hospital.mgt.util.DataUtility;

public class AppointmentForm extends BaseForm {

	
	
	@NotEmpty(message = "Last Name is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$",message = "First Name is invalid")
	private String firstName;
	
	@NotEmpty(message = "Last Name is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$",message = "Last Name is invalid")
	private String lastName;
	
	@NotEmpty
	@Pattern(regexp="(^[7-9][0-9]{9})*$",message = "Mobile No is Invalid")
	private String phoneNo;
	
	private String docName;
	
	private String status;
	@Min(value = 1,message = "Doctor Name is Required")
	private long doctorId;
	
	@NotEmpty(message = "Days is required")
	private String day;
	@NotEmpty(message = "Time is required")
	private String time;
	
	@NotEmpty(message = "Date is required")
	@Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$",message = "Date is Invalid")
	private String date;
	
	private long userId;
	
	
	
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public BaseDTO getDto() {
		AppointmentDTO dto=new AppointmentDTO();
		dto.setId(id);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		dto.setStatus(status);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setPhoneNo(phoneNo);
		dto.setDoctorName(docName);
		dto.setDoctorId(doctorId);
		dto.setDay(day);
		dto.setTime(time);
		dto.setDate(DataUtility.getDate(date));
		dto.setUserId(userId);
		return dto;
		
	}

	@Override
	public void populate(BaseDTO bDto) {
		AppointmentDTO dto = (AppointmentDTO) bDto;
		
		id = dto.getId();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDateTime = dto.getCreatedDatetime();
		modifiedDateTime = dto.getModifiedDatetime();
		firstName = dto.getFirstName();
		lastName =dto.getLastName();
		status=dto.getStatus();
		phoneNo =dto.getPhoneNo();
		docName =dto.getDoctorName();
		doctorId =dto.getDoctorId();
		day=dto.getDay();
		time=dto.getTime();
		date=DataUtility.getStringDate(dto.getDate());
		userId=dto.getUserId();
	}

}
