package com.sun.common_my.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.sun.common_my.mvp.contract.MyContract
import com.sun.common_my.mvp.model.MyModel


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
@Module
//构建MyModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MyModule(private val view: MyContract.View) {
    @ActivityScope
    @Provides
    fun provideMyView(): MyContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMyModel(model: MyModel): MyContract.Model {
        return model
    }
}
