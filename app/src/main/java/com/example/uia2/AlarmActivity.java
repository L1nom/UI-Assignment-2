package com.example.uia2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.uia2.databinding.ActivityAlarmBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmActivity extends Activity {

    ImageView addAlarm, cancelAlarm;
    TextView alarmText, timeofday;
    TimePicker timePicker;
    private ActivityAlarmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlarmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        alarmText = findViewById(R.id.alarm_text);
        addAlarm = findViewById(R.id.addAlarm);
        cancelAlarm = findViewById(R.id.cancelAlarm);
        timePicker = findViewById(R.id.timePicker);
        timeofday = findViewById(R.id.show_ampm);

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        timePicker.setIs24HourView(false);
        timePicker.setHour(hour);
        timePicker.setMinute(minute);

        ArrayList<Integer> alarmDays= new ArrayList<Integer>();


        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                if (hour < 12) {
                    timeofday.setText("AM");
                } else {
                    timeofday.setText("PM");
                }
            }
        });


        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int alarm_hour = timePicker.getHour();
                int alarm_minute = timePicker.getMinute();

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, alarm_hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, alarm_minute);
                intent.putExtra(AlarmClock.EXTRA_DAYS, alarmDays);
                startActivity(intent);
                alarmText.setText("Alarm set for " + hour + ":" + minute + " " + timeofday.getText().toString());
            }

        });

        cancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                startActivity(intent);
            }
        });
    }

}