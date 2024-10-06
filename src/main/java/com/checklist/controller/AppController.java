package com.checklist.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checklist.model.Task;
import com.checklist.model.TaskHistory;
import com.checklist.model.TaskSearchFilter;
import com.checklist.model.User;
import com.checklist.service.TaskService;
import com.checklist.util.DateUtil;

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
	
	@GetMapping("/detail/{date}")
	public String getHistory(Model md, HttpSession session, @PathVariable("date") String date) {
		User user = (User) session.getAttribute("user");

		// Define the expected date format
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		TaskSearchFilter tsf = new TaskSearchFilter();

		try {
			Date parsedDate = formatter.parse(date);
			tsf.setCreatedFrom(parsedDate);
			tsf.setCreatedTo(DateUtil.addDay(parsedDate, 1));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Task> taskList = taskService.getTask(tsf, user.getId());
		md.addAttribute("taskList", taskList);
		return "task-detail";
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
	
	@GetMapping("/history")
	public String getHistory(Model md, HttpSession session) {
		User user = (User)session.getAttribute("user");
		List<TaskHistory> th = taskService.getTaskHistory(user.getId());
		md.addAttribute("taskHistory", th);
		return "history";
	}
}
