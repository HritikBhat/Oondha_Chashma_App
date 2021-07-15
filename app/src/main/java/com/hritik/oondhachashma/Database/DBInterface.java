package com.hritik.oondhachashma.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.hritik.oondhachashma.Database.Tables.Favorites;
import com.hritik.oondhachashma.Database.Tables.Pointer;

import java.util.List;

import static com.hritik.oondhachashma.Assets.Constants.FavoritesTable;
import static com.hritik.oondhachashma.Assets.Constants.PointersTable;


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

    @Query("SELECT EXISTS(SELECT * FROM "+PointersTable+" WHERE sid = :sid)")
    Boolean checkPointer(String sid);

    @Query("SELECT * FROM "+PointersTable+" WHERE sid = :sid")
    Pointer getPointer(String sid);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long addPointer(Pointer pointer);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updatePointer(Pointer pointer);

    @Query("DELETE FROM "+PointersTable+" where sid = :sid")
    void deletePointer(String sid);


}