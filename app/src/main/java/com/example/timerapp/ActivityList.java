package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.res.Configuration;
import android.net.Uri;

public class ActivityList extends AppCompatActivity {

    ButtonFragment buttonFragment;
    TimeStampFragment timestampFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Check if we are at the land mode
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
             finish();
             return;
        }

        buttonFragment = (ButtonFragment) getSupportFragmentManager().findFragmentById(R.id.buttonFrag);
        timestampFragment = (TimeStampFragment) getSupportFragmentManager().findFragmentById(R.id.timeListFrag);

    }

}