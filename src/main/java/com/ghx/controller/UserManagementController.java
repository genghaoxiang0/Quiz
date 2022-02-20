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
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@NoArgsConstructor
@Controller
@RequestMapping("/admin")
public class UserManagementController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @GetMapping("/user-management")
    public String getUserManagement(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-management";
    }

    @PostMapping("/suspend-user")
    @ResponseBody
    public User suspendUser(long userId) {
        return userService.suspendUser(userId);
    }

    @PostMapping("/activate-user")
    @ResponseBody
    public User activateUser(long userId) {
        return userService.activateUser(userId);
    }
}
