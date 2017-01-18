package com.yulin.WheelViewDemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.yulin.WheelViewDemo.utils.DateTimeUtils;
import com.yulin.WheelViewDemo.bean.ItemDataObject;
import com.yulin.WheelViewDemo.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TimePicker extends LinearLayout {
	private static final int DEFAULT_DISPLAY_DAYS = 1;
	private static final int DEFAULT_MIN_INTERVAL = 20;

	private WheelView mWheelViewDate;
	private WheelView mWheelViewHour;
	private WheelView mWheelViewMin;

	private WheelView.ItemObject mDate;
	private WheelView.ItemObject mHour;
	private WheelView.ItemObject mMin;

	private OnDateChangeListener mDateChangeListener;

	private int mMinInterval = DEFAULT_MIN_INTERVAL;

	private int mDisplayDays = DEFAULT_DISPLAY_DAYS;

	public int getDisplayDays() {
		return mDisplayDays;
	}

	public void setDisplayDays(int displayDays) {
		if(displayDays > 7) {
			displayDays = 7;
		}

		if(displayDays < DEFAULT_DISPLAY_DAYS) {
			displayDays = DEFAULT_DISPLAY_DAYS;
		}

		this.mDisplayDays = displayDays;

		this.onFinishInflate();
	}

	/**
	 * @return the mDate
	 */
	public WheelView.ItemObject getDate() {
		return mDate;
	}

	/**
	 * @param mDate the mDate to set
	 */
	public void setDate(WheelView.ItemObject mDate) {
		this.mDate = mDate;
	}

	/**
	 * @return the mHour
	 */
	public WheelView.ItemObject getHour() {
		return mHour;
	}

	/**
	 * @param mHour the mHour to set
	 */
	public void setHour(WheelView.ItemObject mHour) {
		this.mHour = mHour;
	}

	/**
	 * @return the mMin
	 */
	public WheelView.ItemObject getMin() {
		return mMin;
	}

	/**
	 * @param mMin the mMin to set
	 */
	public void setMin(WheelView.ItemObject mMin) {
		this.mMin = mMin;
	}

	public TimePicker(Context context) {
		this(context, null);
	}

	public TimePicker(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override protected void onFinishInflate() {
		super.onFinishInflate();
		LayoutInflater.from(getContext()).inflate(R.layout.layout_time_picker, this);
		mWheelViewDate = (WheelView) findViewById(R.id.app_date);
		mWheelViewHour = (WheelView) findViewById(R.id.app_hour);
		mWheelViewMin = (WheelView) findViewById(R.id.app_min);

		mWheelViewDate.setData(getYearData());
		mWheelViewHour.setData(getMonthData());
		mWheelViewMin.setData(getMinData());

		mWheelViewDate.setOnSelectListener(new WheelView.OnSelectListener() {

			@Override
			public void endSelect(int id, WheelView.ItemObject item) {
				setDate(item);
				dateChangeCallback();
			}

			@Override
			public void selecting(int id, WheelView.ItemObject item) {}
		});

		mWheelViewHour.setOnSelectListener(new WheelView.OnSelectListener() {

			@Override
			public void selecting(int id, WheelView.ItemObject item) {}

			@Override
			public void endSelect(int id, WheelView.ItemObject item) {
				setHour(item);
				dateChangeCallback();
			}
		});

		mWheelViewMin.setOnSelectListener(new WheelView.OnSelectListener() {

			@Override
			public void selecting(int id, WheelView.ItemObject item) {

			}

			@Override
			public void endSelect(int id, WheelView.ItemObject item) {
				setMin(item);
				dateChangeCallback();
			}
		});
	}

	private void  dateChangeCallback() {
		if (null != mDateChangeListener) {
			mDateChangeListener.onDateChange();
		}
	}

	public void initSelected(Calendar current) {
		Calendar calendar = Calendar.getInstance();
		int durHours = DateTimeUtils.getDay(current.getTime()) - DateTimeUtils.getDay(calendar.getTime());
		int dur = Integer.parseInt(String.valueOf(durHours));
		if (dur < 0) {
			dur = 0;
		}
		if (Calendar.getInstance().get(Calendar.MINUTE) > 40) {
			setMonthData(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1, 24);
		} else {
			setMonthData(Calendar.getInstance().get(Calendar.HOUR_OF_DAY), 24);
		}
		mWheelViewDate.setDefault(dur);
		mWheelViewHour
				.setDefault(current.get(Calendar.HOUR_OF_DAY) - calendar.get(Calendar.HOUR_OF_DAY));
		mWheelViewMin.setDefault(current.get(Calendar.MINUTE) / getMinInterval());
		this.setDate(mWheelViewDate.getItemList().get(dur));
		this.setHour(mWheelViewHour.getItemList().get(current.get(Calendar.HOUR_OF_DAY) - calendar.get(Calendar.HOUR_OF_DAY)));
		this.setMin(mWheelViewMin.getItemList().get(current.get(Calendar.MINUTE) / getMinInterval()));
	}

	/**
	 *
	 * @return
     */
	private ArrayList<ItemDataObject> getYearData() {
		ArrayList<ItemDataObject> list = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < mDisplayDays; i++) {
			if (i > 0) {
				calendar.add(Calendar.DATE, 1);
			}

			ItemDataObject item = new ItemDataObject();
			item.isCanSelected = true;
			if (i == 0) {
				item.itemText = DateTimeUtils.formatDate(calendar.getTime(), "今  天");
			} else {
				item.itemText = DateTimeUtils.formatDate(calendar.getTime(), "MM-dd   E");
			}
			item.itemValue = DateTimeUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
			list.add(item);
		}
		return list;
	}

	/**
	 *
	 * @return
     */
	private ArrayList<ItemDataObject> getMonthData() {
		ArrayList<ItemDataObject> list = new ArrayList<>();
		for (int i = 0; i < 24; i++) {
			ItemDataObject item = new ItemDataObject();
			item.isCanSelected = true;
			item.itemText = new DecimalFormat("00").format(i);
			item.itemValue = item.itemText;
			list.add(item);
		}
		return list;
	}

	/**
	 *
	 * @param start
	 * @param end
     */
	public void setMonthData(int start, int end) {
		ArrayList<ItemDataObject> list = new ArrayList<>();
		for (int i = start; i < end; i++) {
			ItemDataObject item = new ItemDataObject();
			item.isCanSelected = true;
			item.itemText = new DecimalFormat("00").format(i);
			item.itemValue = item.itemText;
			list.add(item);
		}
		mWheelViewHour.setData(list);
	}

	/**
	 *
	 * @return
     */
	private ArrayList<ItemDataObject> getMinData() {
		ArrayList<ItemDataObject> list = new ArrayList<>();
		for (int i = 0; i < 60 / getMinInterval(); i++) {
			ItemDataObject item = new ItemDataObject();
			item.isCanSelected = true;
			item.itemText = new DecimalFormat("00").format(getMinInterval() * i);
			item.itemValue = item.itemText;
			list.add(item);
		}
		return list;
	}

	/**
	 *
	 * @return
     */
	public int getMinInterval() {
		return mMinInterval;
	}

	/**
	 *
	 * @param minInterval
     */
	public void setMinInterval(int minInterval) {
		this.mMinInterval = minInterval;
		this.onFinishInflate();
	}

	/**
	 *
	 * @param dateChangeListener
     */
	public void setDateChangeListener(OnDateChangeListener dateChangeListener) {
		this.mDateChangeListener = dateChangeListener;
	}

	/**
	 *
	 */
	public interface OnDateChangeListener {
		void onDateChange();
	}
}
