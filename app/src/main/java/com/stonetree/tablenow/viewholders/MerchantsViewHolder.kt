package com.stonetree.tablenow.viewholders

import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.*
import androidx.navigation.findNavController
import com.stonetree.freemoving.viewholders.BaseViewHolder
import com.stonetree.tablenow.binders.bindLoadImage
import com.stonetree.tablenow.databinding.ItemMerchantsBinding
import com.stonetree.tablenow.directions.MerchantsDirections
import com.stonetree.tablenow.models.Merchant
import java.lang.Exception

class MerchantsViewHolder(
    private val bind: ItemMerchantsBinding
) : BaseViewHolder<Merchant>(bind) {

    override fun onBind(data: Merchant) {
        data.apply {
            bind.merchantName.text = name
            bind.url = data.images.firstOrNull()?.url
            bind.listener = createOnClickListener(data)
            bind.orientation = bind.root.resources.configuration.orientation
        }
    }

    private fun createOnClickListener(merchant: Merchant): View.OnClickListener? {
        return merchant.let {
            return@let View.OnClickListener { view ->
                val direction = MerchantsDirections.actionMerchantsToDetails(it)
                view.findNavController().navigate(direction)
            }
        }
    }
}
