package com.pastexplorehub.dto;

import lombok.Data;

@Data
public class DepartmentDTO {
    private Long id;
    private String deptName;
    private String deptCode;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
    
    
    
}