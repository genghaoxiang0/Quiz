package com.ghx.controller;

import com.ghx.entity.Message;
import com.ghx.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminMessageController {
    @Autowired
    @Qualifier("messageServiceImpl")
    private MessageService messageService;

    @GetMapping("/inbox")
    public String getInbox(Model model) {
        List<Message> messages = messageService.getAllMessages();
        Collections.reverse(messages);
        model.addAttribute("messages", messages);
        int unread = 0;
        for (Message message: messages) {
            if (message.isUnread()) {
                unread++;
            }
        }
        model.addAttribute("unread", unread);
        return "inbox";
    }

    @GetMapping("/message/{id}")
    public String getMessage(@PathVariable Long id, Model model) {
        Message message = messageService.updateMessage(id, false);
        model.addAttribute("message", message);
        return "message";
    }
}
