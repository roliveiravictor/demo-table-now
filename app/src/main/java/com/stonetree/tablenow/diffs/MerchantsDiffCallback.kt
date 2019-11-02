package com.stonetree.tablenow.diffs

import androidx.recyclerview.widget.DiffUtil
import com.stonetree.tablenow.models.Merchant

class MerchantsDiffCallback : DiffUtil.ItemCallback<Merchant>() {

    override fun areItemsTheSame(
        oldItem: Merchant,
        newItem: Merchant
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Merchant,
        newItem: Merchant
    ): Boolean {
        return oldItem.id == newItem.id
    }
}
