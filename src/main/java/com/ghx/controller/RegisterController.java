package com.ghx.controller;

import com.ghx.entity.User;
import com.ghx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @GetMapping("")
    public String getRegister() {
        return "register";
    }

    @PostMapping("")
    public String postRegister(Model model, String name, String email, String username, String password, String confirmPassword, HttpServletRequest request) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Two passwords must be the same.");
            return "register";
        }
        User user = userService.register(name, email, username, password);
        if (user != null) {
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("user", user);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Username already exist.");
            return "register";
        }
    }
}
