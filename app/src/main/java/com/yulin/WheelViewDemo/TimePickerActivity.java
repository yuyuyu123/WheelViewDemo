package com.yulin.WheelViewDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yulin.WheelViewDemo.view.TimePicker;

import java.util.Calendar;

/**
 * Created by YuLin on 2017/1/13 0013.
 */
public class TimePickerActivity extends AppCompatActivity {
    private Calendar mCurrCalendar;
    private TimePicker mTimePicker;

    public static Intent newIntent() {
        return new Intent(App.getInstance(), TimePickerActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        mTimePicker = (TimePicker) findViewById(R.id.id_time_picker);
        mCurrCalendar = Calendar.getInstance();
        mTimePicker.initSelected(mCurrCalendar);
        mTimePicker.setDisplayDays(7);

        mTimePicker.setDateChangeListener(new TimePicker.OnDateChangeListener() {
            @Override
            public void onDateChange() {
                Log.d("YL", "" + mTimePicker.getDate().itemText + "," + mTimePicker.getHour().itemText + "," + mTimePicker.getMin().itemText);
            }
        });
    }
}
