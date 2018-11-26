package com.bilalzaman.motivationalquotes.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by BilalZaman on 23/11/2018.
 */
@Entity
public class FavouriteModel {

    @PrimaryKey(autoGenerate =  true)
    private int catId;
    @ColumnInfo(name = "bgImage")
    private String backgroundImage;
    @ColumnInfo(name = "title")
    private String catTitle;
    private String id;

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

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }
}
