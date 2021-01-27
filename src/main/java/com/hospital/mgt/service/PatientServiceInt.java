package com.hospital.mgt.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hospital.mgt.dto.PatientDTO;
import com.hospital.mgt.exception.DuplicateRecordException;

public interface PatientServiceInt {
	
	
public long add(PatientDTO dto) throws DuplicateRecordException;
	
	
	public void delete(PatientDTO dto) ;
	
	
	public void update(PatientDTO dto) throws DuplicateRecordException;
	  
	
	public PatientDTO findByPK(long pk) ;
	
	
	
	public PatientDTO findByName(String name);
	
	public PatientDTO findByUserId(long id);
	
	
	
	public List<PatientDTO> search(PatientDTO dto);
	
	
	
	public List search(PatientDTO dto, int pageNo, int pageSize) ;
	
	
	public Map<Long, PatientDTO> getMapDTO(Set<Long> ids);

}
