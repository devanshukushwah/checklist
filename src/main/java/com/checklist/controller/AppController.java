package com.checklist.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checklist.model.PageRequest;
import com.checklist.model.PageResponse;
import com.checklist.model.Task;
import com.checklist.model.TaskHistory;
import com.checklist.model.TaskSearchFilter;
import com.checklist.model.User;
import com.checklist.service.TaskService;
import com.checklist.util.DateUtil;

import lombok.AllArgsConstructor;

/**
 * This controller handles requests for managing tasks in the checklist application.
 * It provides functionalities for displaying tasks, task history, adding tasks, 
 * and updating task status.
 */
@Controller
@AllArgsConstructor
public class AppController {

    private TaskService taskService;

    /**
     * A simple test endpoint to verify if the application is running.
     *
     * @return a string confirming the app has started
     */
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "<h2>App Started....</h2>";
    }

    /**
     * Handles the request to display the home page, which shows a list of tasks 
     * for the logged-in user.
     *
     * @param md the model to pass attributes to the view
     * @param session the current HTTP session to retrieve the logged-in user
     * @return the name of the JSP page to render the home view
     */
    @GetMapping(value = {"/", "/home"})
    public String getHome(Model md, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Task> homeTask = taskService.getHomeTask(user.getId());
        md.addAttribute("taskList", homeTask);
        return "home";
    }

    /**
     * Handles the request to display the task history for a specific date.
     * It parses the date from the URL, fetches tasks created on that date, 
     * and adds them to the model.
     *
     * @param md the model to pass attributes to the view
     * @param session the current HTTP session to retrieve the logged-in user
     * @param date the date string (in yyyy-MM-dd format) from the URL
     * @return the name of the JSP page to render the task details view
     */
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

    /**
     * Updates the status of a task and redirects the user to the home page.
     *
     * @param task the task object whose status needs to be updated
     * @return a redirect to the home page
     */
    @PostMapping("/updateStatus")
    public String updateStatus(@ModelAttribute Task task) {
        boolean res = taskService.updateStatus(task.getId(), true);
        return "redirect:home";
    }

    /**
     * Adds a new task to the user's task list and redirects to the home page.
     *
     * @param task the task to be added
     * @param session the current HTTP session to retrieve the logged-in user
     * @return a redirect to the home page
     */
    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task, HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean res = taskService.addTask(user.getId(), task);
        return "redirect:home";
    }

    /**
     * Displays the task history for the logged-in user, with pagination support.
     *
     * Retrieves the task history based on the logged-in user's ID and pagination 
     * parameters (page number and page size). The task history is then passed 
     * to the view to be displayed.
     *
     * @param md the model used to pass attributes to the view
     * @param session the current HTTP session to retrieve the logged-in user
     * @param pageNo the current page number for pagination, defaults to 1 if not provided
     * @param pageSize the number of records per page for pagination, defaults to 10 if not provided
     * @return the name of the JSP page to render the task history view ("history")
     */
	@GetMapping("/history")
	public String getHistory(Model md, HttpSession session,
			@RequestParam(value = "pageNo", defaultValue = "1", required = false) String pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) String pageSize) {
		User user = (User) session.getAttribute("user");
		int iPageNo = Integer.parseInt(pageNo);
		int iPageSize = Integer.parseInt(pageSize);

		PageRequest pageRequest = PageRequest.builder().pageNo(iPageNo).pageSize(iPageSize).build();
		PageResponse<List<TaskHistory>> prth = taskService.getTaskHistory(user.getId(), pageRequest);
		md.addAttribute("pageNo", iPageNo);
		md.addAttribute("pageSize", iPageSize);
		md.addAttribute("totalCount", prth.getTotalCount());
		md.addAttribute("taskHistory", prth.getData());

		int totalPages = (int) Math.ceil((double) prth.getTotalCount() / iPageSize);

		md.addAttribute("totalPages", totalPages);
		return "history";
	}
}
