package com.ps.mrcyclerview.divider;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ps.mrcyclerview.RecyclerAdapter;

/**
 * Created by PengSong on 18/6/9.
 */

public class GridDividerItemDecotation extends Y_DividerItemDecoration {

    private int dividerWidth;
    private @ColorInt
    int dividerColor;
    private GridLayoutManager glm;
    private RecyclerView mRecyclerView;

    private int count = 0;

    public GridDividerItemDecotation(Context context) {
        super(context);
    }

    public GridDividerItemDecotation(RecyclerView recyclerView, int dividerWidth, int dividerColor) {
        super(recyclerView.getContext());
        mRecyclerView = recyclerView;
        this.dividerWidth = dividerWidth;
        this.dividerColor = dividerColor;
        this.glm = (GridLayoutManager) recyclerView.getLayoutManager();
    }

    @Nullable
    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = null;
        RecyclerAdapter adapter = (RecyclerAdapter) mRecyclerView.getAdapter();
        int spanCount = glm.getSpanCount();
        if (adapter.isHeaderView(itemPosition)){
            divider = new Y_DividerBuilder()
                    .setBottomSideLine(true, dividerColor, dividerWidth, 0, 0)
                    .create();
        } else if (adapter.isContentView(itemPosition)) {
            int position = itemPosition - adapter.getHeaderSize();
            if (position % spanCount == 0){
                divider = new Y_DividerBuilder()
                        .setLeftSideLine(true,dividerColor,dividerWidth,0,0)
                        .setRightSideLine(true, dividerColor, dividerWidth, 0, 0)
                        .setBottomSideLine(true, dividerColor, dividerWidth, 0, 0)
                        .create();
            }else {
                divider = new Y_DividerBuilder()
                        .setRightSideLine(true, dividerColor, dividerWidth, 0, 0)
                        .setBottomSideLine(true, dividerColor, dividerWidth, 0, 0)
                        .create();
            }
        }
        return divider;
    }
}
