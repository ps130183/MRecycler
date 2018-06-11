
# MRecycler
MrecyclerView介绍
接触Android也收5年左右了，这么长的时间里用过各种各样的列表，有自己写的，也有第三方的，功能也各有不同。总之，就是没有一种能完全符合工作中的需要，因此也就有了写这个控件的原始想法。接下来就说说这个控件的作用。

# 简单介绍
这个控件的初衷是希望有这样一个列表，只要设置数据和显示数据的布局就可以展示列表的数据，不用再频繁的写Adapter,ViewHolder等等。。。简化写列表的流程。
目前功能除了设置可以直接设置数据外还包括设置多个Header/Footer、上拉加载更多，然后控件整合了linearLayoutManager、GridLayoutManager、StaggeredGridLayoutManager，只需要简单的几个设置就可以实现列表、网格以及瀑布流。并且集成了各种布局管理的分割线。

# 使用
Step 1. Add it in your root build.gradle at the end of repositories:<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;allprojects {<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;repositories {<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;maven { url 'https://jitpack.io' }<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
Step 2. Add the dependency<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dependencies {<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;implementation 'com.github.ps130183:MRecycler:1.0'<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br> 

# 自定义属性
|属性名         | 描述           | 默认值  |
| ------------- |:-------------:| -----:|
| loadMoreRes   | 加载更多的布局文件 | mr_load_more |
| loadMoreFinishRes      | 所有数据加载完以后显示的内容      |   mr_load_more_finish |
| loadMoreErrorRes | 加载更多失败时显示的内容，有单独的点击事件监听，在加载失败时点击重新加载      |    mr_load_more_error |
| emptyRes | 列表数据为空时，展示的内容      |    mr_empty |
| lmType | 布局管理器类型，包括：linear,grid,staggeredGrid      |    linear |
| orientation | 布局管理器的布局方向，包括vertical,horizontal      |    vertical |
| spanCount | lmType!=linear时，每行所包含的item数量      |    2 |
| dividerWidth | 分割线的宽度      |    1dp |
| dividerColor | 分割线的颜色      |    0xffefeff4 |

注意：
1、以上属性需要在xml布局文件中设置
2、分割线的颜色 可以直接取资源文件中 颜色的ID值
3、分割线的宽度是以dp为单位，但是设置时不需要加dp

```java
        <com.ps.mrcyclerview.MRecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:lmType="linear"
        app:orientation="vertical"
        app:dividerColor="@color/dividerColor"
        app:dividerWidth="3"/>
# 例如
1、布局管理器默认是LinearLayoutManager,默认方向vertical,默认分割线宽度1dp<br>
```java<br>

        <com.ps.mrcyclerview.MRecyclerVie                
            android:id="@+id/recyclerView"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

        </com.ps.mrcyclerview.MRecyclerView>
        
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addContentLayout(R.layout.item_content, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position) {
                holder.addRippleEffectOnClick();
                ContentEntity entity = (ContentEntity) mData;
                holder.setText(R.id.name,entity.getName());
            }
        }).addContentLayout(R.layout.item_content2, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position) {
                ContentEntity2 entity = (ContentEntity2) mData;
                holder.setText(R.id.name,entity.getName());
            }
        }).addHeaderLayout(R.layout.header_view, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position) {

            }
        }).create();
        recyclerView.addLoadMoreListener(new LoadMoreListener() {
            @Override
            public void loadMore(int nextPage) {
                if (nextPage == 2){
                    recyclerView.loadMoreError();
                } else {
                    recyclerView.update(getResult(nextPage));
                }

            }
        });

        recyclerView.addClickItemListener(new OnClickItemListener() {
            @Override
            public void clickItem(Object mData, int position) {
                Toast.makeText(DefaultActivity.this,"单击事件： " + position,Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addLongClickItemListener(new OnLongClickItemListener() {
            @Override
            public void longClickItem(Object mData, int position) {
                Toast.makeText(DefaultActivity.this,"长按事件： " + position,Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addLoadMoreErrorListener(new OnLoadMoreErrorListener() {
            @Override
            public void loadMoreError(int nextPage) {
                recyclerView.update(getResult(nextPage));
            }
        });
        recyclerView.update(getResult(1));
  
