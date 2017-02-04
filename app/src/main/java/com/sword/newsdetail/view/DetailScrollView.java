package com.sword.newsdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.ScrollView;

public class DetailScrollView extends ScrollView {

    private static final String TAG ="DetailScrollView" ;
    private boolean isScrolledToTop = true;// 初始化的时候设置一下值
    private boolean isScrolledToBottom = false;
    private DetailListView mChildListView;
    private int mFlingVelocityY;
    //private Scroller mScroller;
    public DetailScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //mScroller = new Scroller(context);
    }

    private ISmartScrollChangedListener mSmartScrollChangedListener;

    /** 定义监听接口 */
    public interface ISmartScrollChangedListener {
        void onScrolledToBottom(int velocityY);
        void onScrolledToTop();
    }

    public void setScanScrollChangedListener(ISmartScrollChangedListener smartScrollChangedListener) {
        mSmartScrollChangedListener = smartScrollChangedListener;
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        //Log.d(TAG,"----------->onOverScrolled ");
        if (scrollY == 0) {
            isScrolledToTop = clampedY;
            isScrolledToBottom = false;
        } else {
            isScrolledToTop = false;
            isScrolledToBottom = clampedY;
        }
        //getSpeed();
        notifyScrollChangedListeners();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //Log.d(TAG,"------->onScrollChanged ");
        if (android.os.Build.VERSION.SDK_INT < 9) {  // API 9及之后走onOverScrolled方法监听
            if (getScrollY() == 0) {    // 小心踩坑1: 这里不能是getScrollY() <= 0
                isScrolledToTop = true;
                isScrolledToBottom = false;
            } else if (getScrollY() + getHeight() - getPaddingTop()-getPaddingBottom() == getChildAt(0).getHeight()) {
                // 小心踩坑2: 这里不能是 >=// 小心踩坑3（可能忽视的细节2）：这里最容易忽视的就是ScrollView上下的padding　
                isScrolledToBottom = true;
                isScrolledToTop = false;
            } else {
                isScrolledToTop = false;
                isScrolledToBottom = false;
            }
            notifyScrollChangedListeners();

        }
        //getSpeed();
        // 有时候写代码习惯了，为了兼容一些边界奇葩情况，上面的代码就会写成<=,>=的情况，结果就出bug了
        // 我写的时候写成这样：getScrollY() + getHeight() >= getChildAt(0).getHeight()
        // 结果发现快滑动到底部但是还没到时，会发现上面的条件成立了，导致判断错误
        // 原因：getScrollY()值不是绝对靠谱的，它会超过边界值，但是它自己会恢复正确，导致上面的计算条件不成立
        // 仔细想想也感觉想得通，系统的ScrollView在处理滚动的时候动态计算那个scrollY的时候也会出现超过边界再修正的情况
    }

    private void notifyScrollChangedListeners() {
        if (isScrolledToTop) {
            if (mSmartScrollChangedListener != null) {
                mSmartScrollChangedListener.onScrolledToTop();
            }
        } else if (isScrolledToBottom) {
            getSpeed();
            if (mSmartScrollChangedListener != null) {
                mSmartScrollChangedListener.onScrolledToBottom(mFlingVelocityY);
            }
        }
    }

    @Override
    protected int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override
    protected int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    @Override
    protected int computeVerticalScrollOffset() {
        return super.computeVerticalScrollOffset();
    }

    private float fLastRawY,fMoveRawY;
    private boolean isMoving = false;
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
                    acquireVelocityTracker(ev);
                    Log.d(TAG,"------->onTouchEvent ACTION_DOWN->y:" + ev.getRawY());
                    fLastRawY = ev.getRawY();
                    isMoving = false;
                    if(mMoveListener!=null){
                        mMoveListener.onDown();
                    }
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    acquireVelocityTracker(ev);
                    if(fLastRawY==0){
                        fLastRawY = ev.getRawY();
                    }
                    fMoveRawY = ev.getRawY();
                    float fCount = fMoveRawY - fLastRawY;
                    Log.d(TAG,"------->onTouchEvent ACTION_MOVE  滑动距离:"+fCount);
                    if(getMoveListener()!=null){
                        getMoveListener().onMove(fCount);
                        isMoving = true;
                        fLastRawY = fMoveRawY;
                    }
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    acquireVelocityTracker(ev);
                    /*if(getChildListView()!=null){
                        getChildListView().onTouchUp(ev);
                    }*/
                    Log.d(TAG,"------->onTouchEvent ACTION_UP");
                    isMoving =false;
                    fLastRawY=0;
                    if(mMoveListener!=null){
                        getSpeed();
                        mMoveListener.onUp(mFlingVelocityY);
                    }
                    releaseVelocityTracker();
                    break;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return super.onTouchEvent(ev);
    }

    public DetailListView getChildListView() {
        return mChildListView;
    }

    public void setChildListView(DetailListView childListView) {
        mChildListView = childListView;
    }


    private void getSpeed(){
        try {
           if(mVelocityTracker!=null){
               final VelocityTracker velocityTracker = mVelocityTracker;
               velocityTracker.computeCurrentVelocity(1000);
               int initialVelocity = (int)velocityTracker.getYVelocity(0);
               Log.d(TAG,"------->速度是:"+initialVelocity);
               mFlingVelocityY = initialVelocity;
           }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param event 向VelocityTracker添加MotionEvent
     *
     * @see android.view.VelocityTracker#obtain()
     * @see android.view.VelocityTracker#addMovement(MotionEvent)
     */
    private void acquireVelocityTracker(final MotionEvent event) {
        if(null == mVelocityTracker) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    /**
     * 释放VelocityTracker
     *
     * @see android.view.VelocityTracker#clear()
     * @see android.view.VelocityTracker#recycle()
     */
    private void releaseVelocityTracker() {
        if(null != mVelocityTracker) {
            //mVelocityTracker.clear();
            //mVelocityTracker.recycle();
            //mVelocityTracker = null;
        }
    }


    private onMoveListener mMoveListener;
    public interface onMoveListener{
        public void onDown();
        public void onUp(int velocityY);
        public void onMove(float distance);
    }
    public onMoveListener getMoveListener() {
        return mMoveListener;
    }
    public void setMoveListener(onMoveListener moveListener) {
        mMoveListener = moveListener;
    }

}