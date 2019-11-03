package com.stonetree.tablenow.binders

import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.core.model.Status
import com.stonetree.tablenow.R
import kotlinx.android.synthetic.main.view_details.*

@BindingAdapter("isIdle")
fun bindIsIdle(view: View, network: NetworkState?) {
    when (network?.status) {
        Status.SUCCESS -> view.visibility = View.VISIBLE
        Status.FAILED -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}

@BindingAdapter("viewMode")
fun bindViewMode(view: View, mode: Int) {
    when (mode) {
        ORIENTATION_LANDSCAPE -> view.visibility = ConstraintLayout.GONE
        ORIENTATION_PORTRAIT -> view.visibility = ConstraintLayout.VISIBLE
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