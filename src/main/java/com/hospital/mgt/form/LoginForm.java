package com.hospital.mgt.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.hospital.mgt.dto.BaseDTO;
import com.hospital.mgt.dto.UserDTO;



public class LoginForm extends BaseForm {

	@NotEmpty
	private String userName;

	@NotEmpty
	@Pattern(regexp="^((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[A-Za-z0-9\\S]{6,12})*$")
	private String password;

	
	

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

	@Override
	public BaseDTO getDto() {
		UserDTO dto = new UserDTO();
		dto.setUserName(userName);
		dto.setPassword(password);
		
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		
	}

	

}
