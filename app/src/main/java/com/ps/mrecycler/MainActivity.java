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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 默认
     * @param view
     */
    public void defaultRecycler(View view) {
        Intent intent = new Intent(this,DefaultActivity.class);
        startActivity(intent);
    }

    /**
     * 网格布局
     * @param view
     */
    public void gridLayoutRecycler(View view) {
        Intent intent = new Intent(this,GridLayoutActivity.class);
        startActivity(intent);
    }

    /**
     * 瀑布流
     * @param view
     */
    public void staggeredGridLayoutRecycler(View view) {
        Intent intent = new Intent(this,StaggeredGridLayoutActivity.class);
        startActivity(intent);
    }
}
