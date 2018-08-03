package com.ps.mrecycler.entity;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.ps.mrecycler.R;

/**
 * Created by PengSong on 18/6/7.
 */

public class MeinvEntity implements ItemDelegate {

    private String name;
    private int imageRes;

    public MeinvEntity(String name, int imageRes) {
        this.name = name;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    @Override
    public int getItemViewRes() {
        return R.layout.item_content_meinv;
    }
}
