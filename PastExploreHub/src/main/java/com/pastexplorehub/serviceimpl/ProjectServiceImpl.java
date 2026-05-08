package com.pastexplorehub.serviceimpl;

import com.pastexplorehub.dto.AdminActivityDTO;
import com.pastexplorehub.dto.ProjectDTO;
import com.pastexplorehub.entity.*;
import com.pastexplorehub.model.Status;
import com.pastexplorehub.repository.*;
import com.pastexplorehub.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired private ProjectRepository projectRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private DepartmentRepository deptRepo;
    @Autowired private ProjectFileRepository fileRepo;
    @Autowired private ProjectTeamMemberRepository teamRepo;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Project submitProject(ProjectDTO dto) throws Exception {
        // 1. Initialize and Map Main Project Entity
        Project project = new Project();
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setTechStack(dto.getTechStack());
        project.setProjectLink(dto.getProjectLink());
        project.setLeadEnrollId(dto.getLeadEnrollId());
        project.setStatus(Status.PENDING);

     
        // 2. Fetch Managed Entities from DB
        User student = userRepo.findByEnrollId(dto.getLeadEnrollId())
                .orElseThrow(() -> new Exception("Logged-in Student not found"));
        User guide = userRepo.findById(dto.getGuideId())
                .orElseThrow(() -> new Exception("Selected Mentor not found"));
        Department dept = deptRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new Exception("Department not found"));

        project.setStudent(student);
        project.setGuide(guide);
        project.setDepartment(dept);

        // 3. Save Project to generate ID
        Project savedProject = projectRepo.save(project);

        // 4. Save Dynamic Team Members
        if (dto.getTeamMemberName() != null) {
            for (int i = 0; i < dto.getTeamMemberName().size(); i++) {
                ProjectTeamMember member = new ProjectTeamMember();
                member.setName(dto.getTeamMemberName().get(i));
                member.setEnrollId(dto.getTeamMemberEnrollId().get(i));
                member.setProject(savedProject);
                teamRepo.save(member);
            }
        }

        // 5. Handle Image Uploads (MultipartFile[])
        if (dto.getProjectImages() != null) {
            for (MultipartFile img : dto.getProjectImages()) {
                if (!img.isEmpty()) {
                    saveFileToDb(savedProject, img, "IMAGE");
                }
            }
        }

        // 6. Handle PDF Code Upload (MultipartFile)
        if (dto.getProjectCode() != null && !dto.getProjectCode().isEmpty()) {
            saveFileToDb(savedProject, dto.getProjectCode(), "PDF_CODE");
        }

        return savedProject;
    }

    private void saveFileToDb(Project project, MultipartFile file, String type) throws IOException {
        ProjectFile pf = new ProjectFile();
        pf.setProject(project);
        pf.setFileName(file.getOriginalFilename());
        pf.setFileType(file.getContentType());
        pf.setData(file.getBytes());
        fileRepo.save(pf);
    }
    
    public List<ProjectDTO> getAllProjectByStatus(Status status){

    	List<Project> projects = projectRepo.findByStatus(status);
    	
    	return projects.stream().map(project->new ProjectDTO().projectToProjectDTO(project)).collect(Collectors.toList());
    }
    
    public ProjectDTO getProjectById(Long projectId) {
    	Project project = projectRepo.findByProjectId(projectId);
    	return new ProjectDTO().projectToProjectDTO(project);
    }
    
    @Transactional
    public Status updateProjectStatus(Long projectId,String status,String message) {
    	    Status newStatus = Status.valueOf(status.toUpperCase());
      	
    try {  projectRepo.updateProjectStatus(projectId, newStatus,message);}
    catch(Exception e) {
    	System.out.println("issue is this : "+e);
    }
    	return newStatus; 
    }

	@Override
	public List<ProjectDTO> getTeacherPenddingProjects(Long userId) {
		List<Project> projects = projectRepo.findByGuideUserIdAndStatus(userId,Status.PENDING);
		//System.out.println("issue is this "+userId+" : "+projects);
		List<ProjectDTO> guideProjects = new ArrayList<>();
		if(projects!=null) {
		 guideProjects = projects.stream().map(project-> new ProjectDTO().projectToProjectDTO(project)).collect(Collectors.toList());
		}
		return guideProjects;
	}  
	
	public List<AdminActivityDTO> getGuideActivityStatus(){
		
		return projectRepo.getGuideActivityStats();
	}

	@Override
	public List<ProjectDTO> getMyProjects(Long userId) {
		List<Project> projects = projectRepo.findByStudent_UserId(userId);
		
		List<ProjectDTO> myProjects = new ArrayList<>();
		
		if(projects!=null) {
		 myProjects = projects.stream().map(project-> new ProjectDTO().projectToProjectDTO(project)).collect(Collectors.toList());
		}
		return myProjects;
	}
}
