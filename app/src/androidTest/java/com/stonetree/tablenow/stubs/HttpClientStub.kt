package com.stonetree.tablenow.stubs

import android.content.Context
import com.appham.mockinizer.RequestFilter
import com.appham.mockinizer.mockinize
import com.stonetree.restclient.core.constants.RestClientConstants.TIMEOUT
import com.stonetree.tablenow.extensions.readFile
import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import com.stonetree.restclient.feature.interceptor.RestClientInterceptor
import com.stonetree.tablenow.constants.Constants.Endpoints
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import java.util.concurrent.TimeUnit

class HttpClientStub(interceptor: RestClientInterceptor, context: Context) : CoreHttpClient {

    private val interceptor: HttpLoggingInterceptor = interceptor.log()

    private val mocks: Map<RequestFilter, MockResponse> = mapOf(
        RequestFilter(Endpoints.MERCHANTS) to MockResponse().apply {
            setResponseCode(200)
            setBody("merchants.json".readFile(context))
        }
    )

    override fun create() = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addNetworkInterceptor(interceptor)
        .mockinize(mocks)
        .build()
}