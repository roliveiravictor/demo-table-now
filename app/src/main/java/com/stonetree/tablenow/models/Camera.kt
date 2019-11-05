package com.stonetree.tablenow.models

import com.google.android.gms.maps.model.LatLng

class Camera(private val coordinate: Coordinates) {

    fun position(): LatLng {
        coordinate.apply {
            return LatLng(latitude, longitude)
        }
    }
}
