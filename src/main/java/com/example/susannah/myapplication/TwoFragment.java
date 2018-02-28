package com.example.susannah.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Logger;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TwoFragment extends Fragment {
    private final String LOG_TAG = TwoFragment.class.getSimpleName();
    StringViewModel model;

    private static final String ARG_MESG = "the_message";
    // private String mTheUser;
    private TextView mTheTextView;

    List<User> users;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TwoFragment.
     */
    public static TwoFragment newInstance(String theMessage) {
        TwoFragment fragment = new TwoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESG, theMessage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOG_TAG, "TwoFragment->onCreate" );
        // first pull the stringfomr the arguments
        if (getArguments() != null) {
            String string = getArguments().getString(ARG_MESG);
            Log.v(LOG_TAG, "The argument was this string :"+ string +" but it's beig ignored");
        }
  //      final UserViewModel model = ViewModelProviders.of(this).get(UserViewModel.class);
        model = ViewModelProviders.of(this).get(StringViewModel.class);

        // Create the observer which updates the UI").
        final Observer<String> stringObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newString) {
                // Update the UI, in this case, a TextView.
                Log.v(LOG_TAG, "onChanged. newString = " + newString);
                mTheTextView.setText(newString);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.getString().observe(this, stringObserver);
    }

    public void onStart() {
        super.onStart();
        Log.v(LOG_TAG, "TwoFragment->onstart");
        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateMessageView(args.getString(ARG_MESG));
        }
    }

    public void updateMessageView(String theNewStringArg) {
        Log.v(LOG_TAG, "updateMessageView");
        // chagne th text view
        mTheTextView = getActivity().findViewById(R.id.txt_two);
        mTheTextView.setText("                                 ");
        if (MainActivity.EXERCISE_VIEW_MODEL == true) {
            model.getString().setValue(theNewStringArg);
            Log.v(LOG_TAG, "Now setting the string int he viewmodel from within the updateMessageView method in TwoFragment");
            Log.v(LOG_TAG, "data in the view model is now " + model.getString().getValue().toString());
            // it should not be necessary update the textview directly.
        }
        if (MainActivity.EXERCISE_PREFERENCES == true) {
            // this is just testing shared preferences
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            String prefString = sharedPref.getString(OneFragment.PREF_ONESTRING, "mistake");
            prefString = prefString.concat(" - pulled from Prefs");
            Log.v(LOG_TAG, "pref: " + prefString);
            theNewStringArg = prefString;
            mTheTextView.setText(theNewStringArg);

        }
        //  File opertaions
        if (MainActivity.EXERCISE_FILE == true) {
            try {
                String filenm = getContext().getFilesDir() + "/" + OneFragment.filename;
                FileReader fileReader = new FileReader(filenm);
                if (fileReader != null) {
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }

                    Log.v(LOG_TAG, "the text read from the file is " + stringBuffer.toString());
                    theNewStringArg = stringBuffer.toString();
                    mTheTextView.setText(theNewStringArg);

                } else {
                    Log.v(LOG_TAG, "no file found");
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, "exception", e);
            }
        }

        if (MainActivity.EXERCISE_ROOM_DATABASE == true) {
            // Room database
            AppDatabase appDatabase = AppDatabase.getAppDatabase(getContext());
            UserDao userDao = appDatabase.getUserDao();
            List<User> users = userDao.findUserById(1);
            if (users != null) {
                if (users.size() != 0) {
                    theNewStringArg = users.get(0).firstname;
                    Log.v(LOG_TAG, "user READ from database" + theNewStringArg);
                } else Log.v(LOG_TAG, "READ from database was empty list");
            } else Log.v(LOG_TAG, "READ from db was null");
            mTheTextView.setText(theNewStringArg);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }
}
