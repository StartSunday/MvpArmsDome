/*
 * Copyright (C) 2016 LingDaNet.Co.Ltd. All Rights Reserved.
 */
package com.sun.component.commonres.dialog;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.GlideArms;
import com.sun.component.commonres.R;


public class LoadingDialog extends BaseDialog {
    private TextView mTvShowText;

    public LoadingDialog(Context context) {
        super(context, R.layout.loading_dialog, R.style.Base_Dialog_Loading);
    }

    @Override
    protected void initView() {
        mTvShowText = findViewById(R.id.tv_loading);
        GlideArms.with(getContext()).load(R.drawable.ic_loading).into((ImageView) findViewById(R.id.iv_loading));
    }

    @Override
    protected void initData() {

    }

    public void show(int resId) {
        mTvShowText.setText(resId);
        show();
    }

    public void show(String showText) {
        mTvShowText.setText(showText);
        show();
    }
}
