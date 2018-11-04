package com.sqsong.wanandroid.ui.wechat.di

import com.sqsong.wanandroid.di.scope.ActivityScope
import com.sqsong.wanandroid.network.ApiService
import com.sqsong.wanandroid.ui.wechat.mvp.PublicAccountContract
import com.sqsong.wanandroid.ui.wechat.mvp.PublicAccountModel
import dagger.Module
import dagger.Provides

@Module
class PublicAccountModule {

    @ActivityScope
    @Provides
    fun providePublicAccountModel(apiService: ApiService): PublicAccountContract.Model {
        return PublicAccountModel(apiService)
    }

}