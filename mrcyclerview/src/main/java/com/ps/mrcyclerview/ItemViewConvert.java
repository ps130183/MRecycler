package com.ps.mrcyclerview;

import android.support.annotation.NonNull;

/**
 * Created by PengSong on 18/6/4.
 */

public interface ItemViewConvert {
    void convert(@NonNull BViewHolder holder,Object mData, int position);
}
