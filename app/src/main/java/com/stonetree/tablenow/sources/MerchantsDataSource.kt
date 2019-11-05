package com.stonetree.tablenow.sources

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.stonetree.tablenow.constants.Constants.Params.Merchants.PAGE_LIMIT
import com.stonetree.tablenow.models.Merchant
import com.stonetree.tablenow.models.MerchantsPool
import com.stonetree.tablenow.repositories.MerchantsRepository

class MerchantsDataSource(
    private val repository: MerchantsRepository
) : PageKeyedDataSource<Long, Merchant>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Merchant>
    ) {
        repository.load {
            callback.onResult(this, null, PAGE_LIMIT.toLong())
        }
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, Merchant>
    ) {
        repository.lazy(params) { merchants ->
            callback.onResult(merchants, getNextKey(this, params))
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Merchant>) {
        Log.w(javaClass.name, params.key.toString())
    }

    private fun getNextKey(model: MerchantsPool, params: LoadParams<Long>): Long? {
        return if (model.size == params.key)
            null
        else
            params.key + PAGE_LIMIT
    }
}
