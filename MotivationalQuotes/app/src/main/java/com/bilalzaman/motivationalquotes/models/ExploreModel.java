package com.bilalzaman.motivationalquotes.models;

/**
 * Created by BilalZaman on 20/10/2018.
 */
public class ExploreModel {

    private String backgroundImage;
    private String catTitle;
    private String id;

    public ExploreModel(String id, String catTitle) {
        this.id = id;
        this.backgroundImage = backgroundImage;
        this.catTitle = catTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
