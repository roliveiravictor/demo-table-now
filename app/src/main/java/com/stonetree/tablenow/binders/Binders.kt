package com.stonetree.tablenow.binders

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy.AUTOMATIC
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.core.model.Status
import com.stonetree.tablenow.R
import com.stonetree.tablenow.extensions.calculateHeight
import com.stonetree.tablenow.extensions.calculateWidth
import com.stonetree.tablenow.extensions.resize


@BindingAdapter("isIdle")
fun bindIsIdle(view: View, network: NetworkState?) {
    when (network?.status) {
        Status.SUCCESS -> view.visibility = View.VISIBLE
        Status.FAILED -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}

@BindingAdapter("orientation")
fun bindOrientation(view: View, orientation: Int) {
    when (orientation) {
        ORIENTATION_LANDSCAPE -> view.visibility = ConstraintLayout.GONE
        ORIENTATION_PORTRAIT -> view.visibility = ConstraintLayout.VISIBLE
    }
}

@BindingAdapter("loadImage")
fun bindLoadImage(view: ImageView, url: String? = "") {
    view.tag = url
    view.context.resources.apply {
        Glide.with(view)
            .load(url)
            .override(
                displayMetrics.calculateHeight(2),
                displayMetrics.calculateWidth(3)
            )
            .diskCacheStrategy(AUTOMATIC)
            .placeholder(R.drawable.loading_animation)
            .into(view)
    }
}

@BindingAdapter("resize")
fun bindResize(view: View, orientation: Int) {
    when (orientation) {
        ORIENTATION_LANDSCAPE -> view.resize(3, 2)
        ORIENTATION_PORTRAIT -> view.resize(1, 4)
    }
}
