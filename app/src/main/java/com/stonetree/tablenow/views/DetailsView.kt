package com.stonetree.tablenow.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.stonetree.tablenow.databinding.ViewDetailsBinding
import com.stonetree.tablenow.viewmodels.DetailsViewModel
import com.stonetree.view.feature.fragment.CoreFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsView : CoreFragment() {

    private val args: DetailsViewArgs by navArgs()

    private val vm: DetailsViewModel by viewModel { parametersOf(args) }

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = ViewDetailsBinding
            .inflate(inflater, viewGroup, false)

        bindXml(data)
        bindObservers(data)

        return data.root
    }

    private fun bindXml(data: ViewDetailsBinding) {
        data.view = this@DetailsView
    }

    private fun bindObservers(data: ViewDetailsBinding) {
        vm.merchant.observe(viewLifecycleOwner) { merchant ->
            data.title.text = merchant.id
            data.description.text = merchant.name
        }
    }
}