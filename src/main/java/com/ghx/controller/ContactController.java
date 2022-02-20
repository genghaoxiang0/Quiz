package com.ghx.controller;

import com.ghx.entity.User;
import com.ghx.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    @Qualifier("messageServiceImpl")
    private MessageService messageService;

    @GetMapping("")
    public String getContact() {
        return "contact";
    }

    @PostMapping("")
    public String postContact(HttpSession session, Model model, String title, String content) {
        User user = (User) session.getAttribute("user");
        messageService.addMessage(user, title, content);
        model.addAttribute("success", "We have received your message");
        return "contact";
    }
}
