package com.example.susannah.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TwoFragment extends Fragment {
    private final String LOG_TAG = TwoFragment.class.getSimpleName();

    private static final String ARG_MESG = "the_message";
    private String mTheMessage;

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
        if (getArguments() != null) {
            mTheMessage = getArguments().getString(ARG_MESG);
        }
    }

    public void onStart() {
        super.onStart();
        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateMessageView(args.getString(ARG_MESG));
        } else if (mTheMessage != null) {
            updateMessageView(mTheMessage);
        }
    }

    public void updateMessageView(String str) {

        // this is just testing shared preferences
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String prefString = sharedPref.getString(OneFragment.PREF_ONESTRING, "mistake");
        prefString = prefString.concat(" - pulled from Prefs");
        Log.v(LOG_TAG, "prefString is " + prefString);

        // test File opertions
        try {
            String filenm = getContext().getFilesDir() + "/" + OneFragment.filename;
            FileReader fileReader = new FileReader(filenm);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            Log.v(LOG_TAG, "the text read from the file is " + stringBuffer.toString());
        } catch (Exception e) {
            Log.e(LOG_TAG, "exception", e);
        }
        // chagne th text view
        TextView textView = getActivity().findViewById(R.id.txt_two);
        textView.setText("                                 ");
        textView.setText(str);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }
}
