package com.ghx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quiz_choice_question")
public class QuizChoiceQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "quiz_id")
    private long quizId;
    @Column(name = "choice_question_id")
    private long choiceQuestionId;
    private String answer;
}
