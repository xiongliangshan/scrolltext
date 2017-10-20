package com.xls.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/10/19.
 */

public class ScrollTextView extends AppCompatTextView {

    private static final String TAG = "ScrollTextView";
    private float textWidth;
    private float textHeight;
    private float startX;
    private float startY = 0;
    private boolean isStop = false;
    private boolean isStart = false;
    private TextPaint mPaint;
    private CharSequence mText;
    private int scrollTextColor;

    private int mSpeed;

    public final static int SLOW = 1;
    public final static int NORMAL = 2;
    public final static int FAST = 3;

    public ScrollTextView(Context context) {
        super(context);
    }

    public ScrollTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ScrollTextView);
        scrollTextColor = attributes.getColor(R.styleable.ScrollTextView_scrollTextColor, Color.WHITE);
        mSpeed = attributes.getInt(R.styleable.ScrollTextView_scrollSpeed,NORMAL);
        setSingleLine(true);
        mPaint = getPaint();
        mPaint.setColor(scrollTextColor);
        mText = getText().toString();
        Log.d(TAG,"构造方法:  mText = "+mText);
        attributes.recycle();


    }

    public ScrollTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float getBaseLineY(){
        Log.d(TAG,"getBaseLineY");
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        textHeight = fm.bottom - fm.top;
        return getMeasuredHeight()/2 + textHeight/2 - fm.bottom;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        startX = getMeasuredWidth();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        startY = getBaseLineY();
        textWidth = getTextWidth();
        Log.i(TAG,"textWidth = "+textWidth+"textHeight = "+ textHeight+" | startX = "+startX+" | text = "+getText());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(TextUtils.isEmpty(mText)){
            return;
        }

        startX-=mSpeed;
        canvas.drawText(getText().toString(),startX,startY,mPaint);
        if(startX<-textWidth){
            startX = getMeasuredWidth();
        }
        if(!isStart || isStop){
            return;
        }
        invalidate();
    }


    private float getTextWidth(){
        Log.d(TAG,"getTextWidth");
        TextPaint textPaint = this.getPaint();
        String text = this.getText().toString();
        return textPaint.measureText(text);
    }

    public void startScroll(){
        if(View.VISIBLE!=getVisibility()){
            setVisibility(View.VISIBLE);
        }
        isStart = true;
        isStop = false;
        invalidate();

    }

    /**
     * 文字滚动固定的时间段后自动消失
     * @param time
     */
    public void startScrollTimer(long time){
        startScroll();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                stopScroll();
            }
        },time);
    }

    public void pauseScroll(){
        isStop =  true;
    }

    public void stopScroll(){
        isStop =  true;
        startX = getMeasuredWidth();
        setVisibility(View.GONE);
    }


    public int getSpeed() {
        return mSpeed;
    }

    public void setSpeed(int speed) {
        this.mSpeed = speed;
    }


    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if(visibility!=View.VISIBLE){
            if(isStart && !isStop){
                stopScroll();
            }
        }

    }

}
