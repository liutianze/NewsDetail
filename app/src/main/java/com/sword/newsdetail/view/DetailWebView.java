package com.sword.newsdetail.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * @author zhengxiaobin@xiaoyouzi.com
 * @since 17/1/19
 */

public class DetailWebView extends WebView {

    private static final String TAG="CustomWebView";
    public DetailWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DetailWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //setOverScrollMode(OVER_SCROLL_NEVER);
    }

}
