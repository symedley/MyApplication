package com.example.susannah.myapplication;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
    implements OneFragment.OnFragmentInteractionListener{
    private static final String LOG_TAG="MainActivity";

    protected static final boolean EXERCISE_ROOM_DATABASE = false;
    protected static final boolean EXERCISE_VIEW_MODEL = true;


    private Boolean isLandscape;
    public static final String EXTRA_MESSAGE = "com.example.susannah.myapplication.EXTRA_MESSAGE";
    protected OneFragment oneFragment;
    protected TwoFragment twoFragment;
    AsyncDataTask myAsyncDataTaskTask;

    public void onFragmentInteraction(String newString) {
        Log.v(LOG_TAG, "onFragment interaction in MainActivity");
        if (isLandscape == true) {
            twoFragment.updateMessageView("NEW: " + newString);
        }else {
            Intent intent = new Intent(this, TwoHolderActivity.class);
            intent.putExtra(EXTRA_MESSAGE, newString);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_container) != null) {
            isLandscape = false ;
            if (savedInstanceState != null) {
                return;
            } // Not dealing properly with savedInstanceState
            oneFragment = new OneFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, oneFragment).commit();
        } else {
            isLandscape = true;
            oneFragment = new OneFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_left_frame, oneFragment).commit();
            twoFragment = TwoFragment.newInstance(getString(R.string.no_message_yet));
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_right_frame, twoFragment).commit();
        }
    }
}