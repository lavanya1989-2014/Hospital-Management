package com.hospital.mgt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.mgt.dto.UserDTO;
import com.hospital.mgt.exception.DuplicateRecordException;
import com.hospital.mgt.form.DoctorForm;
import com.hospital.mgt.form.UserForm;
import com.hospital.mgt.restException.DuplicateRecordRestException;
import com.hospital.mgt.restException.RecordNotFoundRestException;
import com.hospital.mgt.service.UserServiceInt;

@RestController
@RequestMapping("api")
public class UserRestCtl {

	@Autowired
	private UserServiceInt service;

	@GetMapping("/userList")
	public List<UserDTO> display() {
		List<UserDTO> list = service.search(null);
		if (list == null && list.size() == 0) {
			throw new RecordNotFoundRestException("Record not found");
		}
		return list;
	}

	@GetMapping("/user/{id}")
	public UserDTO displayById(@PathVariable int id) {
		UserDTO dto = service.findByPK(id);
		if (dto == null) {
			throw new RecordNotFoundRestException("User id not found - " + id);
		}
		return dto;
	}

	@PostMapping("/user")
	public UserDTO add(@RequestBody UserForm form) {
		UserDTO dto=null;
		try {
			 dto=(UserDTO)form.getDto();
			dto.setId(0);
			service.add(dto);
		} catch (DuplicateRecordException e) {
			throw new DuplicateRecordRestException(e.getMessage());
		}
		return dto;
	}
	
	@PutMapping("/user")
	public UserDTO update(@RequestBody UserForm form) {
		UserDTO dto=null;
		try {
			dto=(UserDTO)form.getDto();
			service.update(dto);
		} catch (DuplicateRecordException e) {
			throw new DuplicateRecordRestException(e.getMessage());
		}
		return dto;
	}
	
	@DeleteMapping("/user/{id}")
	public String deleteById(@PathVariable int id) {
		UserDTO dto = service.findByPK(id);
		if (dto == null) {
			throw new RecordNotFoundRestException("User id not found - " + id);
		}
		service.delete(dto);
		return "Deleted User id is - "+id;
	}

}
