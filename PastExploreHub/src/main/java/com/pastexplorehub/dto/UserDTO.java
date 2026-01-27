package com.pastexplorehub.dto;

import com.pastexplorehub.entity.User;

import lombok.Data;

@Data
public class UserDTO {
	private Long userId;
    private String name;
    private String enrollId;
    private String email;
    private Long department; // Matches <select name="department">
    private String role;     // Matches <select name="role">
    private String password;
    private String confirm_password;
	
    public UserDTO() {}
    
    public UserDTO(User user) {
    	this.userId = user.getUserId();
    	this.name = user.getName();
    	this.enrollId = user.getEnrollId();
    	this.email = user.getEmail();
    	this.department = user.getDepartment().getId();
    	this.setRole(user.getRole());
    }
    
    
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnrollId() {
		return enrollId;
	}
	public void setEnrollId(String enrollId) {
		this.enrollId = enrollId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getDepartment() {
		return department;
	}
	public void setDepartment(Long department) {
		this.department = department;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	
	

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", name=" + name + ", enrollId=" + enrollId + ", email=" + email
				+ ", department=" + department + ", role=" + role + ", password=" + password + ", confirm_password="
				+ confirm_password + "]";
	}

	public UserDTO toUserDTO(User user)
	{
		this.setUserId(user.getUserId());
		this.setName(user.getName());
    	this.setEnrollId(user.getEmail());
    	this.setEmail(user.getEmail());
    	this.setDepartment(user.getDepartment().getId());
    	this.setRole(user.getRole());
    	return this;
	}
}