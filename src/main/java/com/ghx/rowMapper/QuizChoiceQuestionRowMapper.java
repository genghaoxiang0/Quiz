package com.ghx.rowMapper;

import com.ghx.entity.QuizChoiceQuestion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizChoiceQuestionRowMapper implements RowMapper<QuizChoiceQuestion> {
    @Override
    public QuizChoiceQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizChoiceQuestion quizChoiceQuestion = new QuizChoiceQuestion();
        quizChoiceQuestion.setId(rs.getLong("id"));
        quizChoiceQuestion.setQuizId(rs.getLong("quiz_id"));
        quizChoiceQuestion.setChoiceQuestionId(rs.getLong("choice_question_id"));
        quizChoiceQuestion.setAnswer(rs.getString("answer"));
        return quizChoiceQuestion;
    }
}
