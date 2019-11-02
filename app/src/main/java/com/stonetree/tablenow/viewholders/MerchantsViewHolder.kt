package com.stonetree.tablenow.viewholders

import android.view.View
import com.stonetree.freemoving.viewholders.BaseViewHolder
import com.stonetree.tablenow.databinding.ItemMerchantsBinding
import com.stonetree.tablenow.models.Merchant

class MerchantsViewHolder(
    private val bind: ItemMerchantsBinding
) : BaseViewHolder<Merchant>(bind) {

    override fun onBind(data: Merchant) {
        data.apply {
            bind.merchantName.text = name
            bind.listener = createOnClickListener(data)
        }
    }

    private fun createOnClickListener(merchant: Merchant): View.OnClickListener? {
        return merchant.let {
            return@let View.OnClickListener { view ->
                // Do nothing
            }
        }
    }
}
