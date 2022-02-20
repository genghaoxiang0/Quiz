package com.ghx.dao.impl;

import com.ghx.dao.QuizDao;
import com.ghx.entity.Quiz;
import com.ghx.entity.User;
import com.ghx.rowMapper.QuizRowMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Repository
public class QuizDaoJDBCImpl implements QuizDao {
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
    public int createQuiz(long userId, String name, String category, Date startTime) {
        String sql = "insert into quiz (name, category, user_id, start_time) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, name, category, userId, startTime);
    }

    @Override
    public Quiz findLastQuiz(long userId, String category) {
        String sql = "select * from quiz where category = ? and user_id = ? order by start_time desc limit 1";
        return jdbcTemplate.queryForObject(sql, new Object[]{category, userId}, new QuizRowMapper());
    }

    @Override
    public List<Quiz> findQuiz(User user, String category) {
        String sql = "select * from quiz";
        if (!category.equals("Any") && user != null) {
            sql = "select * from quiz where category = ? and user_id = ?";
            return jdbcTemplate.query(sql, new QuizRowMapper(), category, user.getId());
        } else if (!category.equals("Any")) {
            sql = "select * from quiz where category = ?";
            return jdbcTemplate.query(sql, new QuizRowMapper(), category);
        } else if (user != null) {
            sql = "select * from quiz where user_id = ?";
            return jdbcTemplate.query(sql, new QuizRowMapper(), user.getId());
        }
        return jdbcTemplate.query(sql, new QuizRowMapper());
    }

    @Override
    public List<Quiz> findQuiz(long userId) {
        String sql = "select * from quiz where user_id = ? order by start_time desc";
        return jdbcTemplate.query(sql, new QuizRowMapper(), userId);
    }

    @Override
    public Quiz findQuizById(long id) {
        String sql = "select * from quiz where id = ?";
        return jdbcTemplate.queryForObject(sql, new QuizRowMapper(), id);
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        String sql = "select * from quiz";
        return jdbcTemplate.query(sql, new QuizRowMapper());
    }

    @Override
    public int addQuizQuestion(long quizId, long choiceQuestionId) {
        String sql = "insert into quiz_choice_question (quiz_id, choice_question_id) values (?, ?)";
        return jdbcTemplate.update(sql, quizId, choiceQuestionId);
    }

    @Override
    public int updateQuizQuestion(long quizId, long choiceQuestionId, String answer) {
        String sql = "update quiz_choice_question set answer = ? where quiz_id = ? and choice_question_id = ?";
        return jdbcTemplate.update(sql, answer, quizId, choiceQuestionId);
    }

    @Override
    public int updateQuiz(long quizId, Date finishTime) {
        String sql = "update quiz set finish_time = ? where id = ?";
        return jdbcTemplate.update(sql, finishTime, quizId);
    }

    @Override
    public int updateQuiz(long quizId, int score) {
        String sql = "update quiz set score = ? where id = ?";
        return jdbcTemplate.update(sql, score, quizId);
    }

    @Override
    public int countQuiz(long userId, String category) {
        String sql = "select count(*) from quiz where category = ? and user_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{category, userId});
    }

}
