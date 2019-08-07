package com.sun.mvparmsdome.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.sun.mvparmsdome.di.module.TestModule

import com.jess.arms.di.scope.ActivityScope
import com.sun.mvparmsdome.mvp.ui.activity.TestActivity


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
@ActivityScope
@Component(modules = arrayOf(TestModule::class), dependencies = arrayOf(AppComponent::class))
interface TestComponent {
    fun inject(activity: TestActivity)
}
