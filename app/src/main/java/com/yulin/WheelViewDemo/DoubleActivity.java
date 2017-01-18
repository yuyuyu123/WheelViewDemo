package com.yulin.WheelViewDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yulin.WheelViewDemo.bean.FirstBean;
import com.yulin.WheelViewDemo.bean.IItemScrollViewData;
import com.yulin.WheelViewDemo.bean.SecondBean;
import com.yulin.WheelViewDemo.view.ItemScrollView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by YuLin on 2017/1/18 0018.
 */
public class DoubleActivity extends AppCompatActivity {
    private ItemScrollView mItemScrollView1;
    private ItemScrollView mItemScrollView2;

    public static Intent newIntent() {
        return new Intent(App.getInstance(), DoubleActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double);

        mItemScrollView1 = (ItemScrollView) findViewById(R.id.id_item_scroll_view_1);
        mItemScrollView2 = (ItemScrollView) findViewById(R.id.id_item_scroll_view_2);

        initListener();

        List<FirstBean> firstList = new ArrayList<>();
        firstList.add(0, getFirstEmptyBean());
        for (int i = 0; i < 20; i++) {
            FirstBean bean = new FirstBean();
            bean.setFirstName("firstName" + i);
            bean.setFirstValue(i);
            List<SecondBean> secondList = new ArrayList<>();
            for(int j = 0; j < 5; j++) {
                SecondBean sBean = new SecondBean();
                sBean.setSecondText("secondName" + i);
                sBean.setSecondValue(i);
                secondList.add(sBean);
            }
            bean.setSecList(secondList);
            firstList.add(bean);
        }
        mItemScrollView1.setData(firstList);
        mItemScrollView1.setSelection(0);
        mItemScrollView1.triggerSelectedEvent();
    }

    private void initListener() {
        mItemScrollView1.setItemScrollListener(new ItemScrollView.OnItemScrollListener() {
            @Override
            public void onItemSelected(int selectedIndex, IItemScrollViewData item) {
                FirstBean selectedItem = (FirstBean) item;
                SecondBean emptySecond = getSecondEmptyBean();

                List secondList = new ArrayList();
                if (selectedItem.getSecList() != null) {
                    Collections.addAll(secondList, new Object[selectedItem.getSecList().size()]);
                    Collections.copy(secondList, selectedItem.getSecList());
                }

                secondList.add(0, emptySecond);
                mItemScrollView2.setData(secondList);
                mItemScrollView2.setSelection(0);
                mItemScrollView2.triggerSelectedEvent();
            }
        });

        mItemScrollView2.setItemScrollListener(new ItemScrollView.OnItemScrollListener() {
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

    private SecondBean getSecondEmptyBean() {
        SecondBean secondBean = new SecondBean();
        secondBean.setSecondValue(-1);
        secondBean.setSecondText("不限");
        return secondBean;
    }
}
