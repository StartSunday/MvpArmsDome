package com.jess.arms.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.R;


/**
 * <p>空数据显示<p/>
 * Created by ybc on 17/9/19.
 */
public class EmptyView extends LinearLayout {
    public final static int TYPE_DEFAULT = 0;//默认 图文
    public final static int TYPE_TEXT = 1;//只显示文字
    private int type = TYPE_DEFAULT;
    private Context mCtx;
    private TextView tvNoData;
    private ImageView ivImage;
    public EmptyView(Context context) {
        super(context);
        init(context,TYPE_DEFAULT);
    }

    public EmptyView(Context context, int type) {
        super(context);
        init(context,type);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,TYPE_DEFAULT);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,TYPE_DEFAULT);
    }

    public EmptyView(Context context, int imgId, String defStyleAttr) {
        super(context);
        init(context,TYPE_DEFAULT);
        if (ivImage != null) ivImage.setImageResource(imgId);
    }

    private void init(Context context, int type) {
        mCtx = context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_empty, this);
        ivImage = view.findViewById(R.id.iv_icon);
        switch (type) {
            case TYPE_DEFAULT:
                view.findViewById(R.id.iv_icon).setVisibility(VISIBLE);
                break;
            case TYPE_TEXT:
                view.findViewById(R.id.iv_icon).setVisibility(GONE);
                break;
        }
        //tvNoData = (TextView) view.findViewById(R.id.tv_no_data);
    }

}
