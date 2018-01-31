package com.example.susannah.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity
    implements OneFragment.OnFragmentInteractionListener, TwoFragment.OnFragmentTwoInteractionListener {
    private static final String LOG_TAG="MainActivity";

    public void onFragmentInteraction() {
        Log.v(LOG_TAG, "onFragment interaction in MainActivity");
        TextView  tv = findViewById(R.id.txt_two);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            OneFragment oneFragment = new OneFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, oneFragment).commit();
        }
    }

    @Override
    public void onFragmentTwoInteraction() {
        Log.v(LOG_TAG, "on Fragment TWO interaction in MainActivity");
    }
}