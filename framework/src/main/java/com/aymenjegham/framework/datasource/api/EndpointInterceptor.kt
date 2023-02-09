package com.aymenjegham.framework.datasource.api

import android.content.Context
import com.aymenjegham.framework.global.extension.isNetworkAvailable
import com.aymenjegham.framework.global.utils.NoInternetException
import dagger.hilt.android.qualifiers.ApplicationContext
import io.dvlt.themoviedbtest.framework.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

private const val TOKEN = "Authorization"

@Singleton
class EndpointInterceptor @Inject constructor(
    @ApplicationContext private val context: Context,
) : Interceptor {

    @Throws(NoInternetException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (context.isNetworkAvailable()) {
            val requestBuilder = request.newBuilder().apply {
                addHeader(TOKEN, "Bearer ${BuildConfig.tmdbApiKeyV4}")
            }

            request = requestBuilder.build()

        } else {
            throw NoInternetException("No network available")
        }

        return chain.proceed(request)


    }
}