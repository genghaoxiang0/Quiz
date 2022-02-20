package com.ghx.rowMapper;

import com.ghx.entity.ChoiceQuestion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChoiceQuestionRowMapper implements RowMapper<ChoiceQuestion> {
    @Override
    public ChoiceQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChoiceQuestion choiceQuestion = new ChoiceQuestion();
        choiceQuestion.setId(rs.getLong("id"));
        choiceQuestion.setCategory(rs.getString("category"));
        choiceQuestion.setDescription(rs.getString("description"));
        choiceQuestion.setChoiceA(rs.getString("choice_A"));
        choiceQuestion.setChoiceB(rs.getString("choice_B"));
        choiceQuestion.setChoiceC(rs.getString("choice_C"));
        choiceQuestion.setChoiceD(rs.getString("choice_D"));
        choiceQuestion.setAnswer(rs.getString("answer"));
        return choiceQuestion;
    }
}
