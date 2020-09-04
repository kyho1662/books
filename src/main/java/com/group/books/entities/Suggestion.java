package com.group.books.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="suggestion")
public class Suggestion {

    @Id
    @SequenceGenerator(name="my_entity_seq_gen", sequenceName="GLOBAL_SEQUENCE", initialValue=0, allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="my_entity_seq_gen")
    @Column(name="suggestion_id")
    private int suggestionId;

    @Column(name="category")
    private String category;

    @Column(name="title")
    private String title;

    // many to one join
    @JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity=Member.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member creator;

    @Column(name="content")
    private String content;

    @Column(name="regdate")
    private Date regdate;

    public Suggestion() {
    }

    public int getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(int suggestionId) {
        this.suggestionId = suggestionId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Member getCreator() {
        return creator;
    }

    public void setCreator(Member creator) {
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
}
