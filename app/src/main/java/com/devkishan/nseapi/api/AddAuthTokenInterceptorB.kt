package com.devkishan.nseapi.api

import okhttp3.Interceptor
import okhttp3.Response

class AddAuthTokenInterceptorB:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer<Token>")
            .build()

return chain.proceed(request)
    }
}