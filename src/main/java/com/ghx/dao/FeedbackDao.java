package com.ghx.dao;

import com.ghx.entity.Feedback;

import java.util.List;

public interface FeedbackDao {
    public int addFeedback(long userId, int rate, String comment);

    public int updateFeedback(long userId, int rate, String comment);

    public Feedback findFeedback(long userId);

    public List<Feedback> getAllFeedback();

    public double getAvgRate();

    public int countRate(int rate);
}
