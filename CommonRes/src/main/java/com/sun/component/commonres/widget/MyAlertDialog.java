package com.sun.component.commonres.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.sun.component.commonres.R;


/**
 * 弹出框
 */
public class MyAlertDialog {
    private Context context;
    private Dialog dialog;
    private Display display;
    private View layoutView;

    public MyAlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context
                .WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public MyAlertDialog builder(int layoutId) {
        // 获取Dialog布局
        layoutView = LayoutInflater.from(context).inflate(layoutId, null);


        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(layoutView);

//        // 调整dialog背景大小
//       /* lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.8)
//                , LayoutParams.WRAP_CONTENT));*/
//        Window dialogWindow = dialog.getWindow();
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.width = (int) (display.getWidth() * 0.8);
        return this;
    }


    public MyAlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public View findViewById(int id) {
        return layoutView.findViewById(id);
    }

    public void setCanceledOnTouchOutside(boolean b) {
        dialog.setCanceledOnTouchOutside(b);
    }


    public MyAlertDialog show() {
        dialog.show();
        return this;
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}