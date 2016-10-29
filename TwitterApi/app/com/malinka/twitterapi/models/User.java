package com.malinka.twitterapi.models;

/**
 * Created by malinkas on 5/18/16.
 * User class with what's required for the application
 */
public class User {
    private String name;
    private Long userId;

    public User(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

