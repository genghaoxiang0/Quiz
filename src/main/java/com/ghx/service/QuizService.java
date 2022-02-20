package com.ghx.service;

import com.ghx.entity.ChoiceQuestion;
import com.ghx.entity.Quiz;
import com.ghx.entity.User;

import java.util.Date;
import java.util.List;

public interface QuizService {
    public Quiz createQuiz(long userId, String username, String category);

    public List<ChoiceQuestion> generateQuestions(long quizId, String category);

    public int updateQuizQuestion(long quizId, long choiceQuestionId, String answer);

    public int updateQuiz(long quizId, Date finishTime);

    public int updateQuiz(long quizId, int score);

    public Quiz findQuizById(long id);

    public List<Quiz> findQuiz(long userId);

    public List<Quiz> getAllQuizzes();

    public List<Quiz> findQuiz(User user, String category);
}
