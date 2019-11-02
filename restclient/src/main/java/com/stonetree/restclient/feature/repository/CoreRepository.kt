package com.stonetree.restclient.feature.repository

import androidx.lifecycle.MutableLiveData
import com.stonetree.restclient.core.model.NetworkState

abstract class CoreRepository : Repository {

    private val network = MutableLiveData<NetworkState>()

    override fun network() = network

    override fun retry() {

    }
}