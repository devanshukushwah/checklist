package com.checklist.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.checklist.model.Task;
import com.checklist.model.TaskSearchFilter;
import com.checklist.model.User;
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

	@GetMapping(value = {"/", "/home"})
	public String getHome(Model md, HttpSession session) {
		User user = (User)session.getAttribute("user");
		List<Task> homeTask = taskService.getHomeTask(user.getId());
		md.addAttribute("taskList", homeTask);
		return "home";
	}
	
	@GetMapping("/history")
	public String getHistory(Model md, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Task> taskList = taskService.getTask(new TaskSearchFilter(), user.getId());
//		System.out.println(taskList);
		md.addAttribute("taskList", taskList);
		return "history";
	}

	@PostMapping("/updateStatus")
	public String updateStatus(@ModelAttribute Task task) {
		boolean res = taskService.updateStatus(task.getId(), true);
		if (res) {
			return "redirect:home";
		}
		
		return "redirect:home";
		
	}

	@PostMapping("/add")
	public String addTask(@ModelAttribute Task task, HttpSession session) {
		User user = (User)session.getAttribute("user");
		boolean res = taskService.addTask(user.getId(), task);
		
		if (res) {
			return "redirect:home";	
		}
		
		return "redirect:home";
	}
}
