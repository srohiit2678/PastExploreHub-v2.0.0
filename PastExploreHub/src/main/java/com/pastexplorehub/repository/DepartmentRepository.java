package com.pastexplorehub.repository;

import com.pastexplorehub.entity.Department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
 
	@Query("SELECT DISTINCT d.deptName FROM Department d")
    List<String> findAllDepartmentNames();
}