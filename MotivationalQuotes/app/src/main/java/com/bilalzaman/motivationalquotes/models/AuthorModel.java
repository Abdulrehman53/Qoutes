package com.bilalzaman.motivationalquotes.models;

import java.net.IDN;

/**
 * Created by BilalZaman on 27/11/2018.
 */
public class AuthorModel {

    private String name, id;
    private int image;
    private int authorId;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public AuthorModel() {
    }

    public AuthorModel(String iD, int image, String name) {
        this.id = iD;
        this.image = image;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
