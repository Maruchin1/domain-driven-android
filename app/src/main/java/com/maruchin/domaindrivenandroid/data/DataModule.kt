package com.maruchin.domaindrivenandroid.data

import com.maruchin.domaindrivenandroid.data.coupon.CouponsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun couponsApi(retrofit: Retrofit): CouponsApi {
        return retrofit.create()
    }
}
