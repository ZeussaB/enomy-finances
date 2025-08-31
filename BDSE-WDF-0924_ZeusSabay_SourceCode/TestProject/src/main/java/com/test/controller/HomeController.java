package com.test.controller;

import com.test.model.User;
import com.test.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "index"; // index.jsp in /WEB-INF/views/
    }

    @GetMapping("/account")
    public String account(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "account"; // account.jsp
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmNewPassword,
                                 HttpSession session,
                                 Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        if (!userService.verifyPassword(user, currentPassword)) {
            model.addAttribute("errorMessage", "Current password is incorrect.");
            return "account";
        }

        if (!newPassword.equals(confirmNewPassword)) {
            model.addAttribute("errorMessage", "New passwords do not match.");
            return "account";
        }

        userService.updatePassword(user.getEmail(), newPassword);
        model.addAttribute("successMessage", "Password updated successfully.");
        return "account";
    }
    
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password.");
        }
        return "login"; // login.jsp in /WEB-INF/views/
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        User user = userService.authenticate(email, password);

        if (user != null) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/account"; // Redirect to account page after login
        } else {
            return "login"; // Redirect back to login with error flag
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // register.jsp in /WEB-INF/views/
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("errorMessage", "Passwords do not match.");
            return "register";
        }

        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("errorMessage", "Email already registered.");
            return "register";
        }

        if (userService.registerUser(user)) {
            return "redirect:/"; // Redirect to home
        } else {
            model.addAttribute("errorMessage", "Registration failed. Try again.");
            return "register";
        }
    }
}
