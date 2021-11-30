package com.example.uia2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;

import com.example.uia2.databinding.ActivityMainBinding;
import com.example.uia2.databinding.ActivityStopwatchBinding;

public class StopwatchActivity extends Activity {

    private ActivityStopwatchBinding binding;

    ImageView start, pause, stop;
    Chronometer chronometer;
    private long pauseOffset;
    boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStopwatchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chronometer = findViewById(R.id.chronometer);
        start = findViewById(R.id.start);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!running) {
                    chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    chronometer.start();
                    running = true;
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running) {
                    chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    running = false;
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
            }
        });


    }
}