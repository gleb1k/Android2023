package com.example.android2023.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class MetricInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newURL = original.url.newBuilder()
            .addQueryParameter("units", "metric")
            .build()

        return chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )

    }

}
