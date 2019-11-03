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
    Glide.with(view)
        .load(url)
        .diskCacheStrategy(AUTOMATIC)
        .placeholder(R.drawable.loading_animation)
        .into(view)
}

@BindingAdapter("resize")
fun bindResize(view: View, orientation: Int) {

    when (orientation) {
        ORIENTATION_LANDSCAPE -> {
            val layoutParams: ViewGroup.LayoutParams = view.layoutParams
            layoutParams.height = view.context.resources.displayMetrics.heightPixels / 2
            layoutParams.width = view.context.resources.displayMetrics.widthPixels / 3
            view.layoutParams = layoutParams
        }
        ORIENTATION_PORTRAIT -> {
            val layoutParams: ViewGroup.LayoutParams = view.layoutParams
            layoutParams.height = view.context.resources.displayMetrics.heightPixels / 6
            layoutParams.width = view.context.resources.displayMetrics.widthPixels / 2
            view.layoutParams = layoutParams
        }
    }
}