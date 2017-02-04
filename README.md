#Android仿今日头条详情页实现

##源码地址：
 [Android仿今日头条详情页实现 github源码地址](https://github.com/iceAnson/NewsDetail) 
 
 [动态图](https://www.diycode.cc/topics/589)

最近项目有个需求，需要实现一个和今日头条新闻详情页一样的体验。上部分是webview来展示新闻内容，下半部分是listview来展示评论区，可无限加载更多。
起初的实现思路是 将webview放置在listview头部，看似没有什么问题，实现之后发现，webview各种奇怪的问题：黑屏，图片闪烁白屏,渲染速度慢等等问题；
将webview和listview独立放置遍没有问题；于是反编译了一下头条的实现：

![Screen Shot](http://okttsxi3s.bkt.clouddn.com/toutiao1.png)

从上图可以知道，实现的原理是，ViewGroup包着listview和webview实现的；于是顺着这条思路往下走。

今日头条的代码是混淆的无法直接使用，我采用的方案是ScrollView里边嵌套了webview+listview;

这套方案有以下几个问题需要解决：

```java
 1. 解决webview在scrollview全部展开的问题。不展开的方法太过复杂，手势处理太麻烦，这里采用展开的形式
 2. 我们知道scrollview包含的childview是无法复用了，那么首先要解决listview的复用问题；
 3. 滑动到listview和webview边界的时候，对于手势事件的交换和状态的保存。

``` 

## 1、webview全部展开的问题

```java
		mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                int w = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED);
                int h = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED);
                //重新测量
                view.measure(w, h);
                mWebViewHeight = view.getHeight();
                Log.i(TAG, "WEBVIEW高度:" + view.getHeight());
            }
        });
        
```

## 2和3、listview复用问题和手势切换问题

1、复用意味着不能全部展开，现将高度定为（屏幕高度-状态栏高度-标题栏高度）；

2、当scrollview滚动到底部的时候，让listview根据手势惯性fling一会，以加强体验，然后将手势交给listview；

3、当listview滑动到底部的时候，加载更多；

4、当listview滑动到顶部的时候，向上滚5个像素，并将手势交给scrollview，便可向上流畅滑动；

5、当listview即将到达顶部的时候，手动往下拖动，当到达边界的时候，需要让scrollview跟着以前scrollby，让用户感知是一起滑动的，当手指松开的时候，要让scrollview惯性滚动一会，以增强体验；

## 遗留问题
1、scrollview和listview的内部滚动速度是不一致的，scrollview是比较大的，在scrollview滚动到底部的时候，listview采用scrollview滚动速度的三分之一进行fling，体验下来感觉还是比较流畅的。但总感觉还是不够稳妥，优化思路是：接管scrollview和listview的滚动速度，手动控制两个控件的过渡过程；

2、当listview即将到达顶部的时候，手动往下拖动，当到达边界的时候，需要让scrollview跟着以前scrollby，让用户感知是一起滑动的，当手指松开的时候，要让scrollview惯性滚动一会，这里的惯性也由于速度不一致的问题，造成一点点的那么不自然；优化思路和1一致；


Done
----------
QQ:452825089

mail:452825089@qq.com

wechat:ice3897315

blog:http://iceAnson.github.io






