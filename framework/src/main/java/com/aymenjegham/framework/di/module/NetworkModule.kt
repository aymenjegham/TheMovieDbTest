package com.aymenjegham.framework.di.module

import android.util.Log
import com.aymenjegham.framework.datasource.api.EndpointInterceptor
import com.aymenjegham.framework.global.constants.CONNECT_TIMEOUT
import com.aymenjegham.framework.global.constants.READ_TIMEOUT
import com.aymenjegham.framework.global.constants.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
        Log.v(
            "Dvlt_log",
            message
        )
    }.run {
        level = HttpLoggingInterceptor.Level.BODY
        this
    }

    @Provides
    @Singleton
    fun providesOkHTTPClient(
        loggingInterceptor: HttpLoggingInterceptor,
        endpointInterceptor: EndpointInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(endpointInterceptor)
            .build()




}