package com.ps.mrecycler;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.LoadMoreListener;
import com.ps.mrcyclerview.MRecyclerView;
import com.ps.mrcyclerview.click.OnClickItemListener;
import com.ps.mrcyclerview.click.OnLoadMoreErrorListener;
import com.ps.mrcyclerview.click.OnLongClickItemListener;

import java.util.ArrayList;
import java.util.List;

public class DefaultActivity extends AppCompatActivity {

    private MRecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

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
    }

    /**
     * 获取数据
     * @param nextPage
     * @return
     */
    public List<Object> getResult(int nextPage){
        List<Object> contentEntities = new ArrayList<>();
        int number = (nextPage < 3 ? nextPage*20 : (nextPage - 1) * 20 + 10);
        Log.d("MainActivity","当前页数据量：" + number);
        for (int i = (nextPage - 1) * 20; i < number; i++){
            if (i % 2 == 0){
                contentEntities.add(new ContentEntity("第" + i + "条数据"));
            } else {
                contentEntities.add(new ContentEntity2("第" + i + "条数据"));
            }
        }
        return contentEntities;
    }
}
