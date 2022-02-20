package com.ghx.service.impl;

import com.ghx.dao.QuizChoiceQuestionDao;
import com.ghx.entity.QuizChoiceQuestion;
import com.ghx.service.QuizChoiceQuestionService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@NoArgsConstructor
@Service
public class QuizChoiceQuestionServiceImpl implements QuizChoiceQuestionService {
    @Autowired
    @Qualifier("quizChoiceQuestionDaoJDBCImpl")
    private QuizChoiceQuestionDao quizChoiceQuestionDaoJDBC;

    @Override
    public List<QuizChoiceQuestion> findQuizChoiceQuestionsByQuizId(long quizId) {
        return quizChoiceQuestionDaoJDBC.findQuizChoiceQuestionsByQuizId(quizId);
    }

    @Override
    public List<QuizChoiceQuestion> findQuizChoiceQuestionsByQuestionId(long questionId) {
        return quizChoiceQuestionDaoJDBC.findQuizChoiceQuestionsByQuestionId(questionId);
    }
}
