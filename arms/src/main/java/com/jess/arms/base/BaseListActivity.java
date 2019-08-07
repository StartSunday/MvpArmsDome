package com.jess.arms.base;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.R;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DeviceActiveEvent;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.integration.EventBusManager;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.CacheType;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.utils.ArmsUtils;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.jess.arms.widget.CustomLoadMoreView;
import com.jess.arms.widget.EmptyView;
import com.jess.arms.http.Pager;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import static com.jess.arms.utils.ThirdViewUtil.convertAutoView;


/**
 * activities 列表基类
 * Created by ChenYuYun on 2018/3/27.
 * 继承该类：实现其抽象方法即可
 */
public abstract class BaseListActivity<T, P extends IPresenter> extends AppCompatActivity implements IActivity, ISupportActivity, ActivityLifecycleable {
    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();
    private Cache<String, Object> mCache;
    private Unbinder mUnbinder;
    @Inject
    @Nullable
    protected P mPresenter;//如果当前页面逻辑简单, Presenter 可以为 null

    protected  RecyclerView rvList;
    protected SwipeRefreshLayout swipeLayout;

    public BaseQuickAdapter getmAdapter() {
        return mAdapter;
    }

    protected BaseQuickAdapter mAdapter;

    public void setmAdapter(BaseQuickAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    private int mPageIndex = 1;
    private int mPageSize = 9;//一页显示行数

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.fragment_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        rvList = findViewById(R.id.rv_list);
        swipeLayout = findViewById(R.id.swipe_layout);
        swipeLayout.setOnRefreshListener(this::refresh);
        swipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
        initRecyclerView(rvList);
        refresh();
    }

    public int getPageIndex() {
        return mPageIndex;
    }

    //获取分页信息
    public Pager getPager(int pageSize) {
        mPageSize = pageSize;
        return new Pager(mPageIndex, mPageSize);
    }

    //获取分页信息
    public Pager getPager() {
        return new Pager(mPageIndex, mPageSize);
    }


