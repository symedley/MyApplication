package com.example.susannah.myapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Susannah on 2/18/2018.
 */

@Database(entities = {  User.class, Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MyDao userDao();
}
