package com.bilalzaman.motivationalquotes.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.bilalzaman.motivationalquotes.models.FavouriteModel;

/**
 * Created by BilalZaman on 23/11/2018.
 */
@Database(entities = {FavouriteModel.class}, version = 1)
public abstract class FavDatabase extends RoomDatabase {

    FavDatabase favDatabase;

    public FavDatabase getInstance(Context context) {
        if (favDatabase == null)
            favDatabase = Room.databaseBuilder(context, FavDatabase.class, "favdatabase").allowMainThreadQueries().build();
        return favDatabase;
    }

    public abstract FavCatDao favCatDao();

}
