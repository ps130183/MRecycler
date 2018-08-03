package com.ps.mrecycler.entity;

import com.ps.mrcyclerview.delegate.ItemDelegate;
import com.ps.mrecycler.R;

/**
 * Created by PengSong on 18/8/3.
 */

public class MainEntity implements ItemDelegate {
    private String btnName;

    public MainEntity(String btnName) {
        this.btnName = btnName;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    @Override
    public int getItemViewRes() {
        return R.layout.item_main;
    }
}
