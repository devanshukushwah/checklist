package com.checklist.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String validateUser(@ModelAttribute User user, HttpSession session) {
		User loginUser = userService.getUser(user.getEmail(), user.getPassword());
		if(loginUser != null) {
			session.setAttribute("user", loginUser);
			return "redirect:home";
		}
		return "login";
	}
	
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}
	
	@PostMapping("/register")
	public String createUser(@ModelAttribute User user) {
		boolean res = userService.create(user);
		if(res) {
			return "login";
		}
		return "register";
	}
	
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidate session on logout
        return "redirect:/login";
    }
}
