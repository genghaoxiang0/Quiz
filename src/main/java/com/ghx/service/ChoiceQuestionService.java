package com.ghx.service;

import com.ghx.entity.ChoiceQuestion;

import java.util.List;

public interface ChoiceQuestionService {
    public int addQuestion(String category, String description, String choiceA, String choiceB, String choiceC, String choiceD, String answer);

    public ChoiceQuestion editQuestion(long id, String category, String description, String choiceA, String choiceB, String choiceC, String choiceD, String answer);

    public List<ChoiceQuestion> getAllChoiceQuestions();

    public List<ChoiceQuestion> findChoiceQuestions(long quizId);

    public ChoiceQuestion findChoiceQuestion(long id);
}
