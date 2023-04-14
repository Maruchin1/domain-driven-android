package com.maruchin.domaindrivenandroid.data

import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCodesApi
import com.maruchin.domaindrivenandroid.data.activationCode.FakeActivationCodesApi
import com.maruchin.domaindrivenandroid.data.coupon.CouponsApi
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
    abstract fun activationCodesApi(impl: FakeActivationCodesApi): ActivationCodesApi


    companion object {

        @Provides
        @Singleton
        fun couponsApi(retrofit: Retrofit): CouponsApi {
            return retrofit.create()
        }
    }
}
