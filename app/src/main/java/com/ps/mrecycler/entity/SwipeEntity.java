package com.ps.mrecycler.entity;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.ps.mrecycler.R;

/**
 * Created by PengSong on 18/8/3.
 */

public class SwipeEntity implements ItemDelegate {
    private String name;

    public SwipeEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getItemViewRes() {
        return R.layout.item_swipe_layout;
    }
}
