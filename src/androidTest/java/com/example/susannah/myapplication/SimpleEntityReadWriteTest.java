package com.example.susannah.myapplication;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

/**
 * Created by Susannah on 2/18/2018.
 */
@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {
    private UserDao mUserDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mUserDao = mDb.getUserDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        User user = new User();
        user.firstname = "joe";
        user.lastname = "Cool";
        user.age = 12;
        mUserDao.insertUsers(user);
        List<User> byName = mUserDao.findUserWithName("joe");
        assert (byName.get(0).equals(user));
    }
}