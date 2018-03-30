# Weibo
 
- This is a simple app like **Twitter**，which uses **ButterKnife**,**OKHttp** and so on.

- The server use **PHP**, **MySQL** and **Redis**.

- [中文文档](https://github.com/dingdangmao123/weibo/blob/master/README.md)



## Notes

- Personally thinking that the most important thing is to design a good Json data protocol, use PHP to output Json data, use Okhttp and Gson to get the Java data model, Gson generic problem, encapsulate entity messages in the inner field, bypass generics by class inheritance, The code field and msg field are passed to BaseActivity and BaseFragment checks and process.

- With the Token field gotten, it will be stored in MySQL, cached in Redis, automatically refreshed. Of course, the old one is automatically discarded.

- The rest are mainly some regular optimizations, the labeling of pictures prevents misplacement, thumbnails, LRU cache, etc.

- The more interesting of **twitter** is the **@function**, **#topic#** function, through regular expression matching to extract information, generate notifications and insert into MySQL to improve performance, **#topic#** has a starting and ending #, better match, and **@Function** Because it is more difficult to do without the abort symbol, this project supports two types: **@Java@Scala@Kotlin** or **@Java @Scala @Kotlin** is defined by the back @ symbol or space, I wonder if there is a better design. The UI displays and responds to click events with Android SpannableString.



## Librarys

- [ButterKnife](https://github.com/JakeWharton/butterknife)
- [Okhttp](https://github.com/square/okhttp)
- [Gson](https://github.com/google/gson)
- [SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)
- [ImageSelector](https://github.com/smuyyh/ImageSelector)



## Plan

- add feature,fix bugs and refactor the code.

- I plan to add the realtime message notifications (Java NIO)



## Demo

![](https://github.com/dingdangmao123/weico/blob/master/demo/11.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/12.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/13.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/1.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/2.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/3.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/4.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/5.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/6.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/7.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/8.png) ![](https://github.com/dingdangmao123/weico/blob/master/demo/9.png)



