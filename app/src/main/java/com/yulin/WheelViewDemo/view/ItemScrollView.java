package com.yulin.WheelViewDemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yulin.WheelViewDemo.bean.EmptyWheelViewData;
import com.yulin.WheelViewDemo.bean.IItemScrollViewData;
import com.yulin.WheelViewDemo.R;

import java.util.ArrayList;
import java.util.List;

public class ItemScrollView extends ScrollView {
    public static final String TAG = ItemScrollView.class.getSimpleName();
    /**
     *
     */
    private static final float DEFAULT_FONT_SIZE_NORMAL = 18.0f;
    /**
     *
     */
    private static final float DEFAULT_FONT_SIZE_SELECTED = 18.0f;

    /**
     *
     */
    private static final int DEFAULT_SCROLL_DELAY = 50;
    /**
     *
     */
    private static final int DEFAULT_OFFSET = 1;

    /**
     *
     */
    private static final int DEFAULT_DISPLAY_ITEMS_PER_PAGE = 5;

    /**
     *
     */
    private Context mContext;

    /**
     *
     */
    private Paint mPaint;

    /**
     *
     */
    private int mWidth;

    /**
     *
     */
    private LinearLayout mContentLinearLayout;

    /**
     *
     */
    private List<IItemScrollViewData> mData;

    /**
     *
     */
    private float mFontSizeNormal;

    /**
     *
     */
    private float mFontSizeSelected;

    /**
     *
     */
    private int mTextColorNormal;

    /**
     *
     */
    private int mTextColorSelected;

    /**
     *
     */
    private int mLineColor;

    /**
     *
     */
    private int mItemViewHeight = 0;

    /**
     *
     */
    private int mDisplayItemCountPerPage = DEFAULT_DISPLAY_ITEMS_PER_PAGE;

    /**
     *
     */
    private int mOffset;

    /**
     *
     */
    private int mSelectedIndex = 1;

    /**
     *
     */
    private int mScrollY;

    /**
     *
     */
    private Runnable mScrollerTask;

    /**
     *
     */
    private int[] mSelectedAreaBorders;

    /**
     *
     */
    private OnItemScrollListener mWheelViewListener;

    public ItemScrollView(Context context) {
        this(context, null);
    }

