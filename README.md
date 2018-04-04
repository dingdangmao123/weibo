# Weibo
 
- 这是一个简单的Android微博客户端，采用ButterKnife，OKHttp框架
 
- Web后台采用 PHP+MySQL，利用Redis构建热门话题，Token缓存等

- NIO服务器采用Java Netty + Redis消息队列提供消息实时提醒

- [English](https://github.com/dingdangmao123/weibo/blob/master/README_EN.md)


## 开发笔记

- 个人觉得最重要的是设计好Json数据协议，利用PHP输出Json数据，采用Okhttp和Gson得到Java数据模型，Gson泛型的问题，将实体消息封装到inner字段内，通过类继承绕开泛型，code字段和msg字段交给BaseActivity，BaseFragment检查处理。

- Token字段登录后存入MySQL，并缓存到Redis,登录后自动刷新，旧的自动废弃。

- 其余主要是一些常规的优化，图片的标签防止错位，缩略图，LRU的缓存等

- 微博比较有意思的就是@功能，#话题#功能，通过正则表达式匹配来提取信息，生成提醒等批量插入MySQL,提高性能，#话题#功能有起始和终止#号，比较好匹配，而@功能由于没有中止符号较为难办，本项目支持两种:@Java@Scala@Kotlin 或者@Java @Scala @Kotlin 即通过后面的@符号或者空格来界定，不知有无更好的设计。UI通过SpannableString显示和响应点击事件。

- 本项目没有实现转发微博，特别是针对多人连续转发，本人也考虑了两种方案，一种是每条微博保存被转发的微博id(如果多次转发，只保留最近的一条),显示时只显示对当前和最近的一条微博，这样查询起来比较快。另外一种是数据库冗余设计，即保存最原始的微博ID，并且保存所有中间转发的内容，所有中间转发内容包括转发人统统转化成字符串存储，这样刷微博时可以显示全部转发内容和转发人，不知道新浪微博是怎么设计的。

- @，评论，点赞等消息写入MySQL，再同步至Redis消息队列，通过Java Netty框架构建NIO服务器，与客户端保持长连接，提供实时的消息提醒，目前只在首页提供红点提示，也可在全局添加Notification通知；


## 主要框架
- [ButterKnife](https://github.com/JakeWharton/butterknife)
- [Okhttp](https://github.com/square/okhttp)
- [Gson](https://github.com/google/gson)
- [SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)
- [ImageSelector](https://github.com/smuyyh/ImageSelector)
- [Netty](https://github.com/netty/netty)




## 运行效果

![](https://github.com/dingdangmao123/weico/blob/master/demo/11.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/12.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/13.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/1.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/2.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/3.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/4.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/5.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/6.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/7.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/8.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/9.png)

