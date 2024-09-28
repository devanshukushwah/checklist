package com.checklist.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checklist.model.Task;
import com.checklist.service.TaskService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AppController {
	
	private TaskService taskService;

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "<h2>App Started....</h2>";
	}
	
	@GetMapping("/")
	public String getHome(Model md) {

		List<Task> homeTask = taskService.getHomeTask();
		md.addAttribute("taskList", homeTask);	
		
		return "home";
	}
	
	@PostMapping("/add-checklist")
	public String addChecklist() {
		return "add-checklist";
	}
}
