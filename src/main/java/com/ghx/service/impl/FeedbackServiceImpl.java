package com.ghx.service.impl;

import com.ghx.dao.FeedbackDao;
import com.ghx.entity.Feedback;
import com.ghx.service.FeedbackService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@NoArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    @Qualifier("feedbackDaoJDBCImpl")
    private FeedbackDao feedbackDaoJDBC;

    @Override
    public int addFeedback(long userId, int rate, String comment) {
        return feedbackDaoJDBC.addFeedback(userId, rate, comment);
    }

    @Override
    public int updateFeedback(long userId, int rate, String comment) {
        return feedbackDaoJDBC.updateFeedback(userId, rate, comment);
    }

    @Override
    public Feedback findFeedback(long userId) {
        return feedbackDaoJDBC.findFeedback(userId);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackDaoJDBC.getAllFeedback();
    }

    @Override
    public double getAvgRate() {
        return feedbackDaoJDBC.getAvgRate();
    }

    @Override
    public int countRate(int rate) {
        return feedbackDaoJDBC.countRate(rate);
    }
}
