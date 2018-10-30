package com.bilalzaman.motivationalquotes.models;

/**
 * Created by BilalZaman on 19/10/2018.
 */
public class HomeModel {

    private String backgroundImage;
    private String catTitle;
    private String catAuthor;

    public HomeModel(String catTitle, String catAuthor) {
        this.catTitle = catTitle;
        this.catAuthor = catAuthor;
    }


    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(String catTitle) {
        this.catTitle = catTitle;
    }

    public String getCatAuthor() {
        return catAuthor;
    }

    public void setCatAuthor(String catAuthor) {
        this.catAuthor = catAuthor;
    }
}
