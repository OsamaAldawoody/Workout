package com.hfad.workout;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hfad.workout.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends Fragment {


    public StopwatchFragment() {
        // Required empty public constructor
    }


    private static final String TAG = "StopWatchActivity";
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;


    Button startButton;
    Button stopButton;
    Button resetButton;

    Bundle bundle;
    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d(TAG,"onCreate.....................................");
        if (savedInstanceState!=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("state");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.v(TAG,"create view ......................");




        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        startButton = (Button)view.findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
            }
        });

        stopButton = (Button)view.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });
        resetButton = (Button)view.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                seconds = 0;
            }
        });
        runTimer(view);
        return view;
    }

    private void runTimer(View view){
        final TextView timeViewer = (TextView)view.findViewById(R.id.time_view);


        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //convert seconds to minutes and hours.
                int hours = seconds /3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                timeViewer.setText(String.format("%d:%02d:%02d",hours,minutes,secs));
                if (running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });

    }

    @Override
    public  void onSaveInstanceState(Bundle outState) {
        Log.d(TAG,"save state .................");
        outState.putInt("seconds",seconds);
        outState.putBoolean("state",running);
        outState.putBoolean("wasRunning",wasRunning);
        super.onSaveInstanceState(outState);
    }



    @Override
    public  void onPause() {
        super.onPause();
        Log.d(TAG,"on pause .................");
        wasRunning = running;
        running = false;
    }

    @Override
    public  void onResume() {
        super.onResume();

        Log.d(TAG,"on resume.................");
        if (wasRunning){
            running=true;
        }
        Log.d(TAG,String.valueOf(running));
    }

    @Override
    public  void onDestroy() {
        Log.d(TAG,"on destroyed .................");
        super.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        Log.v(TAG,"on attach ................");
        super.onAttach(context);
    }
}
