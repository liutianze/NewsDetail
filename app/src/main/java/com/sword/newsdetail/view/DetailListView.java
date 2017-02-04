package com.sword.newsdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Author: lwh
 * Date: 1/23/17 14:08.
 */

public class DetailListView extends ListView implements /*View.OnTouchListener,*/ AbsListView.OnScrollListener {

    private static final String TAG = "DetailListView";
    private int listViewTouchAction;
    private static final int MAXIMUM_LIST_ITEMS_VIEWABLE = 10000;
    private Scroller mScroller;
    //private ViewGroup mParentView;

    public DetailListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        listViewTouchAction = -1;
        setOnScrollListener(this);
        initFlingRunable();
        //setOnTouchListener(this);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (getAdapter() != null && getAdapter().getCount() > MAXIMUM_LIST_ITEMS_VIEWABLE) {
            if (listViewTouchAction == MotionEvent.ACTION_MOVE) {
                scrollBy(0, -1);
            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    private float fLastRawY,fMoveRawY;
    private boolean isMoving =false;
    private VelocityTracker mVelocityTracker;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try{
            if (mVelocityTracker == null) {
                //检查速度测量器，如果为null，获得一个
                mVelocityTracker = VelocityTracker.obtain();
            }
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    Log.d(TAG,"------->onTouchEvent ACTION_DOWN->y:" + ev.getRawY());
                    fLastRawY = ev.getRawY();
                    isMoving = false;
                    if (!mScroller.isFinished()) { //fling
                        mScroller.abortAnimation();
                    }
                    mVelocityTracker.clear();
                    mVelocityTracker.addMovement(ev);
                    //break;
                    return super.onTouchEvent(ev);//为了列表项可以点击
                }
                case MotionEvent.ACTION_MOVE: {
                    mVelocityTracker.addMovement(ev);
                    fMoveRawY = ev.getRawY();
                    float fCount = fMoveRawY - fLastRawY;
                    if(getMoveListener()!=null){
                        getMoveListener().onMove(fCount);
                        isMoving = true;
                        fLastRawY = fMoveRawY;
                    }
                    Log.d(TAG,"------->onTouchEvent ACTION_MOVE  滑动距离:" + fCount+"==>getChildAt(0).getTop():"+getChildAt(0).getTop());

                    break;
                }

                case MotionEvent.ACTION_UP: {
                    Log.d(TAG,"------->onTouchEvent ACTION_UP");
                    //mVelocityTracker.addMovement(ev);
                    final VelocityTracker velocityTracker = mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000);
                    int initialVelocity = (int)velocityTracker.getYVelocity(0);
                    Log.d(TAG,"------->速度是:"+initialVelocity);
                    if(mMoveListener!=null && isMoving){
                        mMoveListener.onUp(initialVelocity);
                        isMoving = false;
                    }
                    //break;
                    return super.onTouchEvent(ev);//为了让按下效果可以消失
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        //不处理事件了
        if(!isHandleTouchEvent()){
            return false;
        }
        return super.onTouchEvent(ev);
    }



    private void doFling(int speed) {
        if (mScroller == null) {
            return;
        }
        mScroller.fling(0,getScrollY(),0,speed,0,0,-500,10000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
    /**
     * 滑动监听器
     */
    private onMoveListener mMoveListener;
    public interface onMoveListener{
        public void onMove(float distance);
        public void onUp(int velocityY);
    }
    public onMoveListener getMoveListener() {
        return mMoveListener;
    }
    public void setMoveListener(onMoveListener moveListener) {
        mMoveListener = moveListener;
    }


    private boolean isHandleTouchEvent=false;
    public boolean isHandleTouchEvent() {
        return isHandleTouchEvent;
    }
    public void setHandleTouchEvent(boolean handleTouchEvent) {
        isHandleTouchEvent = handleTouchEvent;
    }

    private Field mFlingRunnableField = null;
    private Method mFlingStart = null;
    private Method mFlingEnd = null;
    private Method reportScrollStateChange;
    private Method mFling;

    //放于初始化方法中被调用
    public void initFlingRunable() {
        try {
            //if(mFlingEndField==null){
            mFlingRunnableField = AbsListView.class.getDeclaredField("mFlingRunnable");
            mFlingRunnableField.setAccessible(true);
            mFlingStart = mFlingRunnableField.getType().getDeclaredMethod("start", int.class);//mFlingEndField.getType().getDeclaredMethod("start");
            mFlingStart.setAccessible(true);

            mFlingEnd = mFlingRunnableField.getType().getDeclaredMethod("endFling");//mFlingEndField.getType().getDeclaredMethod("start");
            mFlingEnd.setAccessible(true);

            reportScrollStateChange = AbsListView.class.getDeclaredMethod("reportScrollStateChange", int.class);
            reportScrollStateChange.setAccessible(true);

            mFling = AbsListView.class.getDeclaredMethod("fling", int.class);
            mFling.setAccessible(true);
            //}
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void startFling(int y) {
        try {
            mFlingStart.invoke(mFlingRunnableField.get(this));
            reportScrollStateChange.invoke(this, AbsListView.OnScrollListener.SCROLL_STATE_FLING);
            mFlingStart.invoke(mFlingRunnableField.get(this), y);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /*try {
            if(mFling!=null){
                mFling.invoke(this,y);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }*/

    }

    public void stopFling(){
        try {
            if(mFlingEnd!=null)
                mFlingEnd.invoke(mFlingRunnableField.get(this));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}/*extends ListView {

    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context) {
        super(context);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      *//*  //设置为Integer.MAX_VALUE>>2 是listview全部展开
        int measureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
//设置为400是设置listview的高度只能有400 不全部展开   实现可以滑动的效果
        int measureSpec1 = MeasureSpec.makeMeasureSpec(400, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, measureSpec1);*//*
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >>2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}*/