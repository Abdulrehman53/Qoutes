package com.bilalzaman.motivationalquotes.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.bilalzaman.motivationalquotes.models.FavouriteModel;

import java.util.List;

/**
 * Created by BilalZaman on 23/11/2018.
 */
@Dao
public interface FavCatDao {

    @Query("SELECT * FROM favouritemodel")
    public List<FavouriteModel> showAllUsers();

    @Insert
    public void insertAll(FavouriteModel... favouriteModels);

    @Delete
    public void delete(FavouriteModel favouriteModel);

}
