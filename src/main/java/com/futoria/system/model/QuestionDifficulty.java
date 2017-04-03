package com.futoria.system.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tst_test_question_difficulty")
public class QuestionDifficulty {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @Column(
            name = "level",
            nullable = false
    )
    private Integer level;

    @Column(
            name = "description",
            length = 300,
            nullable = false
    )
    private String description;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE,
            mappedBy = "difficulty"
    )
    private Set<Question> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
