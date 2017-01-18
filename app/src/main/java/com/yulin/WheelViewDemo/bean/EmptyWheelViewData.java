package com.yulin.WheelViewDemo.bean;
/**
 * Created by YuLin on 2017/1/18 0018.
 */
public class EmptyWheelViewData implements IItemScrollViewData {

  public static EmptyWheelViewData getInstance() {
    return new EmptyWheelViewData();
  }

  @Override
  public String getText() {
    return "";
  }

  @Override
  public String getValue() {
    return "";
  }
}
