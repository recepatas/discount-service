package com.recepatas.model;

import java.util.Date;

public class User {

    private String userId;
    private UserType userType;
    private Date createdAt;

    public User(String userId, UserType userType, Date createdAt) {
        this.userId = userId;
        this.userType = userType;
        this.createdAt = createdAt;
    }

    public UserType getUserType() {
        return userType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
