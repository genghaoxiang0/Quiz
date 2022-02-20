package com.ghx.controller;

import com.ghx.entity.Category;
import com.ghx.entity.ChoiceQuestion;
import com.ghx.entity.Quiz;
import com.ghx.entity.QuizChoiceQuestion;
import com.ghx.service.CategoryService;
import com.ghx.service.ChoiceQuestionService;
import com.ghx.service.QuizChoiceQuestionService;
import com.ghx.service.QuizService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Data
@NoArgsConstructor
@Controller
@RequestMapping("/admin")
public class ChoiceQuestionController {
    @Autowired
    @Qualifier("choiceQuestionServiceImpl")
    private ChoiceQuestionService choiceQuestionService;

    @Autowired
    @Qualifier("quizChoiceQuestionServiceImpl")
    private QuizChoiceQuestionService quizChoiceQuestionService;

    @Autowired
    @Qualifier("categoryServiceImpl")
    private CategoryService categoryService;

    @Autowired
    @Qualifier("quizServiceImpl")
    private QuizService quizService;

    @Value("${pageSize}")
    int pageSize;

    @GetMapping("/choice-question")
    public String getChoiceQuestion(Model model) {
        return "redirect:/admin/choice-question/1";
    }

    @GetMapping("/choice-question/{pageNo}")
    public String getChoiceQuestion(@PathVariable Integer pageNo, Model model, HttpServletRequest request) {
        List<ChoiceQuestion> choiceQuestions = choiceQuestionService.getAllChoiceQuestions();
        int totalPages = choiceQuestions.size() / pageSize;
        if (choiceQuestions.size() % pageSize > 0) {
            totalPages++;
        }
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNo);
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        if (pageNo != 1 && (pageNo-1) * pageSize >= choiceQuestions.size()) {
            return "redirect:/admin/choice-question/1";
        } else if (pageNo * pageSize < choiceQuestions.size()) {
            model.addAttribute("choiceQuestions", choiceQuestions.subList((pageNo-1) * pageSize, pageNo * pageSize));
        } else {
            model.addAttribute("choiceQuestions", choiceQuestions.subList((pageNo-1) * pageSize, choiceQuestions.size()));
        }
        Map<String, Object> map= (Map<String, Object>) RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            model.addAttribute("success", map.get("success"));
            model.addAttribute("error", map.get("error"));
        }
        return "choice-question";
    }

    @PostMapping("/add-choice-question")
    public String addQuestion(RedirectAttributes redirectAttributes, String category, String description, String choiceA, String choiceB,String choiceC, String choiceD, String answer) {
        choiceQuestionService.addQuestion(category, description, choiceA, choiceB, choiceC, choiceD, answer);
        redirectAttributes.addFlashAttribute("success", "Question added successfully");
        return "redirect:/admin/choice-question/1";
    }

    @PostMapping("/upload-choice-question")
    public String uploadQuestion(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file == null || !file.getContentType().equals("application/vnd.ms-excel")) {
            redirectAttributes.addFlashAttribute("error", "Please upload a csv file");
            return "redirect:/admin/choice-question/1";
        }
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;
        try {
            inputStream = file.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            br = new BufferedReader(inputStreamReader);
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                choiceQuestionService.addQuestion(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Template format error");
            return "redirect:/admin/choice-question/1";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (br !=null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        redirectAttributes.addFlashAttribute("success", "Questions added successfully");
        return "redirect:/admin/choice-question/1";
    }

    @PostMapping("/edit-choice-question")
    @ResponseBody
    public ChoiceQuestion editQuestion(long id, String category, String description, String choiceA, String choiceB, String choiceC, String choiceD, String answer) {
        ChoiceQuestion originalChoiceQuestion = choiceQuestionService.findChoiceQuestion(id);
        if (!originalChoiceQuestion.getAnswer().equals(answer)) {
            List<QuizChoiceQuestion> quizChoiceQuestions = quizChoiceQuestionService.findQuizChoiceQuestionsByQuestionId(id);
            for (QuizChoiceQuestion quizChoiceQuestion: quizChoiceQuestions) {
                if (quizChoiceQuestion.getAnswer().equals(originalChoiceQuestion.getAnswer())) {
                    Quiz quiz = quizService.findQuizById(quizChoiceQuestion.getQuizId());
                    quizService.updateQuiz(quiz.getId(), quiz.getScore()-10);
                }
                if (quizChoiceQuestion.getAnswer().equals(answer)) {
                    Quiz quiz = quizService.findQuizById(quizChoiceQuestion.getQuizId());
                    quizService.updateQuiz(quiz.getId(), quiz.getScore()+10);
                }
            }
        }
        ChoiceQuestion choiceQuestion = choiceQuestionService.editQuestion(id, category, description, choiceA, choiceB, choiceC, choiceD, answer);
        return choiceQuestion;
    }
}
