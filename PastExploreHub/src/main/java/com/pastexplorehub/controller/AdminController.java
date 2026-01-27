package com.pastexplorehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pastexplorehub.entity.Department;
import com.pastexplorehub.service.DepartmentService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/pastexplorehub/user/actions")
@Controller 
public class AdminController {
	
		@Autowired
		DepartmentService departmentService;

		@GetMapping("/addDepartment")
		public String addDepartment(HttpSession session) {
		return "admin_action";
}
		
		@PostMapping("/saveDepartment")
		public String saveDepartment(@ModelAttribute Department department, RedirectAttributes redirectAttributes) {
		    try {
		        // 1. Save the new department
		        departmentService.saveDepartment(department);
		        // 2. Add a success message for the next page
		        redirectAttributes.addFlashAttribute("successMsg", "Department '" + department.getDeptName() + "' added successfully!");
		        // 3. Redirect back to the activity page or the form
		        return "redirect:/pastexplorehub/user/admin-activity";
		    
		    } catch (Exception e) {
		        // Handle duplicate codes or database errors
		        redirectAttributes.addFlashAttribute("errorMsg", "Failed to add department. Code may already exist.");
		        return "redirect:/pastexplorehub/user/actions/addDepartment";
		    }
		}
} 