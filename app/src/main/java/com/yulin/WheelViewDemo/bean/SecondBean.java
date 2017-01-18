package com.yulin.WheelViewDemo.bean;

/**
 * Created by YuLin on 2017/1/18 0018.
 */
public class SecondBean implements IItemScrollViewData {

    private String secondText;
    private int secondValue;

    @Override
    public String toString() {
        return "SecondBean{" +
                "secondText='" + secondText + '\'' +
                ", secondValue=" + secondValue +
                '}';
    }

    public String getSecondText() {
        return secondText;
    }

    public void setSecondText(String secondText) {
        this.secondText = secondText;
    }

    public int getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(int secondValue) {
        this.secondValue = secondValue;
    }

    @Override
    public String getText() {
        return secondText;
    }

    @Override
    public String getValue() {
        return String.valueOf(secondValue);
    }
}