    public ItemScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
        initScrollTask();
    }

    /**
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ItemScrollView);
        mFontSizeNormal =
                a.getDimension(R.styleable.ItemScrollView_textSizeNormal, DEFAULT_FONT_SIZE_NORMAL);
        mFontSizeSelected =
                a.getDimension(R.styleable.ItemScrollView_textSizeSelected, DEFAULT_FONT_SIZE_SELECTED);
        mTextColorNormal = a.getColor(R.styleable.ItemScrollView_textColorNormal,
                ContextCompat.getColor(context.getApplicationContext(), R.color.gray_500));
        mTextColorSelected = a.getColor(R.styleable.ItemScrollView_textColorSelected,
                ContextCompat.getColor(context.getApplicationContext(), R.color.colorPrimary));
        mLineColor = a.getColor(R.styleable.ItemScrollView_itemLineColor,
                ContextCompat.getColor(context.getApplicationContext(), R.color.colorPrimary));
        mOffset = a.getInt(R.styleable.ItemScrollView_offset, DEFAULT_OFFSET);
        a.recycle();

        this.mContext = context;

        mPaint = new Paint();
        mPaint.setColor(mLineColor);
        mPaint.setStrokeWidth(dip2px(1f));

        this.setVerticalScrollBarEnabled(false);

        mContentLinearLayout = new LinearLayout(context);
        mContentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        this.addView(mContentLinearLayout);
    }

    /**
     *
     */
    private void initScrollTask() {
        mScrollerTask = new Runnable() {
            @Override
            public void run() {
                int newY = getScrollY();
                if (mScrollY - newY == 0) {
                    final int remainder = mScrollY % mItemViewHeight;
                    final int divided = mScrollY / mItemViewHeight;
                    if (remainder == 0) {
                        int newIndex = divided + mOffset;
                        if (mSelectedIndex != newIndex) {
                            mSelectedIndex = newIndex;
                            onSelectedCallBack();
                        }
                    } else {
                        if (remainder > mItemViewHeight / 2) {
                            ItemScrollView.this.post(new Runnable() {
                                @Override
                                public void run() {
                                    ItemScrollView.this.smoothScrollTo(0, mScrollY - remainder + mItemViewHeight);
                                    int newIndex = divided + mOffset + 1;
                                    if (mSelectedIndex != newIndex) {
                                        mSelectedIndex = newIndex;
                                        onSelectedCallBack();
                                    }
                                }
                            });
                        } else {
                            ItemScrollView.this.post(new Runnable() {
                                @Override
                                public void run() {
                                    ItemScrollView.this.smoothScrollTo(0, mScrollY - remainder);
                                    int newIndex = divided + mOffset;
                                    if (mSelectedIndex != newIndex) {
                                        mSelectedIndex = newIndex;
                                        onSelectedCallBack();
                                    }
                                }
                            });
                        }
                    }
                } else {
                    mScrollY = getScrollY();
                    ItemScrollView.this.postDelayed(mScrollerTask, DEFAULT_SCROLL_DELAY);
                }
            }
        };
    }

    /**
     *
     * @return
     */
    public List<IItemScrollViewData> getData() {
        return mData;
    }

    /**
     *
     * @param list
     */
    public void setData(List<? extends IItemScrollViewData> list) {
        if (null == mData) {
            mData = new ArrayList<>();
        }

        if(mData.size() > 0) {
            mData.clear();
        }

        mData.addAll(list);

        for (int i = 0; i < mOffset; i++) {
            mData.add(0, EmptyWheelViewData.getInstance());
            mData.add(EmptyWheelViewData.getInstance());
        }

        mDisplayItemCountPerPage = mOffset * 2 + 1;

        mContentLinearLayout.removeAllViews();

        for (IItemScrollViewData item : mData) mContentLinearLayout.addView(generateTextView(item));

        updateItemView(0);
    }

    /**
     *
     * @return
     */
    public int getOffset() {
        return mOffset;
    }

    /**
     *
     * @param offset
     */
    public void setOffset(int offset) {
        this.mOffset = offset;
    }

    /**
     *
     * @param item
     * @return
     */
    private TextView generateTextView(IItemScrollViewData item) {
        TextView tv = new TextView(mContext);
        tv.setLayoutParams(
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setSingleLine(true);
        tv.setEllipsize(TextUtils.TruncateAt.END);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, mFontSizeNormal);
        tv.setText(item.getText());
        tv.setGravity(Gravity.CENTER);
        int padding = dip2px(16);
        tv.setPadding(padding, padding, padding, padding);
        if (0 == mItemViewHeight) {
            mItemViewHeight = getViewMeasuredHeight(tv);
            mContentLinearLayout.setLayoutParams(
                    new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mItemViewHeight * mDisplayItemCountPerPage));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.getLayoutParams();
            this.setLayoutParams(new LinearLayout.LayoutParams(lp.width, mItemViewHeight * mDisplayItemCountPerPage));
        }
        return tv;
    }

    @Override protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        updateItemView(t);
    }

    /**
     *
     * @param top
     */
    private void updateItemView(int top) {
        int position = top / mItemViewHeight + mOffset;
        int remainder = top % mItemViewHeight;
        int divided = top / mItemViewHeight;

        if (remainder == 0) {
            position = divided + mOffset;
        } else {
            if (remainder > mItemViewHeight / 2) {
                position = divided + mOffset + 1;
            }
        }

        int childSize = mContentLinearLayout.getChildCount();
        for (int i = 0; i < childSize; i++) {
            TextView itemView = (TextView) mContentLinearLayout.getChildAt(i);
            if (null == itemView) {
                return;
            }
            if (position == i) {
                itemView.setTextColor(mTextColorSelected);
                itemView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mFontSizeSelected);
            } else {
                itemView.setTextColor(mTextColorNormal);
                itemView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mFontSizeNormal);
            }
        }
    }

    /**
     *
     */
    public void startScrollerTask() {
        mScrollY = getScrollY();
        this.postDelayed(mScrollerTask, DEFAULT_SCROLL_DELAY);
    }

    /**
     *
     * @return
     */
    private int[] obtainSelectedAreaBorder() {
        if (null == mSelectedAreaBorders) {
            mSelectedAreaBorders = new int[2];
            mSelectedAreaBorders[0] = mItemViewHeight * mOffset;
            mSelectedAreaBorders[1] = mItemViewHeight * (mOffset + 1);
        }
        return mSelectedAreaBorders;
    }

    @Override
    public void setBackground(Drawable background) {
           background = new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                canvas.drawLine(0, obtainSelectedAreaBorder()[0], mWidth, obtainSelectedAreaBorder()[0],
                        mPaint);
                canvas.drawLine(0, obtainSelectedAreaBorder()[1], mWidth, obtainSelectedAreaBorder()[1],
                        mPaint);
            }

            @Override public void setAlpha(int alpha) {

            }

            @Override public void setColorFilter(ColorFilter cf) {

            }

            @Override public int getOpacity() {
                return PixelFormat.UNKNOWN;
            }
        };
        super.setBackground(background);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        setBackground(null);
    }

    /**
     *
     */
    private void onSelectedCallBack() {
        if (null != mWheelViewListener) {
            mWheelViewListener.onItemSelected(mSelectedIndex, mData.get(mSelectedIndex));
        }
    }

    /**
     *
     * @param position
     */
    public void setSelection(int position) {
        final int p = position;
        mSelectedIndex = p + mOffset;
        this.post(new Runnable() {
            @Override
            public void run() {
                ItemScrollView.this.smoothScrollTo(0, p * mItemViewHeight);
            }
        });
    }

    /**
     *
     * @param value
     */
    public void setSelectionText(String value) {
        int p = 0;
        for (int index = 0; index < mData.size(); index++) {
            if (mData.get(index).getValue().equals(value)) p = index;
        }
        final int position = p;
        mSelectedIndex = position + mOffset;
        this.post(new Runnable() {
            @Override
            public void run() {
                ItemScrollView.this.smoothScrollTo(0, position * mItemViewHeight);
            }
        });
    }


    /**
     *
     */
    public void triggerSelectedEvent() {
        onSelectedCallBack();
    }

    /**
     *
     * @param value
     */
    public void setSelection(String value) {
        int position = 0;
        for (int i = 0; i < mData.size(); i++) {
            IItemScrollViewData item = mData.get(i);
            if (value.equals(item.getValue())) {
                position = i;
                break;
            }
        }
        if (position > 0) position = position - mOffset;
        setSelection(position);
    }

    /**
     *
     * @return
     */
    public IItemScrollViewData getSeletedItem() {
        return mData.get(mSelectedIndex);
    }

    /**
     *
     * @return
     */
    public int getSeletedIndex() {
        return mSelectedIndex - mOffset;
    }

    @Override public void fling(int velocityY) {
        super.fling(velocityY / 3);
    }

    @Override public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            startScrollerTask();
        }
        return super.onTouchEvent(ev);
    }

    /**
     *
     * @param itemScrollListener
     */
    public void setItemScrollListener(OnItemScrollListener itemScrollListener) {
        this.mWheelViewListener = itemScrollListener;
    }

    /**
     *
     * @param view
     * @return
     */
    private int getViewMeasuredHeight(View view) {
        int width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
        return view.getMeasuredHeight();
    }

    /**
     *
     * @param dpValue
     * @return
     */
    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     *
     */
    public interface OnItemScrollListener {
        void onItemSelected(int selectedIndex, IItemScrollViewData item);
    }
}
