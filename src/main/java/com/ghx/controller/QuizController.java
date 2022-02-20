package com.ghx.controller;

import com.ghx.entity.ChoiceQuestion;
import com.ghx.entity.Quiz;
import com.ghx.entity.QuizChoiceQuestion;
import com.ghx.entity.User;
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

import javax.servlet.http.HttpSession;
import java.util.*;

@Data
@NoArgsConstructor
@Controller
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    @Qualifier("quizServiceImpl")
    private QuizService quizService;

    @Autowired
    @Qualifier("choiceQuestionServiceImpl")
    private ChoiceQuestionService choiceQuestionService;

    @Autowired
    @Qualifier("quizChoiceQuestionServiceImpl")
    private QuizChoiceQuestionService quizChoiceQuestionService;

    @Value("${quizTimeLimit}")
    int quizTimeLimit;

    @GetMapping("/begin/{category}")
    public String getBeginQuiz(Model model, @PathVariable String category) {
        model.addAttribute("category", category);
        return "begin-quiz";
    }

    @GetMapping("/{category}")
    public String getQuiz(@PathVariable String category, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Quiz quiz = quizService.createQuiz(user.getId(), user.getUsername(), category);
        session.setAttribute("quiz", quiz);
        List<ChoiceQuestion> choiceQuestions = quizService.generateQuestions(quiz.getId(), category);
        session.setAttribute("choiceQuestions", choiceQuestions);
        List<String> userAnswer = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userAnswer.add("");
        }
        session.setAttribute("userAnswer", userAnswer);
        session.setAttribute("quizTimeLimit", quizTimeLimit);
        session.setAttribute("marks", new HashSet<Integer>());
        return "redirect:/quiz/question/1";
    }

    @GetMapping("/question/{questionNum}")
    public String getQuizQuestion(Model model, @PathVariable long questionNum) {
        model.addAttribute("questionNum", questionNum);
        return "quiz";
    }

    @GetMapping("/submit")
    public String getQuizSubmit(HttpSession session) {
        List<String> userAnswer = (List<String>) session.getAttribute("userAnswer");
        Quiz quiz = (Quiz) session.getAttribute("quiz");
        List<ChoiceQuestion> choiceQuestions = (List<ChoiceQuestion>) session.getAttribute("choiceQuestions");
        int score = 0;
        for (int i = 0; i < choiceQuestions.size(); i++) {
            quizService.updateQuizQuestion(quiz.getId(), choiceQuestions.get(i).getId(), userAnswer.get(i));
            if (choiceQuestions.get(i).getAnswer().equals(userAnswer.get(i))) {
                score += 10;
            }
        }
        quizService.updateQuiz(quiz.getId(), new Date());
        quizService.updateQuiz(quiz.getId(), score);
        session.removeAttribute("quiz");
        return "redirect:/quiz/result/"+quiz.getId();
    }

    @PostMapping("/update-answer")
    public void postUpdateAnswer(int questionNum, String value, HttpSession session) {
        List<String> userAnswer = (List<String>) session.getAttribute("userAnswer");
        userAnswer.set(questionNum-1, value);
    }


    @GetMapping("result/{id}")
    public String getQuizResultDetail(Model model, @PathVariable long id, HttpSession session) {
        Quiz quiz = quizService.findQuizById(id);
        User user = (User) session.getAttribute("user");
        if (quiz.getUserId() != user.getId()) {
            return "redirect:/quiz/results";
        }
        model.addAttribute("quiz", quiz);
        List<ChoiceQuestion> choiceQuestions = choiceQuestionService.findChoiceQuestions(id);
        model.addAttribute("choiceQuestions", choiceQuestions);
        List<QuizChoiceQuestion> quizChoiceQuestions = quizChoiceQuestionService.findQuizChoiceQuestionsByQuizId(id);
        model.addAttribute("quizChoiceQuestions", quizChoiceQuestions);
        return "quiz-result-detail";
    }

    @GetMapping("/results")
    public String getQuizResult(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Quiz> quizzes = quizService.findQuiz(user.getId());
        model.addAttribute("quizzes", quizzes);
        return "quiz-result";
    }

    @PostMapping("check-finish")
    @ResponseBody
    public boolean checkFinished(HttpSession session) {
        List<String> userAnswer = (List<String>) session.getAttribute("userAnswer");
        for (int i = 0; i < userAnswer.size(); i++) {
            if (userAnswer.get(i).length() == 0) {
                return false;
            }
        }
        return true;
    }

    @PostMapping("mark-question")
    public void postMarkQuestion(int questionNum, HttpSession session) {
        Set<Integer> marks = (Set<Integer>) session.getAttribute("marks");
        marks.add(questionNum);
    }

    @PostMapping("unmark-question")
    public void postUnmarkQuestion(int questionNum, HttpSession session) {
        Set<Integer> marks = (Set<Integer>) session.getAttribute("marks");
        marks.remove(questionNum);
    }
}
