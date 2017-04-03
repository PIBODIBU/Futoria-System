package com.futoria.system.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tst_test_question")
public class Question {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "difficulty_level_id"
    )
    private QuestionDifficulty difficulty;

    @Column(
            name = "title",
            nullable = false,
            length = 200
    )
    private String title;

    @ManyToMany(
            mappedBy = "questions",
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE
    )
    private Set<Test> tests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(QuestionDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
}
