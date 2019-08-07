package com.sun.component.commonres.widget;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ClassDynamicSpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int topBottomSpace;
    private int space;

    public ClassDynamicSpacesItemDecoration(int space, int spanCount) {
        this.space = space;
        this.spanCount = spanCount;
        this.topBottomSpace = space;
    }

    public ClassDynamicSpacesItemDecoration(int space, int topBottomSpace, int spanCount) {
        this.space = space;
        this.topBottomSpace = topBottomSpace;
        this.spanCount = spanCount;
    }

    private GridLayoutManager.LayoutParams lp = null;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int position = layoutManager.getPosition(view);
        int childCount = parent.getChildCount();
        if (lp.getSpanIndex() < (spanCount - 1)) {
            // 用于设同行两个间隔间跟其距离左右屏幕间隔相同
            outRect.right = space;
        } else {
            outRect.right = 0;
        }
        outRect.bottom = topBottomSpace;
    }
}
