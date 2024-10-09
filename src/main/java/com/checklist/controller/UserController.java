package com.checklist.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checklist.exception.MyAppException;
import com.checklist.model.User;
import com.checklist.service.UserService;

import lombok.AllArgsConstructor;

/**
 * Controller responsible for handling user authentication and registration.
 * It provides endpoints for user login, registration, and logout functionality.
 */
@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    /**
     * Displays the login page.
     *
     * @return the name of the JSP page for login
     */
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    /**
     * Validates the user credentials and logs the user in if successful.
     * If the credentials are invalid, it returns to the login page with an error message.
     *
     * @param user the user object containing email and password entered by the user
     * @param session the current HTTP session to store the logged-in user
     * @param md the model to pass attributes (like error messages) to the view
     * @return the home page if login is successful, otherwise returns to the login page
     */
    @PostMapping("/login")
    public String validateUser(@ModelAttribute User user, HttpSession session, Model md) {
        User loginUser;
        try {
            loginUser = userService.getUser(user.getEmail(), user.getPassword());
        } catch (MyAppException e) {
            md.addAttribute("errorMessage", e.getMessage());
            return "login";
        }
        if (loginUser != null) {
            session.setAttribute("user", loginUser);
            return "redirect:home";
        }
        md.addAttribute("errorMessage", "Failed to login");
        return "login";
    }

    /**
     * Displays the registration page.
     *
     * @return the name of the JSP page for user registration
     */
    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    /**
     * Creates a new user account. If successful, it redirects to the login page.
     * If there is an error, it returns to the registration page with an error message.
     *
     * @param user the user object containing registration information
     * @param md the model to pass attributes (like error messages) to the view
     * @return the login page if registration is successful, otherwise returns to the register page
     */
    @PostMapping("/register")
    public String createUser(@ModelAttribute User user, Model md) {
        boolean res;
        try {
            res = userService.create(user);
        } catch (MyAppException e) {
            md.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
        if (res) {
            return "login";
        }
        md.addAttribute("errorMessage", "Failed to create user");
        return "register";
    }

    /**
     * Logs out the current user by invalidating the session and redirects to the login page.
     *
     * @param session the current HTTP session to be invalidated
     * @return a redirect to the login page
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidate session on logout
        return "redirect:/login";
    }
}
