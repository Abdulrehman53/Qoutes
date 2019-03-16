package com.bilalzaman.motivationalquotes.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bilalzaman.motivationalquotes.models.AuthorModel;
import com.bilalzaman.motivationalquotes.models.ExploreModel;
import com.bilalzaman.motivationalquotes.models.QuotesListModel;

import java.util.ArrayList;

/**
 * Created by BilalZaman on 03/12/2018.
 */
public class DatabaseAccess {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static DatabaseAccess instance;
    private final String quoteQuery = "select quotes._id,authors.name,quotes.body,quotes.is_favourite from quotes inner join authors on quotes.author_id = authors._id order by RANDOM()";
    private final String authorQuery = "select authors._id,authors.name,authors.image from authors order by authors._id";
    private final String catQuery = "select cat_id,name,image from categories order by image";
    private final String catDetail = "select cat_detail._id,categories.name, cat_detail._body,cat_detail.is_favourite from cat_detail inner join categories on categories.cat_id = cat_detail.cat_id where categories.cat_id = ";
    private final String regularRandon = "select body from quotes order by random() limit 1";
    //    order by RANDOM()

    private DatabaseAccess(Context context) {
        this.sqLiteOpenHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();
        this.sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (sqLiteDatabase != null) {
            this.sqLiteDatabase.close();
        }
    }

    public ArrayList<QuotesListModel> getData() {
        ArrayList<QuotesListModel> myData = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(quoteQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            QuotesListModel model = new QuotesListModel();

            model.setAuthorId(cursor.getInt(0));
            model.setAuthorName(cursor.getString(1));
            model.setQuote(cursor.getString(2));
            model.setIsFavourite(cursor.getInt(3));

            cursor.moveToNext();
            myData.add(model);

        }
        cursor.close();
        return myData;
    }

    public ArrayList<QuotesListModel> getRandom() {
        ArrayList<QuotesListModel> myData = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(regularRandon, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            QuotesListModel model = new QuotesListModel();

            model.setAuthorName(cursor.getString(0));

            cursor.moveToNext();
            myData.add(model);

        }
        cursor.close();
        return myData;
    }

    public ArrayList<QuotesListModel> getAuthDetailList(int id) {
        ArrayList<QuotesListModel> myData = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select quotes._id,authors.name,quotes.body,quotes.is_favourite from quotes " +
                "inner join authors on quotes.author_id = authors._id where authors._id = " + id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            QuotesListModel model = new QuotesListModel();

            model.setAuthorId(cursor.getInt(0));
            model.setAuthorName(cursor.getString(1));
            model.setQuote(cursor.getString(2));
            model.setIsFavourite(cursor.getInt(3));

            cursor.moveToNext();
            myData.add(model);

        }
        cursor.close();
        return myData;
    }

    public ArrayList<AuthorModel> getAuthorName() {
        ArrayList<AuthorModel> data = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(authorQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AuthorModel model = new AuthorModel();

            model.setAuthorId(cursor.getInt(0));
            model.setName(cursor.getString(1));
            model.setImage(cursor.getInt(2));

            cursor.moveToNext();
            data.add(model);
        }
        cursor.close();
        return data;
    }


    public void updateList(int id) {

        String updateQuery = "update quotes set is_favourite = 1 where _id = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(updateQuery, null);
        cursor.getCount();
    }

    public ArrayList<QuotesListModel> getUpdatedData() {
        ArrayList<QuotesListModel> myData = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select authors.name,quotes.body,quotes.is_favourite from quotes " +
                "inner join authors on quotes.author_id = authors._id where quotes.is_favourite=1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            QuotesListModel model = new QuotesListModel();

            model.setAuthorName(cursor.getString(0));
            model.setQuote(cursor.getString(1));
            model.setIsFavourite(cursor.getInt(2));

            cursor.moveToNext();
            myData.add(model);

        }
        cursor.close();
        return myData;
    }

    public ArrayList<ExploreModel> getExploreCategories() {
        ArrayList<ExploreModel> myData = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(catQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ExploreModel model = new ExploreModel();

            model.setCatId(cursor.getInt(0));
            model.setCatTitle(cursor.getString(1));
            model.setId(cursor.getInt(2));

            cursor.moveToNext();
            myData.add(model);

        }
        cursor.close();
        return myData;
    }

    public ArrayList<QuotesListModel> getCatDetail(int id) {
        ArrayList<QuotesListModel> myData = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(catDetail + id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            QuotesListModel model = new QuotesListModel();

            model.setAuthorId(cursor.getInt(0));
            model.setAuthorName(cursor.getString(1));
            model.setQuote(cursor.getString(2));
            model.setIsFavourite(cursor.getInt(3));

            cursor.moveToNext();
            myData.add(model);

        }
        cursor.close();
        return myData;
    }

    public void updateCatList(int id) {

        String updateQuery = "update cat_detail set is_favourite = 1 where _id =  " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(updateQuery, null);
        cursor.getCount();
    }

    public ArrayList<QuotesListModel> getExploreUpdateData() {
        ArrayList<QuotesListModel> myData = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select categories._id,categories.name,cat_detail._body,cat_detail.is_favourite" +
                " from cat_detail inner join categories on cat_detail.cat_id = categories.cat_id where cat_detail.is_favourite = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            QuotesListModel model = new QuotesListModel();

            model.setAuthorId(cursor.getInt(0));
            model.setAuthorName(cursor.getString(1));
            model.setQuote(cursor.getString(2));
            model.setIsFavourite(cursor.getInt(3));

            cursor.moveToNext();
            myData.add(model);

        }
        cursor.close();
        return myData;
    }

}