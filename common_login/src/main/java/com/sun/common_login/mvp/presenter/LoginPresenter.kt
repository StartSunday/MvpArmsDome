package com.sun.common_login.mvp.presenter

import android.app.Application

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.sun.common_login.mvp.contract.LoginContract
import com.sun.common_login.mvp.model.entity.LoginBean
import com.sun.component.commonres.entity.BaseBean
import com.sun.component.commonsdk.http.entity.DataResponse
import com.sun.component.commonsdk.http.entity.User
import com.sun.component.commonsdk.utils.RxUtil
import com.sun.component.commonsdk.utils.SPUtils
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber


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
@ActivityScope
class LoginPresenter
@Inject
constructor(model: LoginContract.Model, rootView: LoginContract.View) :
    BasePresenter<LoginContract.Model, LoginContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    override fun onDestroy() {
        super.onDestroy()
    }

    fun login(name: String, pwd: String){
        mModel.login(name, pwd)
            .compose(RxUtil.applySchedulers(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseBean<LoginBean>>(mErrorHandler) {
                override fun onNext(t: BaseBean<LoginBean>) {
                    SPUtils.getInstance().put("islogin",true)
                    mRootView.loginSuccess()
                }
            })
    }
}
