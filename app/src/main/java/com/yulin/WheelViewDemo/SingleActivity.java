package com.yulin.WheelViewDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yulin.WheelViewDemo.bean.FirstBean;
import com.yulin.WheelViewDemo.bean.IItemScrollViewData;
import com.yulin.WheelViewDemo.view.ItemScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuLin on 2017/1/18 0018.
 */
public class SingleActivity extends AppCompatActivity {

    private ItemScrollView mItemScrollView;

    public static Intent newIntent() {
        return new Intent(App.getInstance(), SingleActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        mItemScrollView = (ItemScrollView) findViewById(R.id.id_item_scroll_view);
        List<FirstBean> firstList = new ArrayList<>();
        firstList.add(0, getFirstEmptyBean());
        for (int i = 0; i < 20; i++) {
            FirstBean bean = new FirstBean();
            bean.setFirstName("firstName" + i);
            bean.setFirstValue(i);
            firstList.add(bean);
        }
        mItemScrollView.setData(firstList);
        mItemScrollView.setSelection(0);
        mItemScrollView.triggerSelectedEvent();

        mItemScrollView.setItemScrollListener(new ItemScrollView.OnItemScrollListener() {
            @Override
            public void onItemSelected(int selectedIndex, IItemScrollViewData item) {

            }
        });
    }

    private FirstBean getFirstEmptyBean() {
        FirstBean firstBean = new FirstBean();
        firstBean.setFirstValue(-1);
        firstBean.setFirstName("不限");
        return firstBean;
    }
}
