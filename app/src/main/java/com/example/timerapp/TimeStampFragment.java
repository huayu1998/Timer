package com.example.timerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TimeStampFragment extends Fragment /*implements View.OnClickListener*/ {

    Button back;
    TextView showLists;

    //declare interaction listener
   // private ButtonFragment.OnFragmentInteractionListener myListener;

    public TimeStampFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_time_stamp, container, false);

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_time_stamp, container, false);

        back = view.findViewById(R.id.back);
        showLists = view.findViewById(R.id.listsss);

        return view;
    }

    //@Override
    //public void onClick(View v) {
      //  if (v.getId() == back.getId()) {
        //    myListener.onButtonClicked(4);
        //}
    //}

    //public interface OnFragmentInteractionListener{
      //  void onButtonClicked(int infoID);
    //}
}