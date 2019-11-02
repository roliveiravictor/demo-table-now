package com.stonetree.tablenow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stonetree.tablenow.models.Merchant
import com.stonetree.tablenow.views.DetailsViewArgs

class DetailsViewModel(
    private val args: DetailsViewArgs
) : ViewModel() {

    val merchant: LiveData<Merchant> = MutableLiveData<Merchant>(args.merchant)
}
