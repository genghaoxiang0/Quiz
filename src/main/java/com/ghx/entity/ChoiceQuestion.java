package com.ghx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "choice_question")
public class ChoiceQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String category;
    private String description;
    @Column(name = "choice_a")
    private String choiceA;
    @Column(name = "choice_b")
    private String choiceB;
    @Column(name = "choice_c")
    private String choiceC;
    @Column(name = "choice_d")
    private String choiceD;
    private String answer;
}
