package com.stonetree.restclient.feature

import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

class RestClientImpl(httpClient: CoreHttpClient) : RestClient {

    private val http: OkHttpClient = httpClient.create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.quandoo.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(http)
        .build()

    override fun key(): String = ""

    override fun <T : Any> generate(clazz: KClass<T>): T = retrofit.create(clazz.java)

}
