package com.pastexplorehub.service;

import java.util.List;

import com.pastexplorehub.dto.AdminActivityDTO;
import com.pastexplorehub.dto.ProjectDTO;
import com.pastexplorehub.entity.Project;
import com.pastexplorehub.model.Status;

public interface ProjectService {

	Project submitProject(ProjectDTO projectDto)throws Exception ;

	public List<ProjectDTO>getAllProjectByStatus(Status status);
	public ProjectDTO getProjectById(Long projectId);
	public Status updateProjectStatus(Long projectId,String status);

	public List<ProjectDTO> getTeacherPenddingProjects(Long userId);
	
	List<AdminActivityDTO> getGuideActivityStats();
}