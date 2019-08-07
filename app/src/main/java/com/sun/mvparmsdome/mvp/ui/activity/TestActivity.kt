package com.sun.mvparmsdome.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.sun.mvparmsdome.di.component.DaggerTestComponent
import com.sun.mvparmsdome.di.module.TestModule
import com.sun.mvparmsdome.mvp.contract.TestContract
import com.sun.mvparmsdome.mvp.presenter.TestPresenter

import com.sun.mvparmsdome.R
import com.sun.component.commonsdk.core.RouterHub
import kotlinx.android.synthetic.main.activity_test.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/07/2019 14:16
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
@Route(path = RouterHub.TESTACTIVITY)
class TestActivity : BaseActivity<TestPresenter>(), TestContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerTestComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .testModule(TestModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {

        return R.layout.activity_test //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        tv.setOnClickListener {
//            ArmsUtils.snackbarText("222222")
            ARouter.getInstance().build(RouterHub.HOMEACTIVITY).navigation()
        }
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }
}
