package com.sun.component.commonres.widget;

import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * 为ViewPager添加布局（Fragment），绑定和处理fragments和viewpager之间的逻辑关系
 * 可保持Fragment切换状态
 */
public class FragmentViewPagerAdapter extends PagerAdapter implements MyViewPager.OnPageChangeListener {
    private List<Fragment> fragments; // 每个Fragment对应一个Page
    private FragmentManager fragmentManager;
    private ContainerViewPager viewPager; // viewPager对象
    private int currentPageIndex = 0; // 当前page索引（切换之前）

    private OnExtraPageChangeListener onExtraPageChangeListener; // ViewPager切换页面时的额外功能添加接口

    public FragmentViewPagerAdapter(FragmentManager fragmentManager, ContainerViewPager viewPager, List<Fragment> fragments) {
        this.fragments = fragments;
        this.fragmentManager = fragmentManager;
        this.viewPager = viewPager;
        this.viewPager.setAdapter(this);

        this.viewPager.setOnPageChangeListener(this);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(fragments.get(position).getView()); // 移出viewpager两边之外的page布局
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = fragments.get(position);
        if (!fragment.isAdded()) { // 如果fragment还没有added
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(fragment, fragment.getClass().getSimpleName());
            ft.commit();

            fragmentManager.executePendingTransactions();
        }

        if (fragment.getView().getParent() == null) {
            container.addView(fragment.getView()); // 为viewpager增加布局
        }

        return fragment.getView();
    }

    /**
     * 当前page索引（切换之前）
     *
     * @return
     */
    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public OnExtraPageChangeListener getOnExtraPageChangeListener() {
        return onExtraPageChangeListener;
    }

    /**
     * 设置页面切换额外功能监听器
     *
     * @param onExtraPageChangeListener
     */
    public void setOnExtraPageChangeListener(OnExtraPageChangeListener onExtraPageChangeListener) {
        this.onExtraPageChangeListener = onExtraPageChangeListener;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {
        if (null != onExtraPageChangeListener) { // 如果设置了额外功能接口
            onExtraPageChangeListener.onExtraPageScrolled(i, v, i2);
        }
    }

    @Override
    public void onPageSelected(int i) {
        fragments.get(currentPageIndex).onPause(); // 调用切换前Fargment的onPause()
        if (fragments.get(i).isAdded()) {
            fragments.get(i).onResume(); // 调用切换后Fargment的onResume()
        }
        currentPageIndex = i;

        if (null != onExtraPageChangeListener) { // 如果设置了额外功能接口
            onExtraPageChangeListener.onExtraPageSelected(i);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        if (null != onExtraPageChangeListener) { // 如果设置了额外功能接口
            onExtraPageChangeListener.onExtraPageScrollStateChanged(i);
        }
    }

    /**
     * page切换额外功能接口
     */
    public static class OnExtraPageChangeListener {
        public void onExtraPageScrolled(int i, float v, int i2) {
        }

        public void onExtraPageSelected(int i) {
        }

        public void onExtraPageScrollStateChanged(int i) {
        }
    }
}
