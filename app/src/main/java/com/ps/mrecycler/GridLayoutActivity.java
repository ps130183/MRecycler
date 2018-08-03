package com.ps.mrecycler;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.LoadMoreListener;
import com.ps.mrcyclerview.MRecyclerView;
import com.ps.mrcyclerview.click.OnClickItemListener;
import com.ps.mrcyclerview.click.OnLoadMoreErrorListener;
import com.ps.mrcyclerview.click.OnLongClickItemListener;
import com.ps.mrecycler.entity.MeinvEntity;

import java.util.ArrayList;
import java.util.List;

public class GridLayoutActivity extends AppCompatActivity {

    private MRecyclerView<MeinvEntity> recyclerView;
    private int images[] = {R.drawable.meinv1,R.drawable.meinv2,R.drawable.meinv3,R.drawable.meinv4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addContentLayout(R.layout.item_content_meinv, new ItemViewConvert<MeinvEntity>() {
            @Override
            public void convert(@NonNull BViewHolder holder, MeinvEntity mData, int position, @NonNull List<Object> payloads) {
                holder.addRippleEffectOnClick();
                ImageView image = holder.findView(R.id.image);
                TextView name = holder.findView(R.id.name);
                image.setImageResource(mData.getImageRes());
                name.setText(mData.getName());
            }

        }).create();
        recyclerView.addLoadMoreListener(new LoadMoreListener() {
            @Override
            public void loadMore(int nextPage) {
                if (nextPage == 2){
                    recyclerView.loadMoreError();
                } else {
                    recyclerView.loadDataOfNextPage(getResult(nextPage));
                }

            }
        });

        recyclerView.addClickItemListener(new OnClickItemListener() {
            @Override
            public void clickItem(Object mData, int position) {
                Toast.makeText(GridLayoutActivity.this,"单击事件： " + position,Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addLongClickItemListener(new OnLongClickItemListener() {
            @Override
            public void longClickItem(Object mData, int position) {
                Toast.makeText(GridLayoutActivity.this,"长按事件： " + position,Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addLoadMoreErrorListener(new OnLoadMoreErrorListener() {
            @Override
            public void loadMoreError(int nextPage) {
                recyclerView.loadDataOfNextPage(getResult(nextPage));
            }
        });
        recyclerView.loadDataOfNextPage(getResult(1));
    }

    /**
     * 获取数据
     * @param nextPage
     * @return
     */
    public List<MeinvEntity> getResult(int nextPage){
        List<MeinvEntity> contentEntities = new ArrayList<>();
        int number = (nextPage < 3 ? nextPage*20 : (nextPage - 1) * 20 + 10);
        Log.d("MainActivity","当前页数据量：" + number);
        for (int i = (nextPage - 1) * 20; i < number; i++){
            contentEntities.add(new MeinvEntity("美女" + (i % 4),images[i % 4]));
        }
        return contentEntities;
    }
}
