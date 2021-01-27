package com.hospital.mgt.form;

import javax.validation.constraints.NotEmpty;

import com.hospital.mgt.dto.BaseDTO;
import com.hospital.mgt.dto.UserDTO;



public class ForgetPasswordForm extends BaseForm {

	@NotEmpty(message = "User Name is Required")
	private String userName;
	
	
	
	
	
	
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public BaseDTO getDto() {
	
		UserDTO dto = new UserDTO();
		dto.setUserName(userName);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		
	}

}
