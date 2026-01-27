package com.pastexplorehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pastexplorehub.repository.DepartmentRepository;
import com.pastexplorehub.entity.Department;
import com.pastexplorehub.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentRepository departmentRepo;
	
	@Override
	public List<String> getAllDepartmentName() {
		
		List<String> departmentNames = departmentRepo.findAllDepartmentNames();
		
		return departmentNames;
	}

			@Override
			public void saveDepartment(Department department) {
			    departmentRepo.save(department);
			}
}
