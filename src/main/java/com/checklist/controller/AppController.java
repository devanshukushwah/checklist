package com.checklist.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checklist.model.Task;

@Controller
public class AppController {

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "<h2>App Started....</h2>";
	}
	
	@GetMapping("/")
	public String getHome(Model md) {
		List<Task> tsk = Arrays.asList(new Task("Task 1", false), 
		new Task("Task 2", true), new Task("Task 3", false), new Task("Task 4", true));
		md.addAttribute("taskList", tsk);
		return "home";
	}
	
	@PostMapping("/add-checklist")
	public String addChecklist() {
		return "add-checklist";
	}
}
