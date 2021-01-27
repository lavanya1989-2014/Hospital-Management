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

import com.hospital.mgt.dto.AppointmentDTO;
import com.hospital.mgt.exception.DuplicateRecordException;
import com.hospital.mgt.form.DoctorForm;
import com.hospital.mgt.form.AppointmentForm;
import com.hospital.mgt.restException.DuplicateRecordRestException;
import com.hospital.mgt.restException.RecordNotFoundRestException;
import com.hospital.mgt.service.AppointmentServiceInt;

@RestController
@RequestMapping("api")
public class AppointmentRestCtl {

	@Autowired
	private AppointmentServiceInt service;

	@GetMapping("/appointmentList")
	public List<AppointmentDTO> display() {
		List<AppointmentDTO> list = service.search(null);
		if (list == null && list.size() == 0) {
			throw new RecordNotFoundRestException("Record not found");
		}
		return list;
	}

	@GetMapping("/appointment/{id}")
	public AppointmentDTO displayById(@PathVariable int id) {
		AppointmentDTO dto = service.findByPK(id);
		if (dto == null) {
			throw new RecordNotFoundRestException("Doctor id not found - " + id);
		}
		return dto;
	}

	@PostMapping("/appointment")
	public AppointmentDTO add(@RequestBody DoctorForm form) {
		AppointmentDTO dto=null;
		try {
			 dto=(AppointmentDTO)form.getDto();
			dto.setId(0);
			service.add(dto);
		} catch (DuplicateRecordException e) {
			throw new DuplicateRecordRestException(e.getMessage());
		}
		return dto;
	}
	
	@PutMapping("/appointment")
	public AppointmentDTO update(@RequestBody DoctorForm form) {
		AppointmentDTO dto=null;
		try {
			dto=(AppointmentDTO)form.getDto();
			service.update(dto);
		} catch (DuplicateRecordException e) {
			throw new DuplicateRecordRestException(e.getMessage());
		}
		return dto;
	}
	
	@DeleteMapping("/appointment/{id}")
	public String deleteById(@PathVariable int id) {
		AppointmentDTO dto = service.findByPK(id);
		if (dto == null) {
			throw new RecordNotFoundRestException("Appointment id not found - " + id);
		}
		service.delete(dto);
		return "Deleted Appointment id is - "+id;
	}

}
