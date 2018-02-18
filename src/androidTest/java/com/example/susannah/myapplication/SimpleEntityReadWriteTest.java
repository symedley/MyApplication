package com.example.susannah.myapplication;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Created by Susannah on 2/18/2018.
 */
@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {
    private MyDao mUserDao;
    private TestDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, TestDatabase.class).build();
        mUserDao = mDb.getUserDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        User user = TestUtil.createUser(3);
        user.setName("george");
        mUserDao.insert(user);
        List<User> byName = mUserDao.findUsersByName("george");
        assertThat(byName.get(0), equalTo(user));
    }
}