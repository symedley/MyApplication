package com.example.susannah.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link TwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TwoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_MESG = "the_message";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//    private String mTheMessage;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param theMessage Parameter 1.
     * @return A new instance of fragment TwoFragment.
     */
    public static TwoFragment newInstance(String theMessage) {
        TwoFragment fragment = new TwoFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_MESG, theMessage);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // if there were arguments, we'd extract them here
    }

    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
    public interface OnFragmentTwoInteractionListener {
        void onFragmentTwoInteraction();
    }

//    private void updateMessageView(String str) {
//        // chagne th text view
//        TextView textView = getActivity().findViewById(R.id.txt_two);
//        textView.setText(str);
//    }
}
