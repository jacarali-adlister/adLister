package com.codeup.adlister.models;

public class Category {
    private long id;
    private String title;
    private String imgUrl;

    public Category(long id, String title, String imgUrl) {
        this.id = id;
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public Category(String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public Category(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
