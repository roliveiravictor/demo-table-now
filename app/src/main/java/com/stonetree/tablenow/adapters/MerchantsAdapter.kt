package com.stonetree.tablenow.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import com.stonetree.tablenow.R
import com.stonetree.tablenow.models.Merchant
import com.stonetree.tablenow.diffs.MerchantsDiffCallback
import com.stonetree.tablenow.viewholders.MerchantsViewHolder

class MerchantsAdapter : PagedListAdapter<Merchant, MerchantsViewHolder>(
    MerchantsDiffCallback()
) {
    override fun onBindViewHolder(holder: MerchantsViewHolder, position: Int) {
        getItem(position)?.let { merchant ->
            with(holder) {
                itemView.tag = merchant.id
                onBind(merchant)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MerchantsViewHolder {
        return MerchantsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_merchants, parent, false
            )
        )
    }
}
