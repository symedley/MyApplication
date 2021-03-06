package com.example.susannah.myapplication;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    Button mButton;
//    private String mTheString;
    StringViewModel model;

    private static final String LOG_TAG = "OneFragment";

    private OnFragmentInteractionListener mListener;

    public OneFragment() {
        // Required empty public constructor
        Log.v(LOG_TAG, "FragmentOne. empty constructor");

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OneFragment.
     */
    public static OneFragment newInstance(String param1, String param2) {
        Log.v(LOG_TAG, "FragmentOne. newinstance");
        OneFragment fragment = new OneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(LOG_TAG, "FragmentOne. onCreeate");
//        mTheString = getActivity().getString(R.string.a_default_string);
        super.onCreate(savedInstanceState);
    }

    static final String PREF_ONESTRING = "com.example.susannah.myapplication.PREF_ONESTRING";
    static final String filename = "MyApplicationFileName.txt";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.v(LOG_TAG, "FragmentOne. onCreateView");
        model = ViewModelProviders.of(getActivity()).get(StringViewModel.class);

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        mButton = view.findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(LOG_TAG, "click listener in fragment");
                EditText et = getActivity().findViewById(R.id.editText);
                String newString = et.getText().toString();

                if ((MainActivity.EXERCISE_VIEW_MODEL == true)) {
                    // this should trigger the update
                    Log.v(LOG_TAG, "don't know what to do here");
                    model.getString().setValue(newString);
//                    if (mListener != null) {
//                        //                    mTheString = newString;
//                        mListener.onFragmentInteraction(newString);
//                    }
                }
                if ((MainActivity.EXERCISE_ROOM_DATABASE == true)) {
                    User user = new User();
                    user.lastname = "datab:" + newString;
                    user.firstname = "datab:" + newString;
                    user.id = 1;
                    AppDatabase appDatabase = AppDatabase.getAppDatabase(getContext());
                    UserDao userDao = appDatabase.getUserDao();
                    userDao.insertUsers(user);
                    Log.v(LOG_TAG, "user inserted in database");
                    if (mListener != null) {
                        //                    mTheString = newString;
                        mListener.onFragmentInteraction(newString);
                    }
                }
            }
        });
        return view;
    }

    // this isn't reall used. Not sure how it's meant to be hooked up
    public void onButtonPressed(View view) {
        Log.v(LOG_TAG, "FragmentOne. onButtonPressed !?!");

        if (mListener != null) {
            EditText et = getActivity().findViewById(R.id.editText);
            String newString =  et.getText().toString();
            mListener.onFragmentInteraction(newString);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String newString);
    }
}
