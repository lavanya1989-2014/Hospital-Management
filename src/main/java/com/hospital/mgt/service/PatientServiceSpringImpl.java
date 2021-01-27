package com.hospital.mgt.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.mgt.dao.PatientDAOInt;
import com.hospital.mgt.dao.RoleDAOInt;
import com.hospital.mgt.dto.PatientDTO;
import com.hospital.mgt.exception.DuplicateRecordException;

@Service
public class PatientServiceSpringImpl implements PatientServiceInt {
	
	@Autowired
	PatientDAOInt dao;// have to make it private

	private static Logger log = Logger.getLogger(PatientServiceSpringImpl.class);

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long add(PatientDTO dto) throws DuplicateRecordException {

		log.debug("Patient spring add start");

		PatientDTO existdto = dao.findByName(dto.getFirstName());

		if (existdto != null) {
			throw new DuplicateRecordException("Patient is already exits");
		}

		log.debug("Patient spring add end");
		return dao.add(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(PatientDTO dto) {
		// TODO Auto-generated method stub
		dao.delete(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(PatientDTO dto) throws DuplicateRecordException {
		log.debug("Patient spring add start");

		PatientDTO existdto = dao.findByName(dto.getFirstName());
		
		if (existdto != null && existdto.getId() != dto.getId()) {
			throw new DuplicateRecordException("Patient is already exits");
		}

		log.debug("Patient spring add end");

		dao.update(dto);
	}

	@Transactional(readOnly = true)
	public PatientDTO findByPK(long pk) {

		return dao.findByPk(pk);
	}

	@Transactional(readOnly = true)
	public PatientDTO findByName(String name) {
		// TODO Auto-generated method stub
		return dao.findByName(name);
	}
	
	@Transactional(readOnly = true)
	public PatientDTO findByUserId(long id) {
		// TODO Auto-generated method stub
		return dao.findByUserId(id);
	}

	@Transactional(readOnly = true)
	public List<PatientDTO> search(PatientDTO dto) {
		// TODO Auto-generated method stub
		return dao.search(dto);
	}

	@Transactional(readOnly = true)
	public List search(PatientDTO dto, int pageNo, int pageSize) {

		return dao.search(dto, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public Map<Long, PatientDTO> getMapDTO(Set<Long> ids) {
		// TODO Auto-generated method stub
		return dao.getMapDTO(ids);
	}
}
