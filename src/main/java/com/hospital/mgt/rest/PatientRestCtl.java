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

import com.hospital.mgt.dto.PatientDTO;
import com.hospital.mgt.exception.DuplicateRecordException;
import com.hospital.mgt.form.PatientForm;
import com.hospital.mgt.restException.DuplicateRecordRestException;
import com.hospital.mgt.restException.RecordNotFoundRestException;
import com.hospital.mgt.service.PatientServiceInt;

@RestController
@RequestMapping("api")
public class PatientRestCtl {

	@Autowired
	private PatientServiceInt service;

	@GetMapping("/patientList")
	public List<PatientDTO> display() {
		List<PatientDTO> list = service.search(null);
		if (list == null && list.size() == 0) {
			throw new RecordNotFoundRestException("Record not found");
		}
		return list;
	}

	@GetMapping("/patient/{id}")
	public PatientDTO displayById(@PathVariable int id) {
		PatientDTO dto = service.findByPK(id);
		if (dto == null) {
			throw new RecordNotFoundRestException("Patient id not found - " + id);
		}
		return dto;
	}

	@PostMapping("/patient")
	public PatientDTO add(@RequestBody PatientForm form) {
		PatientDTO dto=null;
		try {
			 dto=(PatientDTO)form.getDto();
			dto.setId(0);
			service.add(dto);
		} catch (DuplicateRecordException e) {
			throw new DuplicateRecordRestException(e.getMessage());
		}
		return dto;
	}
	
	@PutMapping("/patient")
	public PatientDTO update(@RequestBody PatientForm form) {
		PatientDTO dto=null;
		try {
			dto=(PatientDTO)form.getDto();
			service.update(dto);
		} catch (DuplicateRecordException e) {
			throw new DuplicateRecordRestException(e.getMessage());
		}
		return dto;
	}
	
	@DeleteMapping("/patient/{id}")
	public String deleteById(@PathVariable int id) {
		PatientDTO dto = service.findByPK(id);
		if (dto == null) {
			throw new RecordNotFoundRestException("Patient id not found - " + id);
		}
		service.delete(dto);
		return "Deleted Patient id is - "+id;
	}

}
