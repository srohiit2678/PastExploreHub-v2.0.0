package com.pastexplorehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.pastexplorehub.dto.ProjectDTO;
import com.pastexplorehub.dto.UserDTO;
import com.pastexplorehub.entity.ProjectComment;
import com.pastexplorehub.service.ProjectCommentService;
import com.pastexplorehub.service.ProjectService;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/pastexplorehub/project")
@Controller
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectCommentService commentService;

	@PostMapping("/project-details")
	public String uploadProjectDetails(@ModelAttribute ProjectDTO projectDto, HttpSession session) {

		// 1. Get current logged in student ID from session
		UserDTO loggedInUser = (UserDTO) session.getAttribute("loggedInUser");

		projectDto.setDepartmentId(loggedInUser.getDepartment());
		projectDto.setStudentId(loggedInUser.getUserId());

		try {
			// 2. Pass the DTO to your service
			projectService.submitProject(projectDto);
			return "redirect:/pastexplorehub/user/home?success=true";
		} catch (Exception e) {
			return "redirect:/pastexplorehub/user/upload-project?error=" + e.getMessage();
		}
	}

	@GetMapping("/project-view")
	public String fatchProject(@RequestParam("id") Long id, HttpSession session) {

		ProjectDTO project = projectService.getProjectById(id);
	
		session.setAttribute("projectdetails", project);
		  List<ProjectComment>comments = commentService.getCommentsByProject(project.getProjectId());
		  session.setAttribute("project-comments", comments);
		return "project_view";
	}

	@GetMapping("/approve-projects")
	public String approveProjectDashBoard(HttpSession session) {	
		UserDTO user = (UserDTO)session.getAttribute("loggedInUser");
		List<ProjectDTO> projects = projectService.getTeacherPenddingProjects(user.getUserId()); // guideID
		session.setAttribute("panding-projects", projects);
		return "approve_project";
	}

	@PostMapping("/update-status")
	public String updateStatus(@RequestParam("projectId") Long projectId, @RequestParam("newStatus") String newStatus,  RedirectAttributes redirectAttributes) {
	    try {
	    	System.out.println("this");
	    	newStatus = projectService.updateProjectStatus(projectId, newStatus).toString();
	    	System.out.println(newStatus);
	        redirectAttributes.addFlashAttribute("message", "Project status updated to " + newStatus);
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("error", "Failed to update status");
	    }
	    // Redirect back to the teacher's dashboard
	    return "redirect://pastexplorehub/user/home";
	}
	
	@PostMapping("/add-comment")
	public String addComment(@RequestParam("projectId") Long projectId, @RequestParam("message") String message, HttpSession session) {
	    
		System.out.println("hello here");
	    UserDTO loggedInUser = (UserDTO) session.getAttribute("loggedInUser");
    	System.out.println(loggedInUser);
	    if (loggedInUser != null) {
	    	System.out.println(loggedInUser);
	     commentService.addComment(projectId, loggedInUser.getUserId(), message);
	     List<ProjectComment>comments = commentService.getCommentsByProject(projectId);
	     session.setAttribute("project-comments", comments);
	    }
	    return "redirect:/pastexplorehub/project/project-view?id=" + projectId;
	}
}
