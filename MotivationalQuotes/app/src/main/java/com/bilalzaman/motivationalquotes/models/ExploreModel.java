package com.bilalzaman.motivationalquotes.models;

/**
 * Created by BilalZaman on 20/10/2018.
 */
public class ExploreModel {

    private String backgroundImage;
    private String catTitle;

    public ExploreModel(String backgroundImage, String catTitle) {
        this.backgroundImage = backgroundImage;
        this.catTitle = catTitle;
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
}
