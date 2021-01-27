package com.hospital.mgt.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.hospital.mgt.dto.BaseDTO;
import com.hospital.mgt.dto.UserDTO;
import com.hospital.mgt.util.DataUtility;

public class DoctorForm extends BaseForm {
	
	@NotEmpty
	@Pattern(regexp = "(^[A-Za-z ]*)*$")
	private String firstName;
	
	@NotEmpty
	@Pattern(regexp = "(^[A-Za-z ]*)*$")
	private String lastName;
	
	@NotEmpty
	private String userName;
	
	@NotEmpty
	@Pattern(regexp = "(^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[A-Za-z0-9\\S]{6,12})*$")
	private String password;
	
	@NotEmpty
	@Pattern(regexp = "(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))*$")
	private String email;
	
	@NotEmpty
	private String gender;
	
	@NotEmpty
	@Pattern(regexp="(^[7-9][0-9]{9})*$",message = "Mobile No is Invalid")
	private String phoneNo;
	
	@NotEmpty
	@Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$",message = "Date of Birth is Invalid")
	private String dob;
	

	
	@NotEmpty(message = "Qualification is required")
	private String qualification;
	
	@NotEmpty(message = "City is required")
	private String city;
	
	@NotEmpty(message = "Specialization is required")
	private String specialization;
	
	@NotEmpty(message = "Address is required")
	private String address;
	
	@NotEmpty(message = "Days is required")
	private String days;
	@NotEmpty(message = "Time is required")
	private String time;
	
	
	
	

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public BaseDTO getDto() {
		
		UserDTO dto=new UserDTO();
		dto.setId(id);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setUserName(userName);
		dto.setPassword(password);
		dto.setEmailId(email);
		dto.setGender(gender);
		dto.setPhoneNo(phoneNo);
		dto.setCity(city);
		dto.setAddress(address);
		dto.setDays(days);
		dto.setTime(time);
		dto.setSpcialization(specialization);
		dto.setQualification(qualification);
		dto.setDob(DataUtility.getDate(dob));
		
		
		return dto;
		
		
	}

	@Override
	public void populate(BaseDTO bDto) {
		UserDTO dto = (UserDTO) bDto;
		
		id = dto.getId();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDateTime = dto.getCreatedDatetime();
		modifiedDateTime = dto.getModifiedDatetime();
		firstName = dto.getFirstName();
		lastName =dto.getLastName();
		userName=dto.getUserName();
		password=dto.getPassword();
		email=dto.getEmailId();
		gender=dto.getGender();
		phoneNo =dto.getPhoneNo();
		city =dto.getCity();
		address =dto.getAddress();
		days=dto.getDays();
		time=dto.getTime();
		specialization=dto.getSpcialization();
		qualification=dto.getQualification();
		dob=DataUtility.getStringDate(dto.getDob());
		
	}

}
