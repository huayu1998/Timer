package com.example.timerapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class ButtonFragment extends Fragment implements View.OnClickListener {

    Button startNstop;
    Button reset;
    Button count;
    Button forward;
    TextView timercount;

    //declare interaction listener
    private OnFragmentInteractionListener mListener;

    public ButtonFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_button, container, false);


        View view= inflater.inflate(R.layout.fragment_button, container, false);

        startNstop = (Button) view.findViewById(R.id.startNstop);
        reset = (Button) view.findViewById(R.id.reject);
        count = (Button) view.findViewById(R.id.count);
        forward = (Button) view.findViewById(R.id.forward);
        timercount = (TextView) view.findViewById(R.id.timecount);

        //add listeners
        startNstop.setOnClickListener(this);
        reset.setOnClickListener(this);
        count.setOnClickListener(this);

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnFragmentInteractionListener){
            this.mListener= (OnFragmentInteractionListener) context;
        }else{
          //  throw new RuntimeException(context.toString()+" must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == startNstop.getId()) {
            mListener.onButtonClicked(0);
        }
        else if (v.getId() == reset.getId()) {
            mListener.onButtonClicked(1);
        }
        else if (v.getId() == count.getId()) {
            mListener.onButtonClicked(2);
        }
        else if (v.getId() == forward.getId()) {
            mListener.onButtonClicked(3);
        }

    }


    public interface OnFragmentInteractionListener{
        void onButtonClicked(int infoID);
    }
}