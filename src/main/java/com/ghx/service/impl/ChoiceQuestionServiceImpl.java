package com.ghx.service.impl;

import com.ghx.dao.ChoiceQuestionDao;
import com.ghx.entity.ChoiceQuestion;
import com.ghx.service.ChoiceQuestionService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@NoArgsConstructor
@Service
public class ChoiceQuestionServiceImpl implements ChoiceQuestionService {
    @Autowired
    @Qualifier("choiceQuestionDaoHibernateImpl")
    private ChoiceQuestionDao choiceQuestionDaoHibernate;

    @Autowired
    @Qualifier("choiceQuestionDaoJDBCImpl")
    private ChoiceQuestionDao choiceQuestionDaoJDBC;

    @Override
    @Transactional
    public int addQuestion(String category, String description, String choiceA, String choiceB, String choiceC, String choiceD, String answer) {
        return choiceQuestionDaoHibernate.addQuestion(category, description, choiceA, choiceB, choiceC, choiceD, answer);
    }

    @Override
    @Transactional
    public ChoiceQuestion editQuestion(long id, String category, String description, String choiceA, String choiceB, String choiceC, String choiceD, String answer) {
        return choiceQuestionDaoHibernate.editQuestion(id, category, description, choiceA, choiceB, choiceC, choiceD, answer);
    }

    @Override
    @Transactional
    public List<ChoiceQuestion> getAllChoiceQuestions() {
        return choiceQuestionDaoHibernate.getAllChoiceQuestions();
    }

    @Override
    public List<ChoiceQuestion> findChoiceQuestions(long quizId) {
        return choiceQuestionDaoJDBC.findChoiceQuestions(quizId);
    }

    @Override
    @Transactional
    public ChoiceQuestion findChoiceQuestion(long id) {
        return choiceQuestionDaoHibernate.findChoiceQuestion(id);
    }
}
