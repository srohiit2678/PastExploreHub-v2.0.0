package com.pastexplorehub.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class ProjectComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long comment_id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private User userId;
    
    @Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
	private Project projectId;
    
    private String message;
	
	
	public Long getComment_id() {
		return comment_id;
	}
	public void setComment_id(Long comment_id) {
		this.comment_id = comment_id;
	}
	public User getUser() {
		return userId;
	}
	public void setUser(User userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Project getProjectId() {
		return projectId;
	}
	public void setProjectId(Project projectId) {
		this.projectId = projectId;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ProjectComment [comment_id=" + comment_id + ", userId=" + userId.getEnrollId() + ", date=" + date + ", projectId="
				+ projectId.getProjectId() + ", message=" + message + "]";
	}
	
	
	
}
