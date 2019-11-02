package com.stonetree.tablenow.repositories

import androidx.paging.PageKeyedDataSource
import com.stonetree.restclient.feature.repository.CoreRepository
import com.stonetree.tablenow.models.Merchant
import com.stonetree.tablenow.models.MerchantsPool

interface MerchantsRepository {

    fun get(): CoreRepository

    fun load(callback: List<Merchant>.() -> Unit)

    fun lazy(
        params: PageKeyedDataSource.LoadParams<Long>,
        callback: MerchantsPool.(List<Merchant>) -> Unit
    )
}
