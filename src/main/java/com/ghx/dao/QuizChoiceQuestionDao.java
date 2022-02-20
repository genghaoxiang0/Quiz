package com.ghx.dao;

import com.ghx.entity.QuizChoiceQuestion;

import java.util.List;

public interface QuizChoiceQuestionDao {
    public List<QuizChoiceQuestion> findQuizChoiceQuestionsByQuizId(long quizId);

    public List<QuizChoiceQuestion> findQuizChoiceQuestionsByQuestionId(long questionId);
}
