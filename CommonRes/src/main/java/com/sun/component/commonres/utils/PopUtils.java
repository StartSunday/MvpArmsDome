package com.sun.component.commonres.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.sun.component.commonres.R;


/**
 * @author sun
 * @data 2019-01-03
 * @Explain
 */

public class PopUtils {
    private static PopupWindow mPopupWindow;
    public static PopupWindow showPopwindow(View contentView, int type, Activity activity,int width,int height){
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(contentView, width, height, true);
        }
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setContentView(contentView) ;
        mPopupWindow.setAnimationStyle(R.style.PopAnimationStyle);
        mPopupWindow.update();
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), type, 0, 0);
        darkenBackgroud(0.6f, activity.getWindow());
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
          @Override
          public void onDismiss() {

            mPopupWindow.dismiss();
            mPopupWindow = null;
          }
        });
        return mPopupWindow;
    }

    private static void darkenBackgroud(Float bgcolor, Window window) {
         WindowManager.LayoutParams  layoutParams=  window.getAttributes();
        layoutParams.alpha = bgcolor;
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setAttributes(layoutParams);
    }
}
