package com.ghx.service.impl;

import com.ghx.dao.ChoiceQuestionDao;
import com.ghx.dao.QuizDao;
import com.ghx.entity.ChoiceQuestion;
import com.ghx.entity.Quiz;
import com.ghx.entity.User;
import com.ghx.service.QuizService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Data
@NoArgsConstructor
public class QuizServiceImpl implements QuizService {
    @Autowired
    @Qualifier("quizDaoJDBCImpl")
    private QuizDao quizDaoJDBC;

    @Autowired
    @Qualifier("choiceQuestionDaoJDBCImpl")
    private ChoiceQuestionDao choiceQuestionDaoJDBC;

    @Override
    public Quiz createQuiz(long userId, String username, String category) {
        int countQuiz = quizDaoJDBC.countQuiz(userId, category) + 1;
        String quizName = username + "-" + category + "-" + countQuiz;
        quizDaoJDBC.createQuiz(userId, quizName, category, new Date());
        Quiz quiz = quizDaoJDBC.findLastQuiz(userId, category);
        return quiz;
    }

    @Override
    public List<ChoiceQuestion> generateQuestions(long quizId, String category) {
        List<ChoiceQuestion> choiceQuestions = choiceQuestionDaoJDBC.getChoiceQuestions(category);
        Collections.shuffle(choiceQuestions);
        for (int i = 0; i < 10; i++) {
            quizDaoJDBC.addQuizQuestion(quizId, choiceQuestions.get(i).getId());
        }
        return choiceQuestions.subList(0, 10);
    }

    @Override
    public int updateQuizQuestion(long quizId, long choiceQuestionId, String answer) {
        return quizDaoJDBC.updateQuizQuestion(quizId, choiceQuestionId, answer);
    }

    @Override
    public int updateQuiz(long quizId, Date finishTime) {
        return quizDaoJDBC.updateQuiz(quizId, finishTime);
    }

    @Override
    public int updateQuiz(long quizId, int score) {
        return quizDaoJDBC.updateQuiz(quizId, score);
    }

    @Override
    public Quiz findQuizById(long id) {
        return quizDaoJDBC.findQuizById(id);
    }

    @Override
    public List<Quiz> findQuiz(long userId) {
        return quizDaoJDBC.findQuiz(userId);
    }

    @Override
    @Transactional
    public List<Quiz> getAllQuizzes() {
        return quizDaoJDBC.getAllQuizzes();
    }

    @Override
    @Transactional
    public List<Quiz> findQuiz(User user, String category) {
        return quizDaoJDBC.findQuiz(user, category);
    }
}
