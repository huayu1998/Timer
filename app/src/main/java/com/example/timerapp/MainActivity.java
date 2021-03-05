package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.example.timerapp.ButtonFragment.OnFragmentInteractionListener;


public class MainActivity extends AppCompatActivity implements ButtonFragment.OnFragmentInteractionListener {

    ButtonFragment buttonFragment;
    TimeStampFragment timestampFragment;

    Boolean timeStarted = false;

    //private TimerAsyncTask asynctask; // will use this in controller to execute timer.

    MyAsyncTask myAsyncTask;
    //declare counter
    int count = 0;

    // count for timestamp lists
    int stampLists = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create fragment references

        buttonFragment= (ButtonFragment) getSupportFragmentManager().findFragmentById(R.id.buttonFrag);
        timestampFragment = (TimeStampFragment) getSupportFragmentManager().findFragmentById(R.id.timeListFrag);
        myAsyncTask = new MyAsyncTask();


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //outState.putInt("count", count);
        //outState.putInt("stamp", stampLists);
        //outState.putBoolean("isStarted", timeStarted);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //count = savedInstanceState.getInt("count");
        //stampLists = savedInstanceState.getInt("stamp");
        //timeStarted = savedInstanceState.getBoolean("isStarted");

    }


    @Override
    public void onButtonClicked(int infoID) {


        if (infoID == 0) {

            if (buttonFragment != null && buttonFragment.isInLayout()) {
                if (timeStarted == false) {

                    timeStarted = true;

                    //executing asynctask on button click
                    //first checking if it is already running or not
                    if(myAsyncTask.getStatus()!= AsyncTask.Status.RUNNING){
                        myAsyncTask = new MyAsyncTask();
                        myAsyncTask.execute();
                    }
                    buttonFragment.startNstop.setText("STOP");
                    buttonFragment.startNstop.setTextColor(ContextCompat.getColor(this, R.color.white));
                }
                else if (timeStarted == true) {

                    timeStarted = false;
                    buttonFragment.startNstop.setText("START");
                    buttonFragment.startNstop.setTextColor(ContextCompat.getColor(this, R.color.white));

                }

            }

        }
        else if (infoID == 1) {
                stampLists = 1;
                buttonFragment.timercount.setText("00:00:00");
                count = 0;

                if (timestampFragment != null && timestampFragment.isInLayout()) {
                    buttonFragment.timercount.setText("00:00:00");
                    timestampFragment.showLists.setText("");
                    count = 0;
                }
        }
        else if (infoID == 2) {
            if (timestampFragment != null && timestampFragment.isInLayout()) {
                timestampFragment.showLists.append(String.valueOf(stampLists) + "." + buttonFragment.timercount.getText() + "\n");
                stampLists++;
            }
        }
        else if (infoID == 3) {

            Intent intent = new Intent(this, ActivityList.class);
            startActivity(intent);


        }

    }


    private class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            while(timeStarted){
                try{
                    //checking if the asynctask has been cancelled, end loop if so
                    if(isCancelled()) break;

                    Thread.sleep(1000);

                    count++;

                    //send count to onProgressUpdate to update UI
                    publishProgress(count);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }



        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            buttonFragment.timercount.setText(countTime(count));

        }
    }

    private String countTime(int count) {

        String theTime = "";

        if (count <= 60) {
            if (count < 10) {
                theTime = "00:00:0" + String.valueOf(count);
            }
            else {
                theTime = "00:00:" + String.valueOf(count);
            }
        }
        else if (count > 60 && count <= 3600) {

            String mins;
            String sec;

            if (count/60 < 10) {
                mins = "0" + String.valueOf(count/60);
            }
            else {
                mins = String.valueOf(count/60);
            }

            if (count%60 < 10) {
                sec = "0" + String.valueOf(count%60);
            }
            else {
                sec = String.valueOf(count%60);
            }

            theTime = "00:" + mins + ":" + sec;
        }
        else if (count > 3600) {

            String hrs;
            String mins;
            String sec;

            if (count/3600 < 10) {
                hrs = "0" + String.valueOf(count/3600);
            }
            else {
                hrs = String.valueOf(count/3600);
            }

            int var0 = ((count - 3600 * (count/3600)) / 60);

            if (var0 < 10) {
                mins = "0" + String.valueOf(var0);
            }
            else {
                mins = String.valueOf(var0);
            }

            int var1 = (((count - 3600 * (count/3600)) / 60) % 60);

            if ( var1 < 10) {
                sec = "0" + String.valueOf(var1);
            }
            else {
                sec = String.valueOf(var1);
            }

            theTime = hrs + ":" + mins + ":" + sec;

        }

        return theTime;

    }

}