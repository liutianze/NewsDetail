##  实现 布局： WebView +ListView ;

1. 遇到问题： 

 在WebView里面滚动flip, 最大距离只会停留在WebView底部，需要是需要直接换到ListView 开始Flip；
 
### 遇到的问题；  
1、不知道webview什么时候到底了, (Fixed )
@Override
protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    int height = (int) Math.floor(this.getContentHeight() * this.getScale());  
    int webViewHeight = this.getMeasuredHeight();  
    if(this.getScrollY() + webViewHeight >= height){  
       Log.i("THE END", "reached");
    }
    super.onScrollChanged(l, t, oldl, oldt);
}
2、要知道webview滑到底的速度和加速度，(后面可调，次要)

3、ListView要怎么自动fling距离 （）
listView.fling();

4、WebView自动滚，平滑滚动；

![Screen Shot](/image/Screenshot_20170120-161537.png)

5. 页面边界滑动问题：
 1. 在第二个页面滑动，手势不放开，需要能进入第一个页面；

