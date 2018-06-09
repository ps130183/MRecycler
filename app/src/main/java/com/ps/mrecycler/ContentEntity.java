package com.ps.mrecycler;

import com.ps.mrcyclerview.delegate.ItemDelegate;

/**
 * Created by PengSong on 18/6/4.
 */

public class ContentEntity implements ItemDelegate {

    private String name;

    public ContentEntity(String name) {
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
        return R.layout.item_content;
    }
}
