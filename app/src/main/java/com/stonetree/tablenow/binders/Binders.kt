package com.stonetree.tablenow.binders

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.core.model.Status
import com.stonetree.tablenow.R

@BindingAdapter("isIdle")
fun bindIsIdle(view: View, network: NetworkState?) {
    when (network?.status) {
        Status.SUCCESS -> view.visibility = View.VISIBLE
        Status.FAILED -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}

@BindingAdapter("loadImage")
fun bindLoadImage(view: ImageView, url: String? = "") {
    view.tag = url
    Glide.with(view)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.loading_animation)
        .into(view)
}