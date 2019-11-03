package com.stonetree.tablenow.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.stonetree.tablenow.adapters.MerchantsAdapter
import com.stonetree.tablenow.databinding.ViewMerchantsBinding
import com.stonetree.tablenow.viewmodels.MerchantsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MerchantsView : MainFragment() {

    private val adapter: MerchantsAdapter by inject()

    private val vm: MerchantsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = ViewMerchantsBinding.inflate(inflater, viewGroup, false)

        bindXml(bind, adapter)
        bindObservers(bind, adapter)

        return bind.root
    }

    override fun onRequestRetry() {
        vm.retry()
    }

    private fun bindXml(
        bind: ViewMerchantsBinding,
        adapter: MerchantsAdapter
    ) {
        bind.view = this@MerchantsView
        bind.merchants.adapter = adapter
    }

    private fun bindObservers(bind: ViewMerchantsBinding, adapter: MerchantsAdapter) {
        vm.merchants.observe(viewLifecycleOwner) { merchants ->
            adapter.submitList(merchants)
        }

        vm.network.observe(viewLifecycleOwner) { network ->
            bind.network = network
        }
    }
}
