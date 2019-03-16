package com.bilalzaman.motivationalquotes.models;

/**
 * Created by BilalZaman on 19/10/2018.
 */
public class QuotesListModel {

    private String id, quote, authorName;
    private int isFavourite,authorId;


    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public QuotesListModel(String quote, String authorName) {
        this.quote = quote;
        this.authorName = authorName;

    }

    public QuotesListModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

}
