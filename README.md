#Android仿今日头条详情页实现

![Screen Shot](/image/xiaoguo.gif)

最近项目有个需求，需要实现一个和今日头条新闻详情页一样的体验。上部分是webview来展示新闻内容，下半部分是listview来展示评论区，可无限加载更多。
起初的实现思路是 将webview放置在listview头部，看似没有什么问题，实现之后发现，webview各种奇怪的问题：黑屏，图片闪烁白屏,渲染速度慢等等问题；
将webview和listview独立放置遍没有问题；于是反编译了一下头条的实现：

![Screen Shot](/image/toutiao1.png)

从上图可以知道，实现的原理是，ViewGroup包着listview和webview实现的；于是顺着这条思路往下走。

今日头条的代码是混淆的无法直接使用，我采用的方案是ScrollView里边嵌套了webview+listview;

这套方案有以下几个问题需要解决：

```java
 1. 我们知道scrollview包含的childview是无法复用了，那么首先要解决listview的复用问题；
 2. 解决webview在scrollview全部展开的问题。不展开的方法太过复杂，手势处理太麻烦，这里采用展开的形式
 3. 滑动到listview和webview边界的时候，对于手势事件的交换和状态的保存。

``` 