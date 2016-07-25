package com.asha.vrlib;

import android.content.Context;
import android.view.ViewGroup;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by zzj-uc on 16/7/23.
 */
public class MDConsoleHandle {

    private ImageView mHandleImage;
    private FrameLayout.LayoutParams mLayoutParams;

    public MDConsoleHandle(Context context) {
        mLayoutParams = new FrameLayout.LayoutParams(-1, -1, Gravity.CENTER);

        mHandleImage = new ImageView(context);

        mHandleImage.setImageResource(R.drawable.soccerball);
    }

    public void attach(ViewGroup v) {
        v.addView(mHandleImage, mLayoutParams);
    }
}
