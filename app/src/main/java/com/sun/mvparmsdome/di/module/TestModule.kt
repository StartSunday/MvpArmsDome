package com.sun.mvparmsdome.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.sun.mvparmsdome.mvp.contract.TestContract
import com.sun.mvparmsdome.mvp.model.TestModel


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
@Module
//构建TestModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class TestModule(private val view: TestContract.View) {
    @ActivityScope
    @Provides
    fun provideTestView(): TestContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideTestModel(model: TestModel): TestContract.Model {
        return model
    }
}
