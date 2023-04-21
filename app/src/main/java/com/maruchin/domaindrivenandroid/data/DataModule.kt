package com.maruchin.domaindrivenandroid.data

import com.maruchin.domaindrivenandroid.data.account.api.AccountApi
import com.maruchin.domaindrivenandroid.data.account.storage.AccountStorage
import com.maruchin.domaindrivenandroid.data.account.storage.DefaultAccountStorage
import com.maruchin.domaindrivenandroid.data.account.api.FakeAccountApi
import com.maruchin.domaindrivenandroid.data.coupon.api.CouponsApi
import com.maruchin.domaindrivenandroid.data.coupon.factory.ActivationCodeFactory
import com.maruchin.domaindrivenandroid.data.coupon.factory.DefaultActivationCodeFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun accountApi(impl: FakeAccountApi): AccountApi

    @Binds
    abstract fun accountStorage(impl: DefaultAccountStorage): AccountStorage

    @Binds
    abstract fun activationCodeFactory(impl: DefaultActivationCodeFactory): ActivationCodeFactory


    companion object {

        @Provides
        @Singleton
        fun couponsApi(retrofit: Retrofit): CouponsApi {
            return retrofit.create()
        }
    }
}
