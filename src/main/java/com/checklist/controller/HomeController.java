package com.checklist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "<h2>App Started....</h2>";
	}
	
	@GetMapping("/")
	public String getHome() {
		return "index";
	}
}
