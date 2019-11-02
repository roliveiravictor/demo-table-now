package com.stonetree.tablenow.repositories

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.stonetree.restclient.core.extensions.enqueue
import com.stonetree.restclient.feature.RestClient
import com.stonetree.tablenow.apis.MerchantsApi
import com.stonetree.tablenow.models.Merchant
import com.stonetree.tablenow.models.MerchantsPool
import retrofit2.Call

class MerchantsRepositoryImpl(
    private val client: RestClient
) : MainRepository(), MerchantsRepository {

    private val api: MerchantsApi = client.generate(MerchantsApi::class)

    private lateinit var request: Call<MerchantsPool>

    override fun get() = this

    override fun load(callback: List<Merchant>.() -> Unit) {
        request = api.get()
        request.enqueue(network()) {
            onResponse = { response ->
                response.body()?.merchants?.let { merchants ->
                    callback.invoke(merchants)
                }
            }

            onFailure = { error ->
                error?.apply {
                    Log.e(javaClass.name, message.toString())
                }
            }
        }
    }

    override fun lazy(
        params: PageKeyedDataSource.LoadParams<Long>,
        callback: MerchantsPool.(List<Merchant>) -> Unit
    ) {
        request = api.get(params.key)
        request.enqueue(null) {
            onResponse = { response ->
                response.body()?.apply {
                    merchants.let { merchant ->
                        callback.invoke(this, merchant)
                    }
                }
            }

            onFailure = { error ->
                error?.apply {
                    Log.e(javaClass.name, message.toString())
                }
            }
        }
    }
}