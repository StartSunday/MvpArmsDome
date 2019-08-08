package com.sun.common_login.mvp.contract

import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel
import com.sun.common_login.mvp.model.entity.LoginBean
import com.sun.component.commonres.entity.BaseBean
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Field


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
interface LoginContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView{
        fun loginSuccess()
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel{
        abstract fun login( index: String, map: String): Observable<BaseBean<LoginBean>>
    }

}
