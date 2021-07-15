package com.hritik.oondhachashma.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.hritik.oondhachashma.Database.Tables.Favorites;
import com.hritik.oondhachashma.Database.Tables.Pointer;

@Database(entities = {Favorites.class, Pointer.class},version = 1,exportSchema = false)
public abstract class TMKOCDatabase  extends RoomDatabase
{
    public abstract com.hritik.oondhachashma.Database.DBInterface myDao();
}