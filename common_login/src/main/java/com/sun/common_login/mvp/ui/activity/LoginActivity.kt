package com.sun.common_login.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.sun.common_login.di.component.DaggerLoginComponent
import com.sun.common_login.di.module.LoginModule
import com.sun.common_login.mvp.contract.LoginContract
import com.sun.common_login.mvp.presenter.LoginPresenter

import com.sun.common_login.R
import com.sun.component.commonsdk.core.RouterHub
import kotlinx.android.synthetic.main.activity_login.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/08/2019 09:27
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
@Route(path = RouterHub.LOGINACTIVITY)
class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.View {


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .loginModule(LoginModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        login.setOnClickListener {
            mPresenter?.login(userName.text.toString(),password.text.toString())

        }
    }

    override fun loginSuccess() {

        ARouter.getInstance().build(RouterHub.TESTACTIVITY).navigation()
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
