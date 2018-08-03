package com.ps.mrecycler.entity;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.ps.mrecycler.R;

/**
 * Created by PengSong on 18/6/4.
 */

public class ContentEntity implements ItemDelegate {

    private String name;

    private int layoutRes;
    public ContentEntity(String name) {
        this.name = name;
    }

    public ContentEntity(String name, int layoutRes) {
        this.name = name;
        this.layoutRes = layoutRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    @Override
    public int getItemViewRes() {
        return layoutRes;
    }
}
