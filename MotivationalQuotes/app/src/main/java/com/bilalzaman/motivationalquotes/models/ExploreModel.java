package com.bilalzaman.motivationalquotes.models;

/**
 * Created by BilalZaman on 20/10/2018.
 */
public class ExploreModel {

    private int backgroundImage;
    private String catTitle;

    public ExploreModel(int backgroundImage, String catTitle) {
        this.backgroundImage = backgroundImage;
        this.catTitle = catTitle;
    }

    public int getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(int backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(String catTitle) {
        this.catTitle = catTitle;
    }
}
