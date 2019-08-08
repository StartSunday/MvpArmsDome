package com.sun.common_login.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.sun.common_login.mvp.contract.LoginContract
import com.sun.common_login.mvp.model.api.Api
import com.sun.common_login.mvp.model.entity.LoginBean
import com.sun.component.commonres.entity.BaseBean
import io.reactivex.Observable
import io.reactivex.Single


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
class LoginModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), LoginContract.Model {

    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun login(index: String, map: String): Observable<BaseBean<LoginBean>> =
            mRepositoryManager.obtainRetrofitService(Api::class.java)
                .login(index,map)

}
