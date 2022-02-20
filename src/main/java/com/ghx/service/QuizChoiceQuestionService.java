package com.ghx.service;

import com.ghx.entity.QuizChoiceQuestion;

import java.util.List;

public interface QuizChoiceQuestionService {
    public List<QuizChoiceQuestion> findQuizChoiceQuestionsByQuizId(long quizId);

    public List<QuizChoiceQuestion> findQuizChoiceQuestionsByQuestionId(long questionId);
}
