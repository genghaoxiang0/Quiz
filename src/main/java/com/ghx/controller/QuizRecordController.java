package com.ghx.controller;

import com.ghx.entity.*;
import com.ghx.service.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Controller
@RequestMapping("/admin")
public class QuizRecordController {
    @Autowired
    @Qualifier("quizServiceImpl")
    private QuizService quizService;

    @Autowired
    @Qualifier("categoryServiceImpl")
    private CategoryService categoryService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("choiceQuestionServiceImpl")
    private ChoiceQuestionService choiceQuestionService;

    @Autowired
    @Qualifier("quizChoiceQuestionServiceImpl")
    private QuizChoiceQuestionService quizChoiceQuestionService;

    @Value("${pageSize}")
    int pageSize;

    @GetMapping("/quiz/{pageNo}")
    public String getQuizRecord(Model model, @PathVariable Integer pageNo) {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        quizzes.sort(new Comparator<Quiz>() {
            @Override
            public int compare(Quiz o1, Quiz o2) {
                if (o1.getStartTime().after(o2.getStartTime())) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        int totalPages = quizzes.size() / pageSize;
        if (quizzes.size() % pageSize > 0) {
            totalPages++;
        }
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNo);
        if (pageNo != 1 && (pageNo-1) * pageSize >= quizzes.size()) {
            return "redirect:/admin/quiz/1";
        } else if (pageNo * pageSize < quizzes.size()) {
            model.addAttribute("quizzes", quizzes.subList((pageNo-1) * pageSize, pageNo * pageSize));
        } else {
            model.addAttribute("quizzes", quizzes.subList((pageNo-1) * pageSize, quizzes.size()));
        }
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        Map<Long, String> userMap = new HashMap<>();
        for (int i = 0; i < users.size(); i++) {
            userMap.put(users.get(i).getId(), users.get(i).getName());
        }
        model.addAttribute("userMap", userMap);
        return "quiz-record";
    }

    @PostMapping("/quiz/{pageNo}")
    public String postQuizRecord(Model model, @PathVariable Integer pageNo, String user, String categoryFilter, String sort) {
        User u = userService.findUser(user);
        List<Quiz> quizzes = quizService.findQuiz(u, categoryFilter);
        model.addAttribute("sort", sort);
        Map<Long, String> userMap = new HashMap<>();
        List<User> users = userService.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            userMap.put(users.get(i).getId(), users.get(i).getName());
        }
        model.addAttribute("userMap", userMap);
        if (sort.equals("category")) {
            quizzes.sort(new Comparator<Quiz>() {
                @Override
                public int compare(Quiz o1, Quiz o2) {
                    return o1.getCategory().compareTo(o2.getCategory());
                }
            });
        } else if (sort.equals("user")) {
            quizzes.sort(new Comparator<Quiz>() {
                @Override
                public int compare(Quiz o1, Quiz o2) {
                    return userMap.get(o1.getUserId()).compareTo(userMap.get(o2.getUserId()));
                }
            });
        } else {
            quizzes.sort(new Comparator<Quiz>() {
                @Override
                public int compare(Quiz o1, Quiz o2) {
                    if (o1.getStartTime().after(o2.getStartTime())) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });
        }
        model.addAttribute("name", user);
        model.addAttribute("categoryFilter", categoryFilter);
        int totalPages = quizzes.size() / pageSize;
        if (quizzes.size() % pageSize > 0) {
            totalPages++;
        }
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNo);
        if (pageNo != 1 && (pageNo-1) * pageSize >= quizzes.size()) {
            return "redirect:/admin/quiz/1";
        } else if (pageNo * pageSize < quizzes.size()) {
            model.addAttribute("quizzes", quizzes.subList((pageNo-1) * pageSize, pageNo * pageSize));
        } else {
            model.addAttribute("quizzes", quizzes.subList((pageNo-1) * pageSize, quizzes.size()));
        }
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("users", users);
        return "quiz-record";
    }

    @GetMapping("/quiz/result/{id}")
    public String getQuizResultDetail(Model model, @PathVariable long id) {
        Quiz quiz = quizService.findQuizById(id);
        model.addAttribute("quiz", quiz);
        List<ChoiceQuestion> choiceQuestions = choiceQuestionService.findChoiceQuestions(id);
        model.addAttribute("choiceQuestions", choiceQuestions);
        List<QuizChoiceQuestion> quizChoiceQuestions = quizChoiceQuestionService.findQuizChoiceQuestionsByQuizId(id);
        model.addAttribute("quizChoiceQuestions", quizChoiceQuestions);
        return "admin-quiz-result-detail";
    }
}
