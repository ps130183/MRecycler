
# MRecycler
MrecyclerView介绍
接触Android也收5年左右了，这么长的时间里用过各种各样的列表，有自己写的，也有第三方的，功能也各有不同。总之，就是没有一种能完全符合工作中的需要，因此也就有了写这个控件的原始想法。接下来就说说这个控件的作用。

# 简单介绍
这个控件的初衷是希望有这样一个列表，只要设置数据和显示数据的布局就可以展示列表的数据，不用再频繁的写Adapter,ViewHolder等等。。。简化写列表的流程。
目前功能除了设置可以直接设置数据外还包括设置多个Header/Footer、上拉加载更多，然后控件整合了linearLayoutManager、GridLayoutManager、StaggeredGridLayoutManager，只需要简单的几个设置就可以实现列表、网格以及瀑布流。并且集成了各种布局管理的分割线。

# 使用
Step 1. Add it in your root build.gradle at the end of repositories:<br>
	allprojects {<br>
		repositories {<br>
			...<br>
			maven { url 'https://jitpack.io' }<br>
		}<br>
	}<br>
Step 2. Add the dependency<br>
	dependencies {<br>
	        implementation 'com.github.ps130183:MRecycler:1.0'<br>
	}<br>
  
