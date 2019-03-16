package com.bilalzaman.motivationalquotes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by BilalZaman on 03/12/2018.
 */
public class DatabaseHelper extends SQLiteAssetHelper {

    private Context context;
    private static final int DATABASE_VERSION = 4;
    public static String DATABASE = "store.db";

    public DatabaseHelper(Context context) {

        super(context, DATABASE, null, DATABASE_VERSION);
        this.context = context;
        setForcedUpgrade();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
