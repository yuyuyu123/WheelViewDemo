package com.yulin.WheelViewDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yulin.WheelViewDemo.bean.ItemDataObject;
import com.yulin.WheelViewDemo.view.WheelView;

import java.util.ArrayList;

/**
 * Created by YuLin on 2017/1/18 0018.
 */
public class WheelViewActivity extends AppCompatActivity {
    private WheelView mWheelView;
    private ArrayList<ItemDataObject> mData = new ArrayList<>();
    public static Intent newIntent() {
        return new Intent(App.getInstance(), WheelViewActivity.class);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_view);
        mWheelView = (WheelView) findViewById(R.id.id_wheel_view);
        for (int i = 0; i < 20; i++) {
            ItemDataObject object = new ItemDataObject();
            object.itemValue = "" + i;
            object.itemText = "text" + i;
            mData.add(object);
        }
        mWheelView.setData(mData);

        mWheelView.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, WheelView.ItemObject item) {
                Log.d("LY", "id:" + id + "," + item.itemText);
            }

            @Override
            public void selecting(int id, WheelView.ItemObject item) {

            }
        });
    }
}
