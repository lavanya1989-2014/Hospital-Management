package com.hospital.mgt.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.hospital.mgt.dto.BaseDTO;
import com.hospital.mgt.dto.PatientDTO;
import com.hospital.mgt.util.DataUtility;

public class PatientForm extends BaseForm {
	
	@NotEmpty
	@Pattern(regexp = "(^[A-Za-z ]*)*$")
	private String firstName;
	
	@NotEmpty
	@Pattern(regexp = "(^[A-Za-z ]*)*$")
	private String lastName;

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
	
	@NotEmpty(message = "Allergy is required")
	private String allergy;
	
	@NotEmpty(message = "Age is required")
	private String age;
	
	@NotEmpty(message = "City is required")
	private String city;
	
	
	@NotEmpty(message = "Address is required")
	private String address;
	
	private long userId;
	
	
	
	
	
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
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

	

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	@Override
	public BaseDTO getDto() {
		PatientDTO dto=new PatientDTO();
		dto.setId(id);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setUserId(userId);
		dto.setEmailId(email);
		dto.setGender(gender);
		dto.setPhoneNo(phoneNo);
		dto.setAllergy(allergy);
		dto.setCity(city);
		dto.setAddress(address);
		dto.setAge(age);
		dto.setDob(DataUtility.getDate(dob));
		
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		PatientDTO dto = (PatientDTO) bDto;
		
		id = dto.getId();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDateTime = dto.getCreatedDatetime();
		modifiedDateTime = dto.getModifiedDatetime();
		firstName = dto.getFirstName();
		lastName =dto.getLastName();
		email=dto.getEmailId();
		gender=dto.getGender();
		phoneNo =dto.getPhoneNo();
		allergy=dto.getAllergy();
		city =dto.getCity();
		address =dto.getAddress();
		age=dto.getAge();
		dob=DataUtility.getStringDate(dto.getDob());
		userId=dto.getUserId();
		
	}

}
