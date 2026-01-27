package com.pastexplorehub.dto;



public class TeacherDTO {

	private Long teacherId;
	private String name;
	
	public TeacherDTO(){}
	
	public TeacherDTO(Long teacherId,String name)
	{
		this.teacherId = teacherId;
		this.name = name;
	}
	
	
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}