package com.yulin.WheelViewDemo.bean;

import java.util.List;

/**
 * Created by YuLin on 2017/1/18 0018.
 */
public class FirstBean implements IItemScrollViewData {
    private String firstName;
    private int firstValue;

    private List<SecondBean> mSecList;

    @Override
    public String toString() {
        return "FirstBean{" +
                "firstName='" + firstName + '\'' +
                ", firstValue=" + firstValue +
                ", mSecList=" + mSecList +
                '}';
    }

    public List<SecondBean> getSecList() {
        return mSecList;
    }

    public void setSecList(List<SecondBean> mSecList) {
        this.mSecList = mSecList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(int firstValue) {
        this.firstValue = firstValue;
    }

    @Override
    public String getText() {
        return firstName;
    }

    @Override
    public String getValue() {
        return String.valueOf(firstValue);
    }
}
