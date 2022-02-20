package com.ghx.controller;

import com.ghx.entity.Feedback;
import com.ghx.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin/feedback")
public class AdminFeedbackController {
    @Autowired
    @Qualifier("feedbackServiceImpl")
    private FeedbackService feedbackService;

    @GetMapping("")
    public String getFeedback(Model model) {
        List<Feedback> feedbacks = feedbackService.getAllFeedback();
        Collections.reverse(feedbacks);
        model.addAttribute("feedbacks", feedbacks);
        double avgRate = feedbackService.getAvgRate();
        int rate1 = feedbackService.countRate(1);
        int rate2 = feedbackService.countRate(2);
        int rate3 = feedbackService.countRate(3);
        int rate4 = feedbackService.countRate(4);
        int rate5 = feedbackService.countRate(5);
        model.addAttribute("avgRate", avgRate);
        model.addAttribute("rate1", rate1);
        model.addAttribute("rate2", rate2);
        model.addAttribute("rate3", rate3);
        model.addAttribute("rate4", rate4);
        model.addAttribute("rate5", rate5);
        return "admin-feedback";
    }
}
