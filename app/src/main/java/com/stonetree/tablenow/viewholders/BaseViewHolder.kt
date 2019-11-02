package com.stonetree.freemoving.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    bind: ViewDataBinding
) : RecyclerView.ViewHolder(bind.root) {
    abstract fun onBind(data: T)
}
