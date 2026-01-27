package com.pastexplorehub.dto;

import org.springframework.web.multipart.MultipartFile;

import com.pastexplorehub.entity.Project;
import com.pastexplorehub.entity.ProjectTeamMember;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Base64;
import java.util.Date;
@Data
public class ProjectDTO {
	
    private Long projectId;
    // Basic Info
    private String title;
    private String description;
    
    // Selection IDs
    private Long guideId;      // For <select name="guideId">
    private String guideName;
    
    private Long departmentId; // You'll need this to link to the dept
    private String techStack;  // For <select name="techStack">
    
    // Team Info
    private String leadName;
    private String leadEnrollId;
    
    // Since JSP sends simple name arrays for dynamic members:
    private List<String> teamMemberName;
    private List<String> teamMemberEnrollId;
    
    // Project Metadata
    private String projectLink;
    private String status; // Usually set to PENDING in Service
    
    // File Uploads - Variable names must match name="" in JSP
    private MultipartFile[] projectImages; // For multiple images
    private MultipartFile projectCode;    // For the PDF file
    
    
    // DB to FrantEnd
    private List<String> base64Images; // For <img> tags
    private String pdfFileName;
    private Long pdfFileId;
    private String base64PDF;
    
    // Helper fields for mapping
    private Long studentId; // This should be populated from Session in the Controller

    private Date createdAt;
    
    
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getGuideId() {
		return guideId;
	}

	public void setGuideId(Long guideId) {
		this.guideId = guideId;
	}
	
	

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getTechStack() {
		return techStack;
	}

	public void setTechStack(String techStack) {
		this.techStack = techStack;
	}

	public String getLeadName() {
		return leadName;
	}

	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}

	public String getLeadEnrollId() {
		return leadEnrollId;
	}

	public void setLeadEnrollId(String leadEnrollId) {
		this.leadEnrollId = leadEnrollId;
	}

	public List<String> getTeamMemberName() {
		return teamMemberName;
	}

	public void setTeamMemberName(List<String> teamMemberName) {
		this.teamMemberName = teamMemberName;
	}

	public List<String> getTeamMemberEnrollId() {
		return teamMemberEnrollId;
	}

	public void setTeamMemberEnrollId(List<String> teamMemberEnrollId) {
		this.teamMemberEnrollId = teamMemberEnrollId;
	}

	public String getProjectLink() {
		return projectLink;
	}

	public void setProjectLink(String projectLink) {
		this.projectLink = projectLink;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MultipartFile[] getProjectImages() {
		return projectImages;
	}

	public void setProjectImages(MultipartFile[] projectImages) {
		this.projectImages = projectImages;
	}

	public MultipartFile getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(MultipartFile projectCode) {
		this.projectCode = projectCode;
	}

	public Long getStudentId() {
		return studentId;
	}
	
	

	public List<String> getBase64Images() {
		return base64Images;
	}

	public void setBase64Images(List<String> base64Images) {
		this.base64Images = base64Images;
	}

	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}

	public Long getPdfFileId() {
		return pdfFileId;
	}

	public void setPdfFileId(Long pdfFileId) {
		this.pdfFileId = pdfFileId;
	}
	
	

	public String getBase64PDF() {
		return base64PDF;
	}

	public void setBase64PDF(String base64pdf) {
		base64PDF = base64pdf;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
	

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	
	
	@Override
	public String toString() {
		return "ProjectDTO [projectId=" + projectId + ", title=" + title + ", description=" + description + ", guideId="
				+ guideId + ", guideName=" + guideName + ", departmentId=" + departmentId + ", techStack=" + techStack
				+ ", leadName=" + leadName + ", leadEnrollId=" + leadEnrollId + ", teamMemberName=" + teamMemberName
				+ ", teamMemberEnrollId=" + teamMemberEnrollId + ", projectLink=" + projectLink + ", status=" + status
				+ ", projectImages=" + Arrays.toString(projectImages) + ", projectCode=" + projectCode
				+ ", base64Images=" + base64Images + ", pdfFileName=" + pdfFileName + ", pdfFileId=" + pdfFileId
				+ ", base64PDF=" + base64PDF + ", studentId=" + studentId + ", createdAt=" + createdAt + "]";
	}

	public ProjectDTO projectToProjectDTO(Project project) {
		this.setProjectId(project.getProjectId());
		this.setTitle(project.getTitle());
		this.setDescription(project.getDescription());
		this.setGuideId(project.getGuide().getUserId());
		this.setGuideName(project.getGuide().getName());
		this.setDepartmentId(project.getDepartment().getId());
		this.setTechStack(project.getTechStack());
		
		this.setLeadName(project.getStudent().getName());
		this.setLeadEnrollId(project.getLeadEnrollId());
		
		List<ProjectTeamMember> members = project.getTeamMembers();
		List<String> memberName= new ArrayList<>();
		List<String> memberEnrollId = new ArrayList<>();
		
		for(ProjectTeamMember member:members) {
			memberName.add(member.getName());
			memberEnrollId.add(member.getEnrollId());
			
		}
		this.setTeamMemberName(memberName);
		this.setTeamMemberEnrollId(memberEnrollId);
		
		this.setProjectLink(project.getProjectLink());
		this.setStatus(project.getStatus().name());
		
		this.setCreatedAt(project.getCreatedAt());
	//	ProjectDTO dto = new ProjectDTO();
	//    dto.setProjectId(project.getProjectId());
	//    this.setTitle(project.getTitle());
	    
		List<String> displayImages = project.getProjectFiles().stream()
		        .filter(f -> f.getFileType().startsWith("image"))
		        .map(f -> "data:" + f.getFileType() + ";base64," + Base64.getEncoder().encodeToString(f.getData()))
		        .collect(Collectors.toList());
		        
		    this.setBase64Images(displayImages);
		    
		    project.getProjectFiles().stream()
		    .filter(f -> f.getFileType().equals("application/pdf"))
		    .findFirst()
		    .ifPresent(pdf -> {
		        this.setPdfFileId(pdf.getFileId());
		        this.setPdfFileName(pdf.getFileName());
		        
		        // 1. Convert the byte array to Base64 String
		        String base64Content = Base64.getEncoder().encodeToString(pdf.getData());
		        // 2. Format as a Data URI so the browser recognizes it as a file
		        String fullPdfString = "data:application/pdf;base64," + base64Content;
		        
		        // 3. Set this into your new DTO field (make sure you added this field to ProjectDTO)
		        this.setBase64PDF(fullPdfString);
		    });
		
		this.setStudentId(project.getStudent().getUserId());
		return this;
	}
    
}