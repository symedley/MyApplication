package com.example.susannah.myapplication;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * After getting Room to work, add this to make the app data-change aware.
 * Created by Susannah on 2/22/2018.
 */

public class UserViewModel extends AndroidViewModel {
    private final String LOG_TAG = "userViewModel";

    private MutableLiveData<List<User>> users;

    public UserViewModel(Application application) {
        super(application);
        loadUsers();
    }

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asyncronous operation to fetch users.
        // Room database
        AppDatabase appDatabase = AppDatabase.getAppDatabase(getApplication());
        UserDao userDao = appDatabase.getUserDao();
        List<User> users = userDao.findUserById(1);
        String str;
        if (users != null) {
            if (users.size() != 0) {
                str = users.get(0).firstname;
                Log.v(LOG_TAG, "user READ from database" + str);
            } else Log.v(LOG_TAG, "READ from database was empty list");
        } else Log.v(LOG_TAG, "READ from db was null");

    }
}
