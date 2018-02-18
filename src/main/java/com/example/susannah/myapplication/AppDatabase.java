package com.example.susannah.myapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Susannah on 2/18/2018.
 */

@Database(entities = {  User.class, Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract UserDao getUserDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, // don't really allow queries on main thread in an app
                    "database-name").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
