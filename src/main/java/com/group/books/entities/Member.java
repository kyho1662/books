package com.group.books.entities;

import javax.persistence.*;

@Entity
@Table(name="member")
public class Member {

    @Id
    @SequenceGenerator(name="my_entity_seq_gen", sequenceName="GLOBAL_SEQUENCE", initialValue=0, allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="my_entity_seq_gen")
    @Column(name="member_id")
    private int memberId;

    @Column(name="name")
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
