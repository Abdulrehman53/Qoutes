package com.bilalzaman.motivationalquotes.models;



/**
 * Created by BilalZaman on 23/11/2018.
 */

public class ExploreModel {


    private int catId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private int isFavourite;
    private String catTitle,catQuote;

    public String getCatQuote() {
        return catQuote;
    }

    public void setCatQuote(String catQuote) {
        this.catQuote = catQuote;
    }

    public ExploreModel() {
    }

    public ExploreModel(int isFavourite, String catTitle) {
        this.isFavourite = isFavourite;
        this.catTitle = catTitle;
    }

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(String catTitle) {
        this.catTitle = catTitle;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }
}
