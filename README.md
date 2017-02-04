#Android仿今日头条详情页实现

最近项目有个需求，需要实现一个和今日头条新闻详情页一样的体验。上部分是webview来展示新闻内容，下半部分是listview来展示评论区，可无限加载更多。
起初的实现思路是 将webview放置在listview头部，看似没有什么问题，实现之后发现，webview各种奇怪的问题：黑屏，图片闪烁白屏,渲染速度慢等等问题；
将webview和listview独立放置遍没有问题；于是反编译了一下头条的实现：

![Screen Shot](/image/toutiao1.png)