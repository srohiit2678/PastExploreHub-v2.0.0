package com.pastexplorehub.entity;

import com.pastexplorehub.model.Status;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "student_user_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "guide_user_id")
    private User guide;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private String techStack;
    private String projectLink;
    private String leadEnrollId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    // Relationship to Team Members
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectTeamMember> teamMembers;

    // Relationship to Files (Images and PDF)
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectFile> projectFiles;

    
    
    
    
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

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public User getGuide() {
		return guide;
	}

	public void setGuide(User guide) {
		this.guide = guide;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTechStack() {
		return techStack;
	}

	public void setTechStack(String techStack) {
		this.techStack = techStack;
	}

	public String getProjectLink() {
		return projectLink;
	}

	public void setProjectLink(String projectLink) {
		this.projectLink = projectLink;
	}

	public String getLeadEnrollId() {
		return leadEnrollId;
	}

	public void setLeadEnrollId(String leadEnrollId) {
		this.leadEnrollId = leadEnrollId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<ProjectTeamMember> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<ProjectTeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public List<ProjectFile> getProjectFiles() {
		return projectFiles;
	}

	public void setProjectFiles(List<ProjectFile> projectFiles) {
		this.projectFiles = projectFiles;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", title=" + title + ", description=" + description + ", student="
				+ student + ", guide=" + guide + ", department=" + department + ", status=" + status + ", techStack="
				+ techStack + ", projectLink=" + projectLink + ", leadEnrollId=" + leadEnrollId + ", createdAt="
				+ createdAt + ", teamMembers=" + teamMembers + ", projectFiles=" + projectFiles + "]";
	}
    
    
}