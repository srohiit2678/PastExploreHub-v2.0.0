package com.pastexplorehub.service;

import java.util.List;

import com.pastexplorehub.entity.Department;



public interface DepartmentService {
		List<String> getAllDepartmentName();
		// In DepartmentService interface
		void saveDepartment(Department department);

		
}
