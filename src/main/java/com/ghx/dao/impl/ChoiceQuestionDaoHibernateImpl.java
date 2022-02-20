package com.ghx.dao.impl;

import com.ghx.dao.AbstractHibernateDAO;
import com.ghx.dao.ChoiceQuestionDao;
import com.ghx.entity.ChoiceQuestion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChoiceQuestionDaoHibernateImpl extends AbstractHibernateDAO<ChoiceQuestion> implements ChoiceQuestionDao {
    public ChoiceQuestionDaoHibernateImpl() {
        setClazz(ChoiceQuestion.class);
    }

    @Override
    public int addQuestion(String category, String description, String choiceA, String choiceB, String choiceC, String choiceD, String answer) {
        ChoiceQuestion choiceQuestion = new ChoiceQuestion();
        choiceQuestion.setCategory(category);
        choiceQuestion.setChoiceA(choiceA);
        choiceQuestion.setChoiceB(choiceB);
        choiceQuestion.setChoiceC(choiceC);
        choiceQuestion.setChoiceD(choiceD);
        choiceQuestion.setDescription(description);
        choiceQuestion.setAnswer(answer);
        this.getCurrentSession().save(choiceQuestion);
        return 0;
    }

    @Override
    public ChoiceQuestion editQuestion(long id, String category, String description, String choiceA, String choiceB, String choiceC, String choiceD, String answer) {
        ChoiceQuestion choiceQuestion = sessionFactory.getCurrentSession().find(ChoiceQuestion.class, id);
        choiceQuestion.setCategory(category);
        choiceQuestion.setChoiceA(choiceA);
        choiceQuestion.setChoiceB(choiceB);
        choiceQuestion.setChoiceC(choiceC);
        choiceQuestion.setChoiceD(choiceD);
        choiceQuestion.setDescription(description);
        choiceQuestion.setAnswer(answer);
        return choiceQuestion;
    }

    @Override
    public List<ChoiceQuestion> getAllChoiceQuestions() {
        return findAll();
    }

    @Override
    public List<ChoiceQuestion> getChoiceQuestions(String category) {
        return null;
    }

    @Override
    public List<ChoiceQuestion> findChoiceQuestions(long quizId) {
        return null;
    }

    @Override
    public ChoiceQuestion findChoiceQuestion(long id) {
        return findById(id);
    }
}
