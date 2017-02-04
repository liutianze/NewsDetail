/*
package com.seeker.tony.myapplication.view;

*/
/**
 * Author: lwh
 * Date: 2/3/17 17:50.
 *//*



        import android.annotation.TargetApi;
        import android.content.Context;
        import android.graphics.Canvas;
        import android.os.SystemClock;
        import android.util.AttributeSet;
        import android.webkit.WebView;

        import com.bytedance.common.b.g;
        import com.bytedance.common.utility.Logger;
        import com.ss.android.article.base.feature.detail.view.s;
        import com.ss.android.article.base.feature.detail.view.t;
        import java.util.Iterator;
        import java.util.LinkedList;

@TargetApi(9)
public class MyWebViewV9
        extends WebView
{
    int a;
    int b;
    int c;
    s<MyWebViewV9> d;
    private LinkedList<b> e = new LinkedList();
    private a f;
    private c g;
    private int h;
    private boolean i;
    private t j;

    public MyWebViewV9(Context paramContext)
    {
        super(paramContext);
    }

    public MyWebViewV9(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    public MyWebViewV9(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
    }

    private void a(int paramInt)
    {
        if (this.f != null) {
            this.f.a(this.h, paramInt);
        }
    }

    public void a()
    {
        setOverScrollMode(0);
        computeVerticalScrollRange();
    }

    public void b()
    {
        this.e.clear();
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
        this.c = super.computeVerticalScrollRange();
        return this.c;
    }

    public void draw(Canvas paramCanvas)
    {
        super.draw(paramCanvas);
        if (this.i)
        {
            int k = getContentHeight();
            if (Math.abs(k - this.h) >= 10)
            {
                int m = this.h;
                this.h = k;
                a(k - m);
            }
        }
        if (this.g != null) {
            this.g.a(getScaledContentHeight());
        }
    }

    public int getComputedVerticalScrollRange()
    {
        return this.c;
    }

    public int getScaledContentHeight()
    {
        return (int)(g.a(this) * getContentHeight());
    }

    protected void onDraw(Canvas paramCanvas)
    {
        try
        {
            super.onDraw(paramCanvas);
            return;
        }
        catch (Exception paramCanvas) {}
    }

    protected void onOverScrolled(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
    {
        super.onOverScrolled(paramInt1, paramInt2, paramBoolean1, paramBoolean2);
        if (this.d != null) {
            this.d.a(this, paramInt2, paramBoolean2, this.a, this.b);
        }
    }

    protected boolean overScrollBy(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
    {
        if (this.j != null) {
            this.j.a();
        }
        this.a = paramInt2;
        this.b = paramInt6;
        if ((paramInt2 > 0) && (paramInt4 > 0)) {
            paramInt8 = 0;
        }
        for (;;)
        {
            if (this.e.size() >= 10) {
                this.e.removeFirst();
            }
            if (paramInt2 + paramInt4 >= paramInt6) {
                if (!this.e.isEmpty()) {}
            }
            label334:
            for (;;)
            {
                return super.overScrollBy(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramBoolean);
                Iterator localIterator = this.e.iterator();
                for (int k = 0; localIterator.hasNext(); k = ((b)localIterator.next()).a + k) {}
                int m = (int)(((b)this.e.getLast()).b - ((b)this.e.getFirst()).b);
                b();
                if ((m > 0) && (k != 0))
                {
                    k = k / m * 1000;
                    if ((this.d != null) && (k != 0))
                    {
                        this.d.a(k);
                        if (Logger.debug()) {
                            Logger.d("MyWebViewV9", "overScrollBy: v = " + k);
                        }
                        for (;;)
                        {
                            if (!Logger.debug()) {
                                break label334;
                            }
                            Logger.d("MyWebViewV9", "overScrollBy:" + paramInt2 + "/" + paramInt4 + "/" + paramInt6 + "/" + SystemClock.uptimeMillis());
                            break;
                            this.e.add(new b(paramInt2, SystemClock.uptimeMillis()));
                        }
                    }
                }
            }
        }
    }

    public void setContentSizeChangeListener(a parama)
    {
        this.f = parama;
    }

    public void setDetectContentSize(boolean paramBoolean)
    {
        if (this.i != paramBoolean)
        {
            this.i = paramBoolean;
            if (paramBoolean) {
                this.h = getContentHeight();
            }
        }
    }

    public void setOnOverScrolledListener(s<MyWebViewV9> params)
    {
        this.d = params;
    }

    public void setOnScrollBarShowListener(t paramt)
    {
        this.j = paramt;
    }

    public void setWebViewDrawListener(c paramc)
    {
        this.g = paramc;
    }

    public static abstract interface a
    {
        public abstract void a(int paramInt1, int paramInt2);
    }

    private static class b
    {
        int a;
        long b;

        public b(int paramInt, long paramLong)
        {
            this.a = paramInt;
            this.b = paramLong;
        }
    }

    public static abstract interface c
    {
        public abstract void a(int paramInt);
    }
}
*/
