package com.ghx.dao;

import com.ghx.entity.Quiz;
import com.ghx.entity.User;

import java.util.Date;
import java.util.List;

public interface QuizDao {
    public int createQuiz(long userId, String name, String category, Date startTime);

    public Quiz findLastQuiz(long userId, String category);

    public List<Quiz> findQuiz(User user, String category);

    public List<Quiz> findQuiz(long userId);

    public Quiz findQuizById(long id);

    public List<Quiz> getAllQuizzes();

    public int addQuizQuestion(long quizId, long choiceQuestionId);

    public int updateQuizQuestion(long quizId, long choiceQuestionId, String answer);

    public int updateQuiz(long quizId, Date finishTime);

    public int updateQuiz(long quizId, int score);

    public int countQuiz(long userId, String category);
}
