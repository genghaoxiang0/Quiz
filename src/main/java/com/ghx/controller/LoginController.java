package com.ghx.controller;

import com.ghx.entity.User;
import com.ghx.service.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Data
@NoArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @GetMapping("")
    public String getLogin() {
        return "login";
    }

    @PostMapping("")
    public String postLogin(Model model, String username, String password, HttpServletRequest request) {
        User user = userService.validateLogin(username, password);
        if (user != null) {
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("user", user);
            if (!user.isActive()) {
                model.addAttribute("error", "Account is suspended.");
                return "login";
            }
            if (user.isAdmin()) {
                return "redirect:/admin/user-management";
            } else {
                return "redirect:/";
            }
        } else {
            model.addAttribute("error", "Username or password error.");
            return "login";
        }
    }
}
