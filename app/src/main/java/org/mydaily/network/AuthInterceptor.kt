package org.mydaily.network

import okhttp3.Interceptor
import okhttp3.Response
import org.mydaily.data.local.FourMostPreference

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        FourMostPreference.getUserToken().let {
            requestBuilder.addHeader("jwt", it)
        }

        return chain.proceed(requestBuilder.build())
    }
}