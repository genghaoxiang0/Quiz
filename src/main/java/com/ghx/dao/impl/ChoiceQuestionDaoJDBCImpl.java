package com.ghx.dao.impl;

import com.ghx.dao.ChoiceQuestionDao;
import com.ghx.entity.ChoiceQuestion;
import com.ghx.rowMapper.ChoiceQuestionRowMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
@NoArgsConstructor
@Repository
public class ChoiceQuestionDaoJDBCImpl implements ChoiceQuestionDao {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addQuestion(String category, String description, String choiceA, String choiceB, String choiceC, String choiceD, String answer) {
        return 0;
    }

    @Override
    public ChoiceQuestion editQuestion(long id, String category, String description, String choiceA, String choiceB, String choiceC, String choiceD, String answer) {
        return null;
    }

    @Override
    public List<ChoiceQuestion> getAllChoiceQuestions() {
        return null;
    }

    @Override
    public List<ChoiceQuestion> getChoiceQuestions(String category) {
        String sql = "select * from choice_question where category = ?";
        return jdbcTemplate.query(sql, new Object[]{category}, new ChoiceQuestionRowMapper());
    }

    @Override
    public List<ChoiceQuestion> findChoiceQuestions(long quizId) {
        String sql = "select a.* from choice_question as a, quiz_choice_question as q where a.id = q.choice_question_id and quiz_id = ?";
        return jdbcTemplate.query(sql, new ChoiceQuestionRowMapper(), quizId);
    }

    @Override
    public ChoiceQuestion findChoiceQuestion(long id) {
        return null;
    }
}
