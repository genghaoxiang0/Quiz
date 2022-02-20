package com.ghx.dao;

import com.ghx.entity.ChoiceQuestion;

import java.util.List;

public interface ChoiceQuestionDao {
    public int addQuestion(String category, String description, String choiceA, String choiceB, String choiceC, String choiceD, String answer);

    public ChoiceQuestion editQuestion(long id, String category, String description, String choiceA, String choiceB, String choiceC, String choiceD, String answer);

    public List<ChoiceQuestion> getAllChoiceQuestions();

    public List<ChoiceQuestion> getChoiceQuestions(String category);

    public List<ChoiceQuestion> findChoiceQuestions(long quizId);

    public ChoiceQuestion findChoiceQuestion(long id);
}
