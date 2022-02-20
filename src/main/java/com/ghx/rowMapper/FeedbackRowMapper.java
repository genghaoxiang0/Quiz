package com.ghx.rowMapper;

import com.ghx.entity.Feedback;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackRowMapper implements RowMapper<Feedback> {
    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(rs.getLong("id"));
        feedback.setUserId(rs.getLong("user_id"));
        feedback.setRate(rs.getInt("rate"));
        feedback.setComment(rs.getString("comment"));
        return feedback;
    }
}
