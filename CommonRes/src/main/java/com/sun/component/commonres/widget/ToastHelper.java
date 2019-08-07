package com.sun.component.commonres.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sun.component.commonres.R;

public class ToastHelper {
    public static void show(Context context,CharSequence charSequence, int resId){
        LayoutInflater inflater =LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.view_toast, null);
        ImageView image = layout.findViewById(R.id.toast_img);
        if (resId == 0) {
            image.setImageResource(R.mipmap.smile_icon2);
        }else {
            image.setImageResource(resId);
        }
        TextView title = layout.findViewById(R.id.toast_content);
        title.setText(charSequence);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER , 12, 40);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
