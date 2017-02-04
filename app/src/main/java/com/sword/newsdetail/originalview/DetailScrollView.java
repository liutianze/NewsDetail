/*
package com.seeker.tony.myapplication.view;

import android.view.ViewGroup;

*/
/**
 * Author: lwh
 * Date: 2/3/17 17:39.
 *//*



import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.j;
import com.seeker.tony.myapplication.R;
import com.ss.android.article.base.ui.MyWebViewV9;
import com.ss.android.article.news.R.id;
import com.ss.android.common.lib.MobClickCombiner;
import com.ss.android.common.util.LoadUrlUtils;

public class DetailScrollView
        extends ViewGroup {
    private static final Interpolator o = new a();
    private boolean A = false;
    private boolean B = false;
    private int C;
    private int D;
    private int E;
    private boolean F = false;
    private boolean G = false;
    private boolean H = false;
    private boolean I;
    private boolean J;
    private int K;
    private float L = 1.0F;
    Scroller a;
    WebView b;
    WebView c;
    ListView d;
    RelativeLayout e;
    View f;
    View g;
    View h;
    public boolean i = true;
    a j;
    int k;
    int l;
    boolean m = false;
    Runnable n = new e(this);
    private float p;
    private int q = 1;
    private boolean r = false;
    private int s;
    private boolean t = false;
    private int u = 300;
    private int v;
    private int w;
    private int x;
    private VelocityTracker y;
    private int z = -1;

    public DetailScrollView(Context paramContext) {
        super(paramContext);
        init(paramContext);
    }

    public DetailScrollView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init(paramContext);
    }

    public DetailScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        init(paramContext);
    }

    private void init(Context paramContext) {
        setMotionEventSplittingEnabled(false);
        ViewConfiguration localViewConfiguration = ViewConfiguration.get(paramContext);
        this.C = localViewConfiguration.getScaledTouchSlop();
        this.E = localViewConfiguration.getScaledMaximumFlingVelocity();
        this.D = localViewConfiguration.getScaledMinimumFlingVelocity();
        this.k = ((int) j.b(paramContext, 80.0F));
        this.l = ((int) j.b(paramContext, 3.0F));
        this.p = (300.0F / j.b(paramContext, 420.0F));
    }


    private void a(boolean paramBoolean1, boolean paramBoolean2, int paramInt) {
        boolean bool = false;
        if (this.q == 2) {
            return;
        }
        this.a.abortAnimation();
        b(paramBoolean2);
        if ((!paramBoolean2) && (this.d != null) && (this.d.getVisibility() == 0)) {
            this.d.setSelection(0);
        }
        if (paramBoolean1) {
            a(paramInt);
        }
        for (; ; ) {
            paramBoolean1 = bool;
            if (!paramBoolean2) {
                paramBoolean1 = true;
            }
            this.i = paramBoolean1;
            if (this.j == null) {
                break;
            }
            this.j.a(paramBoolean2);
            return;
            scrollBy(0, paramInt);
        }
    }

    private boolean a(View paramView) {
        return (paramView != null) && ((paramView == this.b) || (paramView == this.e) || (paramView == this.f));
    }

    private void b(MotionEvent paramMotionEvent) {
        int i1 = paramMotionEvent.getActionIndex();
        if (paramMotionEvent.getPointerId(i1) == this.z) {
            if (i1 != 0) {
                break label57;
            }
        }
        label57:
        for (i1 = 1; ; i1 = 0) {
            this.v = ((int) paramMotionEvent.getY(i1));
            this.z = paramMotionEvent.getPointerId(i1);
            if (this.y != null) {
                this.y.clear();
            }
            return;
        }
    }

    private int c(int paramInt) {
        int i1 = paramInt;
        if (this.b != null) {
            int i2 = this.b.getLayoutParams().height;
            i1 = paramInt;
            if (i2 > 0) {
                i1 = i2;
            }
        }
        return i1;
    }

    private void c(boolean paramBoolean) {
        if (this.A) {
        }
        while ((!this.m) || (this.z == -1)) {
            return;
        }
        if (paramBoolean) {
            scrollTo(0, getWebViewHeight() - this.l);
        }
        for (; ; ) {
            Logger.d("DetailScrollView", "startDragAsClampedY:" + paramBoolean);
            this.B = true;
            return;
            scrollTo(0, this.l);
        }
    }

    private void e() {
        if (this.y == null) {
            this.y = VelocityTracker.obtain();
            return;
        }
        this.y.clear();
    }

    private void f() {
        if (this.y == null) {
            this.y = VelocityTracker.obtain();
        }
    }

    private void g() {
        if (this.y != null) {
            this.y.recycle();
            this.y = null;
        }
    }

    private int getCappedCurVelocity() {
        return Math.min((int) this.a.getCurrVelocity(), this.E);
    }

    private int getScrollRange() {
        return getWebViewHeight() * 2;
    }

    private int getWebViewHeight() {
        return c(getHeight());
    }

    private void h() {
        this.t = true;
        this.s = getScrollY();
        postDelayed(this.n, this.u);
    }

    private void i() {
        this.A = false;
        g();
    }

    public void a(int paramInt) {
        int i1 = 200;
        int i3 = getScrollX();
        int i4 = getScrollY();
        int i2 = (int) (Math.abs(paramInt) * this.p);
        if (i2 < 200) {
        }
        for (; ; ) {
            if (this.H) {
                i1 = 0;
            }
            for (; ; ) {
                this.a.startScroll(i3, i4, 0, paramInt, i1);
                ViewCompat.postInvalidateOnAnimation(this);
                h();
                return;
                if (i2 <= 350) {
                    break label84;
                }
                i1 = 350;
                break;
            }
            label84:
            i1 = i2;
        }
    }

    void a(View paramView, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3) {
        if ((!this.m) || (this.H) || (this.G) || (Build.VERSION.SDK_INT < 9) || (!this.a.isFinished())) {
        }
        do {
            do {
                return;
                if (paramView == this.b) {
                    if ((getScrollY() == 0) && (paramView.getScrollY() >= 0) && (paramBoolean) && (paramInt2 > 0)) {
                        c(false);
                        this.i = true;
                    }
                    this.w = paramInt3;
                    return;
                }
            } while ((paramView != this.d) || (getScrollY() != getWebViewHeight()));
            if (paramInt1 < 0) {
                this.i = false;
                c(true);
                return;
            }
        } while ((paramView.getScrollY() != 0) || (!paramBoolean) || (paramInt2 >= 0));
        c(true);
        this.i = false;
    }

    public void a(View paramView1, View paramView2) {
        this.g = paramView1;
        this.h = paramView2;
    }

    public boolean a() {
        return a(true);
    }

    boolean a(boolean paramBoolean) {
        int i1 = getScrollY();
        boolean bool;
        if (i1 < getWebViewHeight() / 2) {
            bool = true;
            if (!bool) {
                break label57;
            }
            i1 = getWebViewHeight() - i1;
            label28:
            if (!bool) {
                break label63;
            }
        }
        label57:
        label63:
        for (String str = "handle_open_drawer"; ; str = "handle_close_drawer") {
            a(str);
            a(paramBoolean, bool, i1);
            return bool;
            bool = false;
            break;
            i1 = -i1;
            break label28;
        }
    }

    public void b() {
        if (getVisibility() != 0) {
        }
        int i1;
        do {
            return;
            i1 = getScrollY();
        } while (i1 <= 20);
        a(true, false, -i1);
    }

    protected void b(int paramInt) {
        if ((getChildCount() <= 0) || (this.H) || (this.G)) {
            return;
        }
        int i3 = getScrollX();
        int i4 = getScrollY();
        int i1;
        if (paramInt > 0) {
            paramInt = Math.min(paramInt, this.E);
            if ((!(this.d instanceof MyListViewV9)) || (!((MyListViewV9) this.d).b())) {
                break label167;
            }
            i1 = Integer.MAX_VALUE;
            label74:
            if (this.b.getVisibility() != 0) {
                break label179;
            }
        }
        label167:
        label179:
        for (int i2 = -(int) (this.b.getContentHeight() * this.b.getScale()); ; i2 = 0) {
            this.a.fling(i3, i4, 0, paramInt, 0, 0, i2, i1);
            ViewCompat.postInvalidateOnAnimation(this);
            h();
            Logger.d("DetailScrollView", "start fling, velocityY = " + paramInt);
            return;
            paramInt = Math.max(paramInt, -this.E);
            break;
            i1 = Math.max(0, getWebViewHeight());
            break label74;
        }
    }

    public void b(boolean paramBoolean) {
        int i2;
        int i1;
        if ((this.b != null) && (this.b.getVisibility() == 0)) {
            if (this.w <= 0) {
                break label150;
            }
            i2 = 1;
            if (!paramBoolean) {
                break label176;
            }
            if (i2 == 0) {
                break label155;
            }
            i1 = this.w;
            label39:
            this.x = this.b.getScrollY();
            label50:
            if ((i2 != 0) || (!this.b.getSettings().getJavaScriptEnabled())) {
                break label184;
            }
            StringBuilder localStringBuilder = new StringBuilder("javascript:window.scrollTo(");
            localStringBuilder.append(this.b.getScrollX());
            localStringBuilder.append(",");
            localStringBuilder.append(i1);
            localStringBuilder.append(");");
            LoadUrlUtils.loadUrl(this.b, localStringBuilder.toString());
        }
        for (; ; ) {
            if ((this.b instanceof MyWebViewV9)) {
                ((MyWebViewV9) this.b).b();
            }
            return;
            label150:
            i2 = 0;
            break;
            label155:
            i1 = (int) (this.b.getContentHeight() * this.b.getScale());
            break label39;
            label176:
            i1 = this.x;
            break label50;
            label184:
            this.b.scrollTo(this.b.getScrollX(), i1);
        }
    }

    public void c() {
        if (getVisibility() != 0) {
            return;
        }
        int i1 = getScrollY();
        a("handle_open_drawer");
        a(true, true, getWebViewHeight() - i1);
    }

    protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
        return paramLayoutParams instanceof ViewGroup.MarginLayoutParams;
    }

    public void computeScroll() {
        if ((!this.a.isFinished()) && (this.a.computeScrollOffset())) {
            i1 = getWebViewHeight();
            i2 = getScrollX();
            i3 = getScrollY();
            i4 = this.a.getCurrX();
            i5 = this.a.getCurrY();
            if ((i5 > i3) && (i3 >= i1) && ((this.d instanceof MyListViewV9))) {
                if (this.d.getVisibility() != 0) {
                    i5 = Math.max(0, Math.min(i5, i1));
                    if ((i2 != i4) || (i3 != i5)) {
                        scrollTo(i4, i5);
                        if (i5 == i1) {
                            this.i = false;
                        }
                    }
                    ViewCompat.postInvalidateOnAnimation(this);
                    if ((this.I) && (this.c != null)) {
                        this.c.setDetectContentSize(false);
                    }
                }
            }
        }
        while ((!this.I) || (this.c == null)) {
            int i3;
            int i6;
            do {
                do {
                    int i5;
                    do {
                        do {
                            do {
                                int i2;
                                int i4;
                                return;
                                i6 = getCappedCurVelocity();
                            } while ((i6 == 0) || (!((MyListViewV9) this.d).a(i6)));
                            this.a.forceFinished(true);
                        } while (!Logger.debug());
                        Logger.d("DetailScrollView", "computeScroll, abort fling, ListView start fling, velocity = " + i6 + ", scrollY = " + i3);
                        return;
                    } while ((i5 >= i3) || (i3 > 0) || (this.b.getVisibility() != 0));
                    i6 = getCappedCurVelocity();
                } while (i6 == 0);
                this.a.forceFinished(true);
                this.b.flingScroll(0, -i6);
            } while (!Logger.debug());
            Logger.d("DetailScrollView", "computeScroll, abort fling, WebView start fling, velocity = " + -i6 + ", scrollY = " + i3);
            return;
        }
        int i1 = getScrollY();
        MyWebViewV9 localMyWebViewV9 = this.c;
        if ((this.q == 1) && (this.c.getVisibility() == 0) && (i1 > 0) && (i1 < getWebViewHeight())) {
        }
        for (boolean bool = true; ; bool = false) {
            localMyWebViewV9.setDetectContentSize(bool);
            return;
        }
    }

    protected int computeVerticalScrollExtent() {
        if ((this.J) && (this.c != null)) {
            int i2 = this.c.computeVerticalScrollExtent();
            int i1 = i2;
            if ((this.d instanceof MyListViewV9)) {
                float f1 = i2;
                i1 = (int) (((MyListViewV9) this.d).computeVerticalScrollExtent() * this.L + f1);
            }
            return i1;
        }
        return super.computeVerticalScrollExtent();
    }

    protected int computeVerticalScrollOffset() {
        if ((this.J) && (this.c != null)) {
            int i2 = this.c.computeVerticalScrollOffset();
            int i1 = i2;
            if (getScrollY() > 0) {
                i1 = i2;
                if ((this.d instanceof MyListViewV9)) {
                    float f1 = i2;
                    i1 = (int) (((MyListViewV9) this.d).computeVerticalScrollOffset() * this.L + f1);
                }
            }
            return i1;
        }
        return super.computeVerticalScrollOffset();
    }

    protected int computeVerticalScrollRange() {
        if ((this.J) && (this.c != null)) {
            int i2 = this.c.getComputedVerticalScrollRange();
            int i1 = i2;
            if ((this.d instanceof MyListViewV9)) {
                float f1 = i2;
                i1 = (int) (((MyListViewV9) this.d).computeVerticalScrollRange() * this.L + f1);
            }
            return i1;
        }
        return super.computeVerticalScrollRange();
    }

    public boolean d() {
        return this.t;
    }

    public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
        if (paramMotionEvent.getActionMasked() == 0) {
            this.K = 0;
        }
        boolean bool;
        try {
            bool = super.dispatchTouchEvent(paramMotionEvent);
            switch (paramMotionEvent.getAction()) {
                case 2:
                default:
                    return bool;
            }
        } catch (Exception localException) {
            for (; ; ) {
                localException.printStackTrace();
                bool = false;
            }
            this.m = true;
            return bool;
        }
        this.m = false;
        return bool;
    }

    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -1);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), paramAttributeSet);
    }

    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
        return new ViewGroup.MarginLayoutParams(paramLayoutParams.width, paramLayoutParams.height);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = new Scroller(getContext());
        this.b = ((WebView) findViewById(R.id.top_webview));
        this.d = ((ListView) findViewById(R.id.bottom_listview));
        this.e = ((RelativeLayout) findViewById(R.id.bottom_listview_layout));
        this.f = findViewById(R.id.night_mode_overlay);
        if (Build.VERSION.SDK_INT >= 9) {
            if ((this.b instanceof MyWebViewV9)) {
                this.c = ((MyWebViewV9) this.b);
                this.c.setOnOverScrolledListener(new b(this));
                this.c.a();
                this.c.setContentSizeChangeListener(new c(this));
            }
            if ((this.d instanceof MyListViewV9)) {
                ((MyListViewV9) this.d).setOnOverScrolledListener(new d(this));
                ((MyListViewV9) this.d).c();
            }
        }
        setLayoutType(this.q);
    }

    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
        boolean bool2 = true;
        boolean bool1 = true;
        if ((this.H) || (this.G) || (this.r) || (Build.VERSION.SDK_INT < 9)) {
            bool1 = false;
        }
        do {
            return bool1;
            a(paramMotionEvent);
        } while ((paramMotionEvent.getActionMasked() == 2) && (this.A));
        switch (paramMotionEvent.getActionMasked()) {
        }
        for (; ; ) {
            return this.A;
            int i2 = this.z;
            if (i2 != -1) {
                int i1 = paramMotionEvent.findPointerIndex(i2);
                if (i1 == -1) {
                    Logger.w("DetailScrollView", "Invalid pointerId=" + i2 + " in onInterceptTouchEvent");
                } else {
                    i2 = (int) paramMotionEvent.getX(i1);
                    i2 = (int) paramMotionEvent.getY(i1);
                    int i3 = Math.abs(i2 - this.v);
                    if ((!this.A) && (this.a.isFinished())) {
                        int i4 = getScrollY();
                        if (i2 > this.b.getBottom() - i4) {
                        }
                        for (i1 = 1; ; i1 = 0) {
                            if ((i4 != 0) || (i1 != 0)) {
                                break label254;
                            }
                            this.v = i2;
                            break;
                        }
                        label254:
                        if (i4 == getWebViewHeight()) {
                            if ((i3 > this.C) && (i2 > this.v) && (this.d.getScrollY() == 0) && (this.g != null) && (this.g.getTop() == 0) && (this.g.getParent() == this.d)) {
                                this.B = true;
                                if (Logger.debug()) {
                                    Logger.v("DetailScrollView", "force pendingDrag " + i2 + " " + this.v);
                                }
                            }
                            if (!this.B) {
                                continue;
                            }
                        }
                    }
                    if ((!this.A) && (this.B)) {
                        e();
                        this.y.addMovement(paramMotionEvent);
                        this.A = true;
                        this.v = i2;
                        paramMotionEvent = getParent();
                        if (paramMotionEvent != null) {
                            paramMotionEvent.requestDisallowInterceptTouchEvent(true);
                        }
                    } else {
                        this.B = false;
                        if (i3 > this.C) {
                            this.A = true;
                            this.v = i2;
                            f();
                            this.y.addMovement(paramMotionEvent);
                            paramMotionEvent = getParent();
                            if (paramMotionEvent != null) {
                                paramMotionEvent.requestDisallowInterceptTouchEvent(true);
                                continue;
                                this.B = false;
                                this.v = ((int) paramMotionEvent.getY());
                                this.z = paramMotionEvent.getPointerId(0);
                                e();
                                this.y.addMovement(paramMotionEvent);
                                if (!this.a.isFinished()) {
                                }
                                for (bool1 = bool2; ; bool1 = false) {
                                    this.A = bool1;
                                    break;
                                }
                                this.B = false;
                                this.A = false;
                                this.z = -1;
                                g();
                                continue;
                                i1 = paramMotionEvent.getActionIndex();
                                this.v = ((int) paramMotionEvent.getY(i1));
                                this.z = paramMotionEvent.getPointerId(i1);
                                continue;
                                b(paramMotionEvent);
                                this.v = ((int) paramMotionEvent.getY(paramMotionEvent.findPointerIndex(this.z)));
                            }
                        }
                    }
                }
            }
        }
    }

    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i1 = getChildCount();
        paramInt3 -= paramInt1;
        paramInt2 = paramInt4 - paramInt2;
        paramInt1 = 0;
        if (paramInt1 < i1) {
            View localView = getChildAt(paramInt1);
            if ((localView == this.b) || (localView == this.f)) {
                localView.layout(0, 0, paramInt3, c(paramInt2));
            }
            for (; ; ) {
                paramInt1 += 1;
                break;
                if (localView == this.e) {
                    if (this.q == 2) {
                        localView.layout(0, 0, paramInt3, paramInt2);
                    } else {
                        localView.layout(0, c(paramInt2), paramInt3, c(paramInt2) + paramInt2);
                    }
                } else {
                    localView.layout(0, 0, 0, 0);
                }
            }
        }
        if (this.F) {
            this.F = false;
            post(new f(this));
        }
    }

    protected void onMeasure(int paramInt1, int paramInt2) {
        int i3 = 0;
        int i4 = 0;
        int i1 = View.MeasureSpec.getSize(paramInt1);
        paramInt1 = View.MeasureSpec.getMode(paramInt1);
        int i2 = View.MeasureSpec.getSize(paramInt2);
        paramInt2 = View.MeasureSpec.getMode(paramInt2);
        if ((paramInt1 == 0) || (paramInt2 == 0)) {
            paramInt1 = 0;
            paramInt2 = i4;
        }
        for (; ; ) {
            setMeasuredDimension(paramInt2, paramInt1);
            return;
            paramInt2 = View.MeasureSpec.makeMeasureSpec(i1, 1073741824);
            i4 = View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
            int i5 = getChildCount();
            paramInt1 = i3;
            while (paramInt1 < i5) {
                View localView = getChildAt(paramInt1);
                if (a(localView)) {
                    localView.measure(paramInt2, i4);
                }
                paramInt1 += 1;
            }
            paramInt2 = i1;
            paramInt1 = i2;
        }
    }

    public void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
        if (this.j != null) {
            this.j.a(paramInt2);
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent paramMotionEvent) {
        int i1 = 0;
        if ((this.H) || (this.G) || (Build.VERSION.SDK_INT < 9)) {
            return false;
        }
        a(paramMotionEvent);
        f();
        this.y.addMovement(paramMotionEvent);
        int i3;
        int i4;
        int i5;
        switch (paramMotionEvent.getActionMasked()) {
            case 4:
            default:
            case 0:
            case 2:
                int i6;
                label466:
                label476:
                label497:
                label562:
                do {
                    for (; ; ) {
                        return true;
                        this.B = false;
                        if (getChildCount() == 0) {
                            break;
                        }
                        if (this.a.isFinished()) {
                            i1 = getScrollY();
                            if ((i1 == 0) || (i1 == getWebViewHeight())) {
                                this.A = false;
                                return false;
                            }
                        }
                        if (!this.a.isFinished()) {
                        }
                        for (boolean bool = true; ; bool = false) {
                            this.A = bool;
                            if (bool) {
                                ViewParent localViewParent = getParent();
                                if (localViewParent != null) {
                                    localViewParent.requestDisallowInterceptTouchEvent(true);
                                }
                            }
                            if (!this.a.isFinished()) {
                                this.a.abortAnimation();
                            }
                            this.v = ((int) paramMotionEvent.getY());
                            this.z = paramMotionEvent.getPointerId(0);
                            break;
                        }
                        i1 = paramMotionEvent.findPointerIndex(this.z);
                        if (i1 == -1) {
                            Logger.w("DetailScrollView", "Invalid pointerId=" + this.z + " in onTouchEvent");
                        } else {
                            i3 = (int) paramMotionEvent.getY(i1);
                            i2 = this.v - i3;
                            i1 = i2;
                            if (!this.A) {
                                i1 = i2;
                                if (Math.abs(i2) > this.C) {
                                    paramMotionEvent = getParent();
                                    if (paramMotionEvent != null) {
                                        paramMotionEvent.requestDisallowInterceptTouchEvent(true);
                                    }
                                    this.A = true;
                                    if (i2 <= 0) {
                                        break label466;
                                    }
                                }
                            }
                            for (i1 = i2 - this.C; this.A; i1 = i2 + this.C) {
                                this.B = false;
                                this.v = i3;
                                i3 = getScrollY();
                                i2 = getScrollRange();
                                i4 = i3 + i1;
                                if (((i1 > 0) && (i4 >= i2)) || ((i1 < 0) && (i4 <= 0))) {
                                    this.y.clear();
                                }
                                i5 = getWebViewHeight();
                                i6 = this.b.getScrollY();
                                if (i1 >= 0) {
                                    break label562;
                                }
                                i2 = i1;
                                if (this.K >= 0) {
                                    break label497;
                                }
                                if (i1 < this.K) {
                                    break label476;
                                }
                                this.K -= i1;
                                this.d.setSelectionFromTop(0, this.K);
                                break;
                            }
                            i2 = i1 - this.K;
                            this.d.setSelectionFromTop(0, 0);
                            this.K = 0;
                            i1 = i2;
                            if (i4 < 0) {
                                i1 = 0 - i3;
                            }
                            if (i1 != 0) {
                                scrollBy(0, i1);
                            }
                            if ((i1 == 0) && (i2 < 0) && (i3 == 0) && (i6 > 0)) {
                                i1 = i2;
                                if (i2 < -i6) {
                                    i1 = -i6;
                                }
                                this.b.scrollBy(0, i1);
                            }
                        }
                    }
                } while (i1 <= 0);
                if ((i3 == 0) && (this.c != null)) {
                    i2 = this.c.getComputedVerticalScrollRange();
                    if (i6 + i5 < i2) {
                        if (i1 + i6 + i5 > i2) {
                            i2 = i2 - i6 - i5;
                            label614:
                            this.b.scrollBy(0, i2);
                        }
                    }
                }
                break;
        }
        for (int i2 = 1; i2 == 0; i2 = 0) {
            if (i4 > i5) {
                i1 = i5 - i3;
                this.K -= i4 - i5;
                this.d.setSelectionFromTop(0, this.K);
            }
            if (i1 != 0) {
                scrollBy(0, i1);
            }
            if (i1 + i3 != getWebViewHeight()) {
                break;
            }
            this.i = false;
            break;
            this.B = false;
            if (!this.A) {
                break;
            }
            paramMotionEvent = this.y;
            paramMotionEvent.computeCurrentVelocity(1000, this.E);
            i3 = (int) paramMotionEvent.getYVelocity(this.z);
            if (getChildCount() > 0) {
                i4 = getScrollY();
                i2 = getWebViewHeight();
                if (i4 != 0) {
                    break label964;
                }
                i4 = this.b.getScrollY();
                if (i3 <= 0) {
                    break label867;
                }
                if (i4 + i2 < this.c.getComputedVerticalScrollRange()) {
                    this.b.flingScroll(0, -i3);
                    i1 = 1;
                }
            }
            for (; ; ) {
                i2 = i1;
                if (i1 == 0) {
                    i2 = i1;
                    if (this.K != 0) {
                        i2 = i1;
                        if ((this.d instanceof MyListViewV9)) {
                            ((MyListViewV9) this.d).a(-i3);
                            i2 = 1;
                        }
                    }
                }
                if (i2 == 0) {
                    b(-i3);
                }
                this.z = -1;
                i();
                break;
                label867:
                if (i4 > 0) {
                    this.b.flingScroll(0, -i3);
                    i1 = 1;
                    continue;
                    this.B = false;
                    if (!this.A) {
                        break;
                    }
                    this.z = -1;
                    i();
                    break;
                    i1 = paramMotionEvent.getActionIndex();
                    this.v = ((int) paramMotionEvent.getY(i1));
                    this.z = paramMotionEvent.getPointerId(i1);
                    break;
                    b(paramMotionEvent);
                    this.v = ((int) paramMotionEvent.getY(paramMotionEvent.findPointerIndex(this.z)));
                    break;
                }
                label964:
                i1 = 0;
            }
            i2 = i1;
            break label614;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean paramBoolean) {
    }

    public void setDisableInfoLayer(boolean paramBoolean) {
        this.G = paramBoolean;
        if (this.G) {
            setVerticalScrollBarEnabled(false);
        }
    }

    public void setDisableScrollOver(boolean paramBoolean) {
        this.H = paramBoolean;
        if (this.H) {
            setVerticalScrollBarEnabled(false);
        }
    }

    public void setDisallowIntercept(boolean paramBoolean) {
        this.r = paramBoolean;
        if (paramBoolean) {
            setVerticalScrollBarEnabled(false);
        }
    }

    public void setEnableDetectContentSizeChange(boolean paramBoolean) {
        this.I = paramBoolean;
        if ((!paramBoolean) && (this.c != null)) {
            this.c.setDetectContentSize(false);
        }
    }

    public void setLayoutType(int paramInt) {
        this.q = paramInt;
        if (paramInt != 1) {
            setVerticalScrollBarEnabled(false);
        }
    }

    public void setMyOnChangedListener(a parama) {
        this.j = parama;
    }

    public void setShowBottomViewOnFirstLayout(boolean paramBoolean) {
        this.F = paramBoolean;
    }

    public void setVerticalScrollBarEnabled(boolean paramBoolean) {
        boolean bool1 = false;
        int i1 = 1;
        if ((this.r) || (this.G) || (this.H) || (this.q != 1)) {
            paramBoolean = false;
        }
        if (this.J == paramBoolean) {
            return;
        }
        super.setVerticalScrollBarEnabled(paramBoolean);
        boolean bool2 = isVerticalScrollBarEnabled();
        label62:
        g localg;
        label82:
        Object localObject;
        if (!bool2) {
            paramBoolean = true;
            setWillNotDraw(paramBoolean);
            if (!bool2) {
                break label218;
            }
            localg = new g(this);
            if (this.c != null) {
                localObject = this.c;
                if (bool2) {
                    break label224;
                }
            }
        }
        label218:
        label224:
        for (paramBoolean = true; ; paramBoolean = false) {
            ((MyWebViewV9) localObject).setVerticalScrollBarEnabled(paramBoolean);
            this.c.setOnScrollBarShowListener(localg);
            if ((this.d instanceof MyListViewV9)) {
                localObject = this.d;
                paramBoolean = bool1;
                if (!bool2) {
                    paramBoolean = true;
                }
                ((ListView) localObject).setVerticalScrollBarEnabled(paramBoolean);
                ((MyListViewV9) this.d).setOnScrollBarShowListener(localg);
            }
            this.J = bool2;
            if ((!bool2) || (this.d == null)) {
                break;
            }
            if (this.d.isSmoothScrollbarEnabled()) {
                i1 = 100;
            }
            this.L = Math.max(1.0F, j.b(getContext(), 100.0F) / i1);
            return;
            paramBoolean = false;
            break label62;
            localg = null;
            break label82;
        }
    }

    public static abstract interface a {
        public abstract void a();

        public abstract void a(int paramInt);

        public abstract void a(boolean paramBoolean);
    }
}

*/
