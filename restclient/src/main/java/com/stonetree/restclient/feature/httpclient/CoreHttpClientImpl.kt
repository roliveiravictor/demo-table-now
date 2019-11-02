package com.stonetree.restclient.feature.httpclient

import com.stonetree.restclient.core.constants.RestclientConstants.TIMEOUT
import com.stonetree.restclient.feature.interceptor.RestClientInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class CoreHttpClientImpl(private val interceptor: RestClientInterceptor) : CoreHttpClient {

    override fun create() = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(interceptor.log())
        .build()
}