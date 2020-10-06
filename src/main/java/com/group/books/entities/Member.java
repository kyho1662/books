package com.group.books.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="member")
public class Member {

    @Id
    @SequenceGenerator(name="my_entity_seq_gen", sequenceName="GLOBAL_SEQUENCE", initialValue=0, allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="my_entity_seq_gen")
    @Column(name="member_id")
    private int memberId;

    @Column(name="id")
    private String loginId;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;


    public Member() {
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

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    // JSON 요청에서 누락시킴
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    // JSON 데이터 저장은 허용
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
