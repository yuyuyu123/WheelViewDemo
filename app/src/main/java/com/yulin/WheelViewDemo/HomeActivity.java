package com.yulin.WheelViewDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by Administrator on 2017/1/18 0018.
 */

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
    }

    public void singleItemScrollView(View v) {
        startActivity(SingleActivity.newIntent());
    }

    public void doubleItemScrollView(View v) {
        startActivity(DoubleActivity.newIntent());
    }

    public void wheelView(View v) {
        startActivity(WheelViewActivity.newIntent());
    }

    public void timePicker(View v) {
        startActivity(TimePickerActivity.newIntent());
    }
}
