package com.example.nayanjyoti.sndpostrequest.api.model;

public class User {

    private Integer id;
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }
}
