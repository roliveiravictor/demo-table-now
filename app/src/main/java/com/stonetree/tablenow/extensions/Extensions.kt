package com.stonetree.tablenow.extensions

import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
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

fun DisplayMetrics.calculateHeight(proportion: Int) = heightPixels / proportion

fun DisplayMetrics.calculateWidth(proportion: Int) = widthPixels / proportion

fun View.resize(width: Int, height: Int) {
    context.resources.displayMetrics.let { metrics ->
        val layoutParams: ViewGroup.LayoutParams = layoutParams
        layoutParams.width = metrics.calculateWidth(width)
        layoutParams.height = metrics.calculateHeight(height)
        this.layoutParams = layoutParams
    }
}