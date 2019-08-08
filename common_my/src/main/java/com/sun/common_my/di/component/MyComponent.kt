package com.sun.common_my.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.sun.common_my.di.module.MyModule

import com.jess.arms.di.scope.ActivityScope
import com.sun.common_my.mvp.ui.activity.MyActivity


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/08/2019 09:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = arrayOf(MyModule::class), dependencies = arrayOf(AppComponent::class))
interface MyComponent {
    fun inject(activity: MyActivity)
}
