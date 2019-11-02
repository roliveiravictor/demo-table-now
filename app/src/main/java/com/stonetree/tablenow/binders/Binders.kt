package com.stonetree.tablenow.binders

import android.view.View
import androidx.databinding.BindingAdapter
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.core.model.Status

@BindingAdapter("isIdle")
fun bindIsIdle(view: View, network: NetworkState?) {
    when (network?.status) {
        Status.SUCCESS -> view.visibility = View.VISIBLE
        Status.FAILED -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}