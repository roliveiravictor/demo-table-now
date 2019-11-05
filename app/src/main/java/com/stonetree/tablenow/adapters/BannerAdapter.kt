package com.stonetree.tablenow.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.stonetree.tablenow.R
import com.stonetree.tablenow.binders.bindLoadImage
import com.stonetree.tablenow.interfaces.MerchantBanner
import com.stonetree.tablenow.models.Image

class BannerAdapter(
    private val images: List<Image>,
    private val banner: MerchantBanner
) : PagerAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(
        view: View,
        `object`: Any
    ): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(viewGroup: ViewGroup, position: Int): Any {
        val view = banner.view().layoutInflater.inflate(
            R.layout.item_merchant_banner,
            viewGroup,
            false
        )

        view.findViewById<ImageView>(R.id.banner_image).apply {
            bindLoadImage(this, images[position].url)
        }

        viewGroup.addView(view)

        return view
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as ConstraintLayout)
    }
}
