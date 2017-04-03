package com.futoria.system.model;

import com.futoria.core.model.university.Subject;
import com.futoria.core.model.user.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tst_test")
public class Test {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @Column(
            name = "uuid",
            nullable = false,
            length = 36,
            unique = true
    )
    private String uuid;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "subject_id",
            nullable = false
    )
    private Subject subject;

    @Column(
            name = "need_validation"
    )
    private Boolean needValidation;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user_owner_id"
    )
    private User owner;

    @Column(
            name = "title",
            length = 100,
            nullable = false
    )
    private String title;

    @ManyToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinTable(name = "tst_rel_test_question",
            joinColumns = @JoinColumn(
                    name = "test_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "question_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Question> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Boolean getNeedValidation() {
        return needValidation;
    }

    public void setNeedValidation(Boolean needValidation) {
        this.needValidation = needValidation;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
