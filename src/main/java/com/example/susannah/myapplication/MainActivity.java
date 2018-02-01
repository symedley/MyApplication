package com.example.susannah.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity
    implements OneFragment.OnFragmentInteractionListener{
    private static final String LOG_TAG="MainActivity";

    private OneFragment oneFragment;
    private TwoFragment twoFragment;

    public void onFragmentInteraction(String newString) {
        Log.v(LOG_TAG, "onFragment interaction in MainActivity");
        twoFragment.updateMessageView("NEW NEW: " + newString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            } // Not dealing properly with savedInstanceState
            oneFragment = new OneFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, oneFragment).commit();
        } else {
            oneFragment = new OneFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_left_frame, oneFragment).commit();
            twoFragment = TwoFragment.newInstance(getString(R.string.no_message_yet));
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_right_frame, twoFragment).commit();
        }
    }
}