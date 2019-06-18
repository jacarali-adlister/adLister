package com.codeup.adlister.models;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class Ad {
    private long id;
    private long userId;
    private String title;
    private String description;
    private Date create_date;
    private String imageUrl;

    public Ad(long id, long userId, String title, String description, Date create_date, String imageUrl) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.create_date = create_date;
        this.imageUrl = imageUrl;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Ad(long userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
