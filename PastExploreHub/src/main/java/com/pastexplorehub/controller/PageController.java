package com.pastexplorehub.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pastexplorehub.dto.AdminActivityDTO;
import com.pastexplorehub.dto.ProjectDTO;
import com.pastexplorehub.dto.UserDTO;
import com.pastexplorehub.model.Status;
import com.pastexplorehub.service.DepartmentService;
import com.pastexplorehub.service.ProjectService;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/pastexplorehub/user")
@Controller // Use @Controller to return JSPs
public class PageController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/home")
	public String showHome(HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("loggedInUser");

		List<ProjectDTO> projects = projectService.getAllProjectByStatus(Status.APPROVED);
		session.setAttribute("projects", projects);
		if (user == null)
			return "redirect:/pastexplorehub/user-api/login";
		return user.getRole().toString().toLowerCase();
	}

	@GetMapping("/profile")
	public String showProfile(HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("loggedInUser");
		if (user == null)
			return "redirect:/pastexplorehub/user-api/login";

		String role = user.getRole();
		// FIX: Use equalsIgnoreCase to avoid Case-Sensitivity issues
		if (role.equalsIgnoreCase("student"))
			return "student_profile";
		if (role.equalsIgnoreCase("admin"))
			return "admin_profile";
		return "teacher_profile";
	}

	// Handles the "Upload Projects" link
	@GetMapping("/upload-project")
	public String showUploadPage(HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("loggedInUser");
		// FIX: Allow students in, block everyone else
		if (user == null || !user.getRole().toString().equalsIgnoreCase("student")) {
			return "redirect:/pastexplorehub/user-api/login";
		}
		return "upload_project";
	}

	@GetMapping("/student")
	public String showHomePage(HttpSession session) {
		// Get the user from the session
		UserDTO userDto = (UserDTO) session.getAttribute("loggedInUser");
		if (userDto == null) {
			return "redirect:/pastexplorehub/user-api/login"; // If no one is logged in, go to login page
		}
		// Return the JSP name based on the role
		// If role is STUDENT, it returns "student" (student.jsp)
		// If role is TEACHER, it returns "teacher" (teacher.jsp)
		String role = userDto.getRole().toString().toLowerCase();
		// System.out.println(role);
		return role;
	}

	@GetMapping("/admin-profile")
	public String showAdminProfile(HttpSession session) {
		UserDTO userDto = (UserDTO) session.getAttribute("loggedInUser");
		if (userDto == null || !userDto.getRole().equals("ADMIN")) {

			return "redirect:/pastexplorehub/user-api/login";
		}
		return "admin_profile"; // This resolves to /WEB-INF/views/admin_profile.jsp
	}

	@GetMapping("/admin-activity")
	public String showAdminActivity(HttpSession session,Model model) {
		UserDTO userDto = (UserDTO) session.getAttribute("loggedInUser");
		
		
		if (userDto == null || !userDto.getRole().equals("ADMIN")) {

			return "redirect:/pastexplorehub/user-api/login";
		}
		
		List<ProjectDTO> approvedProjects = projectService.getAllProjectByStatus(Status.APPROVED);
		List<ProjectDTO> pendingProjects = projectService.getAllProjectByStatus(Status.PENDING);
		List<ProjectDTO> rejectedProjects = projectService.getAllProjectByStatus(Status.REJECTED);
		
		model.addAttribute("approvedProjects", approvedProjects);
		model.addAttribute("pendingProjects", pendingProjects);
		model.addAttribute("rejectedProjects", rejectedProjects);
		
		
		List<String> depts = departmentService.getAllDepartmentName();
	    model.addAttribute("deptList", depts);
		
	     List<AdminActivityDTO> activityStats= projectService.getGuideActivityStats();
	    model.addAttribute("activityStats", activityStats);
		
	    
		return "admin_activity"; // This resolves to /WEB-INF/views/admin_profile.jsp
	}

	@GetMapping("/student-profile")
	public String showStudentProfile(HttpSession session) {
		UserDTO userDto = (UserDTO) session.getAttribute("loggedInUser");

		if (userDto == null || !(userDto.getRole().toUpperCase().equals("STUDENT"))) {
		
			return "redirect:/pastexplorehub/user/login";
		}
		
		return "student_profile"; // This resolves to /WEB-INF/views/admin_profile.jsp
	}

	@GetMapping("/teacher-profile")
	public String showTeacherProfile(HttpSession session) {
		UserDTO userDto = (UserDTO) session.getAttribute("loggedInUser");
		if (userDto == null || !userDto.getRole().equals("teacher")) {
		
			return "redirect:/pastexplorehub/user/login";
		}
		
		return "teacher_profile"; // This resolves to /WEB-INF/views/admin_profile.jsp
	}
	
	

}