package com.hospital.mgt.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hospital.mgt.dto.RoleDTO;
import com.hospital.mgt.exception.DuplicateRecordException;



/**Role Service Interface
 * @author Navigable Set
 *@version 1.0
 *@copywrite Navigable Set
 */


public interface RoleServiceInt {


	
	
	
	public long add(RoleDTO dto) throws DuplicateRecordException;
	
	
	public void delete(RoleDTO dto) ;
	
	
	public void update(RoleDTO dto) throws DuplicateRecordException;
	  
	
	public RoleDTO findByPK(long pk) ;
	
	
	
	public RoleDTO findByName(String name);
	
	
	
	public List<RoleDTO> search(RoleDTO dto);
	
	
	
	public List search(RoleDTO dto, int pageNo, int pageSize) ;
	
	
	public Map<Long, RoleDTO> getMapDTO(Set<Long> ids);
	
	
	
}
