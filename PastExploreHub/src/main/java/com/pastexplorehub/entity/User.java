package com.pastexplorehub.entity;

import com.pastexplorehub.dto.UserDTO;
import com.pastexplorehub.model.UserRole; // We will put Enums in .model
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String enrollId;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public User() {}
    
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEnrollId() {
		return enrollId;
	}

	public void setEnrollId(String enrollId) {
		this.enrollId = enrollId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role.toString();
	}

	public void setRole(String role) {
		role=role.toUpperCase(); 
		switch(role) {
		case "ADMIN": this.role=UserRole.ADMIN;
		break;
		case "STUDENT":this.role=UserRole.STUDENT;
		break;
		case "TEACHER":this.role=UserRole.TEACHER;
		break;
		}
		
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", enrollId=" + enrollId + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", role=" + role + ", department=" + department + "]";
	}

	public User toUser(UserDTO userDto) {
	    this.setUserId(userDto.getUserId());
	    this.setName(userDto.getName());
	    this.setEnrollId(userDto.getEnrollId()); // Use getEnrollId() here
	    this.setEmail(userDto.getEmail());
	    
	    if (userDto.getDepartment() != null) {
	        Department dep = new Department();
	        dep.setId(userDto.getDepartment());
	        this.setDepartment(dep);
	    }
	    
	    this.setRole(userDto.getRole());
	    return this;
	}
	
}