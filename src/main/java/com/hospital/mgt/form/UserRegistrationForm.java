package com.hospital.mgt.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.hospital.mgt.dto.BaseDTO;
import com.hospital.mgt.dto.UserDTO;
import com.hospital.mgt.util.DataUtility;



public class UserRegistrationForm extends BaseForm {

	@NotEmpty
	@Pattern(regexp = "(^[A-Za-z ]*)*$")
	private String firstName;
	/**
	 * Last Name of User
	 */
	@NotEmpty
	@Pattern(regexp = "(^[A-Za-z ]*)*$")
	private String lastName;

	@NotEmpty
	private String userName;

	@NotEmpty
	private String gender;
	/**
	 * Password of User
	 */
	@NotEmpty
	@Pattern(regexp="(^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[A-Za-z0-9\\S]{6,12})*$",message="Pattern.form.password")
	private String password;

	@NotEmpty
	private String confirmPassword;

	/**
	 * Date of Birth of User
	 */
	@NotEmpty
	@Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$",message = "Date of Birth is Invalid")
	private String dob;

	/**
	 * MobielNo of User
	 */
	@NotEmpty
	@Pattern(regexp="(^[7-9][0-9]{9})*$",message = "Mobile No is Invalid")
	private String phoneNo;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public BaseDTO getDto() {
		UserDTO dto = new UserDTO();
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setUserName(userName);
		dto.setPassword(password);
		dto.setGender(gender);
		dto.setDob(DataUtility.getDate(dob));
		dto.setPhoneNo(phoneNo);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {

	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
