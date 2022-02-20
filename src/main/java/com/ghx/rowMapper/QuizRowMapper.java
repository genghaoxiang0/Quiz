package com.ghx.rowMapper;

import com.ghx.entity.Quiz;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizRowMapper implements RowMapper<Quiz> {
    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz quiz = new Quiz();
        quiz.setId(rs.getLong("id"));
        quiz.setName(rs.getString("name"));
        quiz.setCategory(rs.getString("category"));
        quiz.setUserId(rs.getLong("user_id"));
        quiz.setStartTime(rs.getTimestamp("start_time"));
        quiz.setFinishTime(rs.getTimestamp("finish_time"));
        quiz.setScore(rs.getInt("score"));
        return quiz;
    }
}
