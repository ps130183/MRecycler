package com.ps.mrecycler;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.daimajia.swipe.SwipeLayout;
import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.MRecyclerView;
import com.ps.mrcyclerview.utils.RefreshUtils;
import com.ps.mrecycler.entity.SwipeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 侧滑
 */
public class SwipeLayoutActivity extends AppCompatActivity {

    private MRecyclerView<SwipeEntity> mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_layout);
        initRecycler();
    }

    private void initRecycler(){
        mRecyclerView = findViewById(R.id.mrecycler);

        mRecyclerView.addContentLayout(R.layout.item_swipe_layout, new ItemViewConvert<SwipeEntity>() {
            @Override
            public void convert(@NonNull BViewHolder holder, final SwipeEntity mData, int position, @NonNull List<Object> payloads) {
                holder.setText(R.id.name,mData.getName());


                SwipeLayout swipeLayout = holder.findView(R.id.swipe_layout);
                if (swipeLayout != null) {
                    //set show mode.
                    swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
                    swipeLayout.setSwipeEnabled(true);
                    swipeLayout.setClickToClose(true); //点击其他区域关闭侧滑
                }

                holder.findView(R.id.remove).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mRecyclerView.delete(mData);
                    }
                });
            }
        }).create();

        mRecyclerView.addRefreshListener(new RefreshUtils.OnRefreshListener() {
            @Override
            public void refresh() {
                mRecyclerView.clear();
                List<SwipeEntity> swipeEntities = new ArrayList<>();
                for (int i = 0; i < 20; i++){
                    swipeEntities.add(new SwipeEntity("第" + (i + 1) + "项数据"));
                }
                mRecyclerView.loadDataOfNextPage(swipeEntities);
                mRecyclerView.stopRefresh();
            }
        });
        List<SwipeEntity> swipeEntities = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            swipeEntities.add(new SwipeEntity("第" + (i + 1) + "项数据"));
        }
        mRecyclerView.loadDataOfNextPage(swipeEntities);

    }
}
