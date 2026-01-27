package com.pastexplorehub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pastexplorehub.dto.TeacherDTO;
import com.pastexplorehub.dto.UserDTO;
import com.pastexplorehub.entity.User;
import com.pastexplorehub.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/pastexplorehub/user-api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginPage() {

		return "login";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}

	@PostMapping("/login")
	public String UserLogin(@RequestParam String enrollId, @RequestParam String password, HttpSession session,
			Model model) {
		try {
			UserDTO userDto = userService.login(enrollId, password);
			session.setAttribute("loggedInUser", userDto);
			if (userDto.getRole().toLowerCase().equals("student")) {
				List<TeacherDTO> teachers = userService.getAllTeachers();
				session.setAttribute("teachersAsGuid", teachers);
			}
			// the full path defined in PageController to reach home page
			return "redirect:/pastexplorehub/user/home";
		} catch (Exception e) {
			model.addAttribute("error", "Invalid Enrollment ID or Password");
			return "login";
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@ModelAttribute UserDTO userDto) {
		try {
			User user = userService.registerUser(userDto);
			return ResponseEntity.ok(user);
		} catch (Exception e) {

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
