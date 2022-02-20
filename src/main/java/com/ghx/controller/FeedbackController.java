package com.ghx.controller;

import com.ghx.entity.Feedback;
import com.ghx.entity.User;
import com.ghx.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    @Qualifier("feedbackServiceImpl")
    private FeedbackService feedbackService;

    @GetMapping("")
    public String getFeedback() {
        return "feedback";
    }

    @PostMapping("")
    public String postFeedback(HttpSession session, int rate, String comment, Model model) {
        User user = (User) session.getAttribute("user");
        Feedback feedback = feedbackService.findFeedback(user.getId());
        if (feedback == null) {
            feedbackService.addFeedback(user.getId(), rate, comment);
        } else {
            feedbackService.updateFeedback(user.getId(), rate, comment);
        }
        model.addAttribute("success", "Thank you for your feedback.");
        return "feedback";
    }
}
