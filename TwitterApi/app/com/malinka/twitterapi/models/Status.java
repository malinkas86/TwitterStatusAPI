package com.malinka.twitterapi.models;

import java.util.Date;

/**
 * Created by malinkas on 5/18/16.
 * Status class with what's only required for the application
 */
public class Status {
    private User user;

    private Date createdAt;

    private String text;

    public Status(){
    }

    public Status(String text){
        this.text=text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
