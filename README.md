# scrolltext
字幕滚动，跑马灯

##### 先看看效果图：

![image](https://github.com/xiongliangshan/scrolltext/blob/master/file/ezgif-5-19b5396712.gif)


用法非常简单：

```
<com.xls.library.ScrollTextView
        android:id="@+id/marqueeView"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:background="#a0000000"
        android:layout_alignParentTop="true"
        app:scrollSpeed="normal"
        app:scrollTextColor="@android:color/white"
        android:textSize="20sp"
        android:visibility="gone"
        android:text="打扫房间大神卡李方军啊三角阀神卡了积分多少的发送冷风机"/>
```

#### 启动跑马灯
```
startScroll()
```

#### 启动后持续一段时间自动停止小时
```
startScrollTimer(long time)
```
#### 暂停滚动中的字幕
```
pauseScroll()
```
#### 停止字幕滚动并隐藏控件
```
stopScroll()
```
#### 设置速度
```
setSpeed(int speed)
```
也可以在xml文件中，取值有三种slow，normal，fast 
```
app:scrollSpeed="normal"
```
#### 设置字幕的颜色
```
app:scrollTextColor="@android:color/white"
```






