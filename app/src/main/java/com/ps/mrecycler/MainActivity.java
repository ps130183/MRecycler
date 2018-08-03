package com.ps.mrecycler;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ps.mrcyclerview.BViewHolder;
import com.ps.mrcyclerview.ItemViewConvert;
import com.ps.mrcyclerview.LoadMoreListener;
import com.ps.mrcyclerview.MRecyclerView;
import com.ps.mrcyclerview.click.OnClickItemListener;
import com.ps.mrcyclerview.click.OnLoadMoreErrorListener;
import com.ps.mrcyclerview.click.OnLongClickItemListener;
import com.ps.mrecycler.entity.MainEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] btnNames = {"默认", "网格布局", "默认布局", "侧滑带下拉刷新"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycler();
    }


    private void initRecycler() {
        MRecyclerView<MainEntity> mRecyclerView = findViewById(R.id.mrecycler);
        mRecyclerView.addContentLayout(R.layout.item_main, new ItemViewConvert<MainEntity>() {
            @Override
            public void convert(@NonNull BViewHolder holder, MainEntity mData, int position, @NonNull List<Object> payloads) {
                holder.setText(R.id.btnName, mData.getBtnName());
            }

        }).create();
        mRecyclerView.addClickItemListener(new OnClickItemListener() {
            @Override
            public void clickItem(Object mData, int position) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, DefaultActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, GridLayoutActivity.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, StaggeredGridLayoutActivity.class);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, SwipeLayoutActivity.class);
                        break;
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });

        List<MainEntity> mainEntities = new ArrayList<>();
        for (int i = 0; i < btnNames.length; i++) {
            mainEntities.add(new MainEntity(btnNames[i]));
        }
        mRecyclerView.loadDataOfNextPage(mainEntities);
    }
}
