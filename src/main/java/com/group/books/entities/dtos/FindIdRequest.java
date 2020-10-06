package com.group.books.entities.dtos;

public class FindIdRequest {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
