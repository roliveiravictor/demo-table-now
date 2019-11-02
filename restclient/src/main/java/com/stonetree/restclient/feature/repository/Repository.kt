package com.stonetree.restclient.feature.repository

import androidx.lifecycle.MutableLiveData
import com.stonetree.restclient.core.model.NetworkState

interface Repository {

    fun network(): MutableLiveData<NetworkState>

    fun retry()
}