    /**
     * 初始化RecyclerView控件,如果需要特殊处理样式时，可以重写这个方法
     *
     * @param recyclerView
     */
    protected void initRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvList.setLayoutManager(manager);
        setRecyclerView(recyclerView);
        mAdapter = getAdapter();
//        rvList.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this::loadData, rvList);
        mAdapter.disableLoadMoreIfNotFullPage();
        mAdapter.setEmptyView(new EmptyView(this));
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnItemClickListener(this::itemClick);
        mAdapter.setOnItemChildClickListener(this::itemChildClick);
    }

    protected void setRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }

    /**
     * 继续加载数据
     */
    protected void loadData() {
        swipeLayout.setRefreshing(true);
        swipeLayout.setEnabled(false);
        getData();
    }

    /**
     * 刷新列表
     */
    protected void refresh() {
        mPageIndex = 1;
        loadData();
    }

    public abstract BaseQuickAdapter getAdapter();

    public abstract void itemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position);

    public abstract void itemClick(BaseQuickAdapter baseQuickAdapter, View view, int position);

    public abstract void getData();

    //数据请求成功后的结果处理
    public void doSuc(List<T> rows, int totalPageCount) {
        swipeLayout.setRefreshing(false);
        swipeLayout.setEnabled(true);
        mAdapter.loadMoreComplete();
        if (rvList.getAdapter() == null) {
            rvList.setAdapter(mAdapter);
        }
        if (mPageIndex == 1) {
            mAdapter.setNewData(rows);
        } else {
            mAdapter.addData(rows);
        }
        if (mPageIndex++ >= totalPageCount) {
            mAdapter.loadMoreEnd(false);
        }
    }

    /**
     * 根据是否存在下一页
     *
     * @param rows
     */
    public void doSucNew(List<T> rows) {
        boolean hasNext = rows != null && rows.size() >= mPageSize;
        doSuc(rows, hasNext ? mPageIndex + 2 : mPageIndex);
    }

    //数据请求后失败处理
    protected void doError(Throwable throwable) {
        if (throwable.getMessage().equals("无数据")) {
            mAdapter.loadMoreEnd(false);
            swipeLayout.setEnabled(true);
            swipeLayout.setRefreshing(false);
        } else {
            swipeLayout.setEnabled(true);
            swipeLayout.setRefreshing(false);
            mAdapter.loadMoreFail();
        }

    }


    @NonNull
    @Override
    public synchronized Cache<String, Object> provideCache() {
        if (mCache == null) {
            mCache = ArmsUtils.obtainAppComponentFromContext(this).cacheFactory().build(CacheType.ACTIVITY_CACHE);
        }
        return mCache;
    }

    @NonNull
    @Override
    public final Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    final SupportActivityDelegate mDelegate = new SupportActivityDelegate(this);

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return mDelegate;
    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    @Override
    public ExtraTransaction extraTransaction() {
        return mDelegate.extraTransaction();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = convertAutoView(name, context, attrs);
        return view == null ? super.onCreateView(name, context, attrs) : view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            int layoutResID = initView(savedInstanceState);
            //如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
            if (layoutResID != 0) {
                setContentView(layoutResID);
                //绑定到butterknife
                mUnbinder = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            if (e instanceof InflateException) throw e;
            e.printStackTrace();
        }
        mDelegate.onCreate(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDelegate.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mDelegate.onDestroy();
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        this.mUnbinder = null;
        if (mPresenter != null)
            mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }

    /**
     * 是否使用 EventBus
     * Arms 核心库现在并不会依赖某个 EventBus, 要想使用 EventBus, 还请在项目中自行依赖对应的 EventBus
     * 现在支持两种 EventBus, greenrobot 的 EventBus 和畅销书 《Android源码设计模式解析与实战》的作者 何红辉 所作的 AndroidEventBus
     * 确保依赖后, 将此方法返回 true, Arms 会自动检测您依赖的 EventBus, 并自动注册
     * 这种做法可以让使用者有自行选择三方库的权利, 并且还可以减轻 Arms 的体积
     *
     * @return 返回 {@code true} (默认为 {@code true}), Arms 会自动注册 EventBus
     */
    @Override
    public boolean useEventBus() {
        return true;
    }

    /**
     * 这个 {@link Activity} 是否会使用 {@link Fragment}, 框架会根据这个属性判断是否注册 {@link FragmentManager.FragmentLifecycleCallbacks}
     * 如果返回 {@code false}, 那意味着这个 {@link Activity} 不需要绑定 {@link Fragment}, 那你再在这个 {@link Activity} 中绑定继承于 {@link BaseFragment} 的 {@link Fragment} 将不起任何作用
     *
     * @return 返回 {@code true} (默认为 {@code true}), 则需要使用 {@link Fragment}
     */
    @Override
    public boolean useFragment() {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        EventBusManager.getInstance().post(new DeviceActiveEvent());
        return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        EventBusManager.getInstance().post(new DeviceActiveEvent());
        return super.dispatchKeyEvent(event);
    }

    /**
     * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
     * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
     */
    @Override
    public void onBackPressedSupport() {
        mDelegate.onBackPressedSupport();
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    @Override
    public FragmentAnimator getFragmentAnimator() {
        return mDelegate.getFragmentAnimator();
    }

    /**
     * Set all fragments animation.
     * 设置Fragment内的全局动画
     */
    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     * <p/>
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return mDelegate.onCreateFragmentAnimator();
    }

    /**
     * Causes the Runnable r to be added to the action queue.
     * <p>
     * The runnable will be run after all the previous action has been run.
     * <p>
     * 前面的事务全部执行后 执行该Action
     */
    @Override
    public void post(Runnable runnable) {
        mDelegate.post(runnable);
    }
// 选择性拓展其他方法

    public void loadRootFragment(int containerId, @NonNull ISupportFragment toFragment) {
        mDelegate.loadRootFragment(containerId, toFragment);
    }

    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments) {
        mDelegate.loadMultipleRootFragment(containerId, showPosition, toFragments);
    }

    public void showFragment(ISupportFragment showFragment) {
        mDelegate.showHideFragment(showFragment);
    }


    public void start(ISupportFragment toFragment) {
        mDelegate.start(toFragment);
    }

    /**
     * @param launchMode Same as Activity's LaunchMode.
     */
    public void start(ISupportFragment toFragment, @ISupportFragment.LaunchMode int launchMode) {
        mDelegate.start(toFragment, launchMode);
    }

    /**
     * It is recommended to use {@link SupportFragment#startWithPopTo(ISupportFragment, Class, boolean)}.
     *
     * @see #popTo(Class, boolean)
     * +
     * @see #start(ISupportFragment)
     */
    public void startWithPopTo(ISupportFragment toFragment, Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment);
    }

    /**
     * Pop the fragment.
     */
    public void pop() {
        mDelegate.pop();
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * back stack.
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment);
    }

    /**
     * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
     * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim);
    }

    /**
     * 得到位于栈顶Fragment
     */
    public ISupportFragment getTopFragment() {
        return SupportHelper.getTopFragment(getSupportFragmentManager());
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
    }
}
