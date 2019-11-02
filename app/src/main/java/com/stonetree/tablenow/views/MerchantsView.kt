package com.stonetree.tablenow.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.stonetree.tablenow.adapters.MerchantsAdapter
import com.stonetree.tablenow.databinding.ViewMerchantsBinding
import com.stonetree.tablenow.models.MerchantsViewModel
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
        val data = ViewMerchantsBinding.inflate(inflater, viewGroup, false)

        bindXml(data, adapter)
        bindObservers(data, adapter)

        return data.root
    }

    override fun onRequestRetry() {
        vm.retry()
    }

    private fun bindXml(
        data: ViewMerchantsBinding,
        adapter: MerchantsAdapter
    ) {
        data.view = this@MerchantsView
        data.merchants.adapter = adapter
    }

    private fun bindObservers(data: ViewMerchantsBinding, adapter: MerchantsAdapter) {
        vm.merchants.observe(viewLifecycleOwner) { merchants ->
            adapter.submitList(merchants)
        }

        vm.network.observe(viewLifecycleOwner) { network ->
            data.network = network
        }
    }
}
