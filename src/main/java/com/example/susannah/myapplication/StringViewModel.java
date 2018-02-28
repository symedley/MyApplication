package com.example.susannah.myapplication;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

/**
 * After getting Room to work, add this to make the app data-change aware.
 * Created by Susannah on 2/22/2018.
 */

public class StringViewModel extends AndroidViewModel {
    private final String LOG_TAG = "userViewModel";

    private MutableLiveData<String> mTheString;

    public StringViewModel(Application application) {

        super(application);
        Log.v(LOG_TAG, "StringViewMOdel");

        getString();
    }

    public MutableLiveData<String> getString() {
        Log.v(LOG_TAG, "getString");

        if (mTheString == null) {
            mTheString = new MutableLiveData<String>();
            loadString();
        }
        return mTheString;
    }

    private void loadString() {
        // Do an asyncronous operation to fetch users.
        // Room database
        Log.v(LOG_TAG, "loadString...");

        // LEFT OFF:
        // I think the problem is that this pulls
        // the data from the database but doesn't watch it
        // How watch a record in the database?
        getString();

        AppDatabase appDatabase = AppDatabase.getAppDatabase(getApplication());
        UserDao userDao = appDatabase.getUserDao();
        List<User> users = userDao.findUserById(1);
        String str = "placeholder";
        if (users != null) {
            if (users.size() != 0) {
                str = users.get(0).firstname;
                Log.v(LOG_TAG, "user READ from database: " + str);
            } else Log.v(LOG_TAG, "READ from database was empty list");
        } else Log.v(LOG_TAG, "READ from db was null");
        Log.v(LOG_TAG, "loadString got: " + str);
        mTheString.setValue(str);
    }
}
