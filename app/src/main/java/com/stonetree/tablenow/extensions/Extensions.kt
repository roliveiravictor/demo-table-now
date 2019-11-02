package com.stonetree.tablenow.extensions

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.tablenow.models.Merchant

fun Merchant.createMapMark(): MarkerOptions {
    val position = LatLng(
        location.coordinates.latitude,
        location.coordinates.longitude
    )

    return MarkerOptions()
        .position(position)
}