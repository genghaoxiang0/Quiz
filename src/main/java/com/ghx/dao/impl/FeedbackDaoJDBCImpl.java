package com.ghx.dao.impl;

import com.ghx.dao.FeedbackDao;
import com.ghx.entity.Feedback;
import com.ghx.rowMapper.FeedbackRowMapper;
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
public class FeedbackDaoJDBCImpl implements FeedbackDao {
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
    public int addFeedback(long userId, int rate, String comment) {
        String sql = "insert into feedback (user_id, rate, comment) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, userId, rate, comment);
    }

    @Override
    public int updateFeedback(long userId, int rate, String comment) {
        String sql = "update feedback set rate = ?, comment = ? where user_id = ?";
        return jdbcTemplate.update(sql, rate, comment, userId);
    }

    @Override
    public Feedback findFeedback(long userId) {
        String sql = "select * from feedback where user_id = ?";
        List<Feedback> feedbacks = jdbcTemplate.query(sql, new FeedbackRowMapper(), userId);
        if (feedbacks.size() == 0) {
            return null;
        } else {
            return feedbacks.get(0);
        }
    }

    @Override
    public List<Feedback> getAllFeedback() {
        String sql = "select * from feedback";
        return jdbcTemplate.query(sql, new FeedbackRowMapper());
    }

    @Override
    public double getAvgRate() {
        String sql = "select avg(feedback.rate) from feedback";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    @Override
    public int countRate(int rate) {
        String sql = "select count(*) from feedback where rate=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, rate);
    }
}
