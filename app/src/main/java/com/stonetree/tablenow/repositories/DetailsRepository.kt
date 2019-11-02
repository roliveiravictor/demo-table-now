package com.stonetree.tablenow.repositories

import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.tablenow.models.Camera
import com.stonetree.tablenow.views.DetailsViewArgs

interface DetailsRepository {

    fun camera(args: DetailsViewArgs): Camera

    fun selectedMerchant(args: DetailsViewArgs): MarkerOptions
}