package com.ghx.dao.impl;

import com.ghx.dao.QuizChoiceQuestionDao;
import com.ghx.entity.QuizChoiceQuestion;
import com.ghx.rowMapper.QuizChoiceQuestionRowMapper;
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
public class QuizChoiceQuestionDaoJDBCImpl implements QuizChoiceQuestionDao {
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
    public List<QuizChoiceQuestion> findQuizChoiceQuestionsByQuizId(long quizId) {
        String sql = "select * from quiz_choice_question where quiz_id = ?";
        return jdbcTemplate.query(sql, new QuizChoiceQuestionRowMapper(), quizId);
    }

    @Override
    public List<QuizChoiceQuestion> findQuizChoiceQuestionsByQuestionId(long questionId) {
        String sql = "select * from quiz_choice_question where choice_question_id = ?";
        return jdbcTemplate.query(sql, new QuizChoiceQuestionRowMapper(), questionId);
    }
}
