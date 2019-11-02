package com.stonetree.tablenow.factories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.stonetree.tablenow.models.Merchant
import com.stonetree.tablenow.repositories.MerchantsRepository
import com.stonetree.tablenow.sources.MerchantsDataSource

class MerchantsSourceFactory(
    private val repository: MerchantsRepository
) : DataSource.Factory<Long, Merchant>() {

    val data = MutableLiveData<MerchantsDataSource>()

    override fun create(): DataSource<Long, Merchant> {
        return MerchantsDataSource(repository).also { source ->
            data.postValue(source)
        }
    }
}
