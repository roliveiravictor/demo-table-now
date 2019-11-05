package com.stonetree.tablenow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.stonetree.restclient.core.constants.RestClientConstants.MAX_THREADS
import com.stonetree.restclient.core.constants.RestClientConstants.PAGE_SIZE
import com.stonetree.restclient.core.constants.RestClientConstants.PRE_FETCH_DISTANCE
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.tablenow.factories.MerchantsSourceFactory
import com.stonetree.tablenow.models.Merchant
import com.stonetree.tablenow.repositories.MerchantsRepository
import java.util.concurrent.Executors

class MerchantsViewModel(
    private val repository: MerchantsRepository,
    private val factory: MerchantsSourceFactory
) : ViewModel() {

    private val config: PagedList.Config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(PRE_FETCH_DISTANCE)
        .setEnablePlaceholders(false)
        .build()

    val merchants: LiveData<PagedList<Merchant>> =
        LivePagedListBuilder(factory, config)
            .setFetchExecutor(Executors.newFixedThreadPool(MAX_THREADS))
            .build()

    val network: LiveData<NetworkState> = repository.get().network()

    fun retry() = factory.data.value?.invalidate()
}
