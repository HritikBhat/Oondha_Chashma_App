package com.hritik.oondhachashma.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hritik.oondhachashma.Database.Tables.Favorites;

import java.util.List;

import static com.hritik.oondhachashma.Assets.Constants.FavoritesTable;


@Dao
public interface DBInterface
{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long addFavorites(Favorites favorite);

    @Query("SELECT * FROM "+FavoritesTable)
    List<Favorites> getFavorites();

    @Query("SELECT EXISTS(SELECT * FROM "+FavoritesTable+" WHERE sid = :sid)")
    Boolean checkFav(String sid);


    @Query("DELETE FROM "+FavoritesTable+" where sid = :sid")
    void deleteFav(String sid);


}