package com.stonetree.tablenow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.tablenow.models.Merchant
import com.stonetree.tablenow.repositories.DetailsRepository
import com.stonetree.tablenow.views.DetailsViewArgs

class DetailsViewModel(
    private val args: DetailsViewArgs,
    private val repository: DetailsRepository
) : ViewModel() {

    fun camera() = repository.camera(args)

    fun selectedMerchant(): MarkerOptions = repository.selectedMerchant(args)

    val merchant: LiveData<Merchant> = MutableLiveData<Merchant>(args.merchant)
}
