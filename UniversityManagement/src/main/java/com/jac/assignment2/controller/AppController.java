package com.jac.assignment2.controller;

import com.jac.assignment2.model.*;
import com.jac.assignment2.repository.*;
import com.jac.assignment2.service.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import java.util.*;

@Controller
public class AppController {

	@Autowired
	private UserService service;
	@Autowired
	private studentByEmailRepository studentByEmailRepository;
	@Autowired
	private  ProfessorByEmailRepository professorByEmailRepository;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		service.registerDefaultUser(user);

		return "register_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
//		User user=service.get(id);
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}
	@GetMapping("/newUser")
	public String newUser(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currenUserName = authentication.getName();;
			model.addAttribute("user", currenUserName);
		}
		return "newUser";
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = service.get(id);
		List<Role> listRoles = service.listRoles();
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("user", user);
		return "user_form";
	}

	@PostMapping("/users/save")
	public String saveUser(User user) {
		service.save(user);

		return "redirect:/users";
	}

	@PostMapping("/users/update")
	public String updateUser(User user) {
		service.update(user);

		return "redirect:/users";
	}

	@PostMapping("/login")
	public String login() {
		return "redirect:/users";
	}

	@GetMapping(value = "/admin")
	public String currentUserName(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			model.addAttribute("username",currentUserName);
		}
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);

		return "administration";
	}

	@GetMapping(value = "/studentDashboard")
	public String currentStudentName(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentStudentName = authentication.getName();
			Student student = studentByEmailRepository.findByEmail(currentStudentName);
			model.addAttribute("student", student);
		}
		return "studentDashboard";
	}

	@GetMapping("/professorDashboard")
	public String professorDashboard(Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentProfName = authentication.getName();
			Professor professor = professorByEmailRepository.findByEmail(currentProfName);
			model.addAttribute("professor", professor);
		}

		    return "professorDashboard";
	}


}
