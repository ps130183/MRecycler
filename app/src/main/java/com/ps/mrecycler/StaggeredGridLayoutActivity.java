package com.ps.mrecycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class StaggeredGridLayoutActivity extends AppCompatActivity {
    private MRecyclerView recyclerView;
    private int images[] = {R.drawable.meinv1,R.drawable.meinv2,R.drawable.meinv3,R.drawable.meinv4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addContentLayout(R.layout.item_content_meinv, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position) {
                holder.addRippleEffectOnClick();
                MeinvEntity entity = (MeinvEntity) mData;
                LinearLayout content = holder.findView(R.id.content);
                ImageView image = holder.findView(R.id.image);
                TextView name = holder.findView(R.id.name);


//                //获取图片的宽高
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inJustDecodeBounds = true;
//                Bitmap bitmap = BitmapFactory.decodeResource(StaggeredGridLayoutActivity.this.getResources(),entity.getImageRes(),options);
//                int imageWidth = options.outWidth;
//                int imageHeight = options.outHeight;
//
//                imageHeight = (imageWidth / getWidth()) * imageHeight;
//
//                content.getLayoutParams().height = imageHeight + 60;
                image.getLayoutParams().height = ((position % 4 + 1) > 2 ?  (position % 4 + 1) * 60  : (position % 4 + 1) * 120) + 360;



                image.setImageResource(entity.getImageRes());
                name.setText(entity.getName());



            }
        }).addHeaderLayout(R.layout.header_view, new ItemViewConvert() {
            @Override
            public void convert(@NonNull BViewHolder holder, Object mData, int position) {

            }
        }).addFooterLayout(R.layout.header_view, new ItemViewConvert() {
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
                Toast.makeText(StaggeredGridLayoutActivity.this,"单击事件： " + position,Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addLongClickItemListener(new OnLongClickItemListener() {
            @Override
            public void longClickItem(Object mData, int position) {
                Toast.makeText(StaggeredGridLayoutActivity.this,"长按事件： " + position,Toast.LENGTH_SHORT).show();
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
            contentEntities.add(new MeinvEntity("美女" + (i % 4),images[i % 4]));
        }
        return contentEntities;
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public int getWidth(){
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}
