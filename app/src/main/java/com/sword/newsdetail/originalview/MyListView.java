/*
package com.seeker.tony.myapplication.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.OverScroller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Logger;

*/
/**
 * Author: lwh
 * Date: 2/3/17 17:44.
 *//*



@TargetApi(9)
public class MyListView
        extends ListView
{
    private static final boolean d;
    int a;
    int b;
    s<MyListView> c;
    private OverScroller e;
    private Method f;
    private Method g;
    private Object h;
    private long i;
    private AbsListView.OnScrollListener j = new h(this);
    private AbsListView.OnScrollListener k;
    private t l;

    static
    {
        if (Build.VERSION.SDK_INT >= 21) {}
        for (boolean bool = true;; bool = false)
        {
            d = bool;
            return;
        }
    }

    public MyListView(Context paramContext)
    {
        super(paramContext);
        a();
    }

    public MyListView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        a();
    }

    public MyListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        a();
    }

    private boolean d()
    {
        return Build.VERSION.SDK_INT >= 14;
    }

    private void e()
    {
        try
        {
            Object localObject1 = Class.forName(AbsListView.class.getName()).getDeclaredField("mEdgeGlowTop");
            ((Field)localObject1).setAccessible(true);
            localObject1 = ((Field)localObject1).get(this);
            Object localObject2 = Class.forName(localObject1.getClass().getName());
            Field localField = ((Class)localObject2).getDeclaredField("mGlow");
            localField.setAccessible(true);
            localField.set(localObject1, new ColorDrawable(0));
            localObject2 = ((Class)localObject2).getDeclaredField("mEdge");
            ((Field)localObject2).setAccessible(true);
            ((Field)localObject2).set(localObject1, new ColorDrawable(0));
            return;
        }
        catch (Exception localException) {}
    }

    @SuppressLint({"NewApi"})
    protected void a()
    {
        super.setOnScrollListener(this.j);
        if (!d()) {}
        do
        {
            return;
            setFriction(ViewConfiguration.getScrollFriction());
            e();
            try
            {
                Object localObject = AbsListView.class.getDeclaredField("mFlingRunnable");
                ((Field)localObject).setAccessible(true);
                this.h = ((Field)localObject).get(this);
                localObject = Class.forName("android.widget.AbsListView$FlingRunnable");
                this.g = ((Class)localObject).getDeclaredMethod("start", new Class[] { Integer.TYPE });
                this.g.setAccessible(true);
                localObject = ((Class)localObject).getDeclaredField("mScroller");
                ((Field)localObject).setAccessible(true);
                this.e = ((OverScroller)((Field)localObject).get(this.h));
                this.f = AbsListView.class.getDeclaredMethod("reportScrollStateChange", new Class[] { Integer.TYPE });
                this.f.setAccessible(true);
                return;
            }
            catch (Throwable localThrowable)
            {
                localThrowable.printStackTrace();
                this.e = null;
                this.h = null;
                this.g = null;
                this.f = null;
            }
        } while (!Logger.debug());
        Logger.d("MyListViewV9", "init, exception:" + Log.getStackTraceString(localThrowable));
    }

    protected void a(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
    {
        if (this.k != null) {
            this.k.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
        }
        if (this.l != null) {
            this.l.a();
        }
    }

    @SuppressLint({"NewApi"})
    public boolean a(int paramInt)
    {
        boolean bool = true;
        if (!b()) {
            return false;
        }
        if (d) {
            fling(paramInt);
        }
        for (;;)
        {
            return bool;
            if ((this.f != null) && (this.g != null)) {
                try
                {
                    this.f.invoke(this, new Object[] { Integer.valueOf(2) });
                    this.g.invoke(this.h, new Object[] { Integer.valueOf(paramInt) });
                }
                catch (Throwable localThrowable)
                {
                    localThrowable.printStackTrace();
                    if (Logger.debug()) {
                        Logger.d("MyListViewV9", "tryFling, exception:" + Log.getStackTraceString(localThrowable));
                    }
                    bool = false;
                }
            } else {
                bool = false;
            }
        }
    }

    public boolean b()
    {
        return ((this.h != null) && (this.f != null) && (this.g != null)) || ((d) && (getVisibility() == 0));
    }

    public void c()
    {
        setOverScrollMode(0);
    }

    public int computeVerticalScrollExtent()
    {
        return super.computeVerticalScrollExtent();
    }

    public int computeVerticalScrollOffset()
    {
        return super.computeVerticalScrollOffset();
    }

    public int computeVerticalScrollRange()
    {
        return super.computeVerticalScrollRange();
    }

    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
        if (getVisibility() != 0) {
            return false;
        }
        return super.onInterceptTouchEvent(paramMotionEvent);
    }

    protected void onOverScrolled(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
    {
        super.onOverScrolled(paramInt1, paramInt2, paramBoolean1, paramBoolean2);
        if (this.c != null) {
            this.c.a(this, paramInt2, paramBoolean2, this.a, this.b);
        }
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
        if (getVisibility() != 0) {
            return false;
        }
        return super.onTouchEvent(paramMotionEvent);
    }

    @SuppressLint({"NewApi"})
    protected boolean overScrollBy(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
    {
        this.a = paramInt2;
        this.b = paramInt6;
        if ((paramInt2 < 0) && (paramInt4 < 0)) {}
        for (int n = 0;; n = paramInt8)
        {
            int m;
            if ((d()) && (this.c != null) && (paramInt2 < 0) && (getFirstVisiblePosition() == 0) && (paramInt4 == 0))
            {
                paramInt8 = 0;
                if (this.e != null)
                {
                    m = (int)-this.e.getCurrVelocity();
                    paramInt8 = m;
                    if (Logger.debug())
                    {
                        Logger.d("MyListViewV9", "overScrollBy:canUseOverScroller: " + m);
                        paramInt8 = m;
                    }
                }
                m = paramInt8;
                if (paramInt8 == 0)
                {
                    paramInt8 = 0;
                    long l1 = SystemClock.uptimeMillis() - this.i;
                    if (l1 > 0L) {
                        paramInt8 = (int)(1000L / l1);
                    }
                    if (paramInt8 > 0) {
                        break label273;
                    }
                    m = 0;
                }
            }
            for (;;)
            {
                m *= paramInt2;
                if (m != 0) {
                    this.c.a(m);
                }
                if (Logger.debug()) {
                    Logger.d("MyListViewV9", "overScrollBy:" + paramInt2 + "/" + paramInt4 + "/" + paramInt6 + "/" + m);
                }
                this.i = SystemClock.uptimeMillis();
                return super.overScrollBy(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, n, paramBoolean);
                label273:
                m = paramInt8;
                if (paramInt8 > 60) {
                    m = 60;
                }
            }
        }
    }

    public void setOnOverScrolledListener(s<MyListViewV9> params)
    {
        this.c = params;
    }

    public void setOnScrollBarShowListener(t paramt)
    {
        this.l = paramt;
    }

    public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
    {
        this.k = paramOnScrollListener;
    }
}
*/
