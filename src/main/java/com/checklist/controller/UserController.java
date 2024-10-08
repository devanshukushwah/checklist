package com.checklist.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checklist.exception.DatabaseError;
import com.checklist.exception.MyAppException;
import com.checklist.exception.UserAlreadyExists;
import com.checklist.model.User;
import com.checklist.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserController {
	
	private UserService userService;
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@PostMapping("/login")
	public String validateUser(@ModelAttribute User user, HttpSession session, Model md) {
		User loginUser;
		try {
			loginUser = userService.getUser(user.getEmail(), user.getPassword());
		} catch (MyAppException e) {
			md.addAttribute("errorMessage", e.getMessage());
			return "login";
		}
		if(loginUser != null) {
			session.setAttribute("user", loginUser);
			return "redirect:home";
		}
		md.addAttribute("errorMessage", "failed to login");
		return "login";
	}
	
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}
	
	@PostMapping("/register")
	public String createUser(@ModelAttribute User user, Model md) {
		boolean res;
		try {
			res = userService.create(user);
		} catch (MyAppException e) {
			md.addAttribute("errorMessage", e.getMessage());
			return "register";
		} 
		if(res) {
			return "login";
		}
		
		md.addAttribute("errorMessage", "failed to create user");
		return "register";
	}
	
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidate session on logout
        return "redirect:/login";
    }
}
