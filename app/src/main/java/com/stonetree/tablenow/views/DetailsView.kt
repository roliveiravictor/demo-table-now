package com.stonetree.tablenow.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window.FEATURE_NO_TITLE
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.tablenow.R
import com.stonetree.tablenow.constants.Constants.Map.Camera.ZOOM_DISTANCE
import com.stonetree.tablenow.databinding.ViewDetailsBinding
import com.stonetree.tablenow.viewmodels.DetailsViewModel
import com.stonetree.view.feature.fragment.CoreFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsView : CoreFragment() {

    private val args: DetailsViewArgs by navArgs()

    private val vm: DetailsViewModel by viewModel { parametersOf(args) }

    private lateinit var map: SupportMapFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = ViewDetailsBinding
            .inflate(inflater, viewGroup, false)

        bindMap()
        bindXml(data)
        bindObservers(data)

        map.onCreate(savedInstanceState)
        markOnMap(arrayListOf(vm.selectedMerchant()))

        return data.root
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map.onLowMemory()
    }

    private fun bindXml(data: ViewDetailsBinding) {
        data.view = this@DetailsView
    }

    private fun bindObservers(data: ViewDetailsBinding) {
        vm.merchant.observe(viewLifecycleOwner) { merchant ->
            data.title.text = merchant.name
            data.address.text = merchant.location.address.toString()
            data.rating.rating = merchant.reviewScore
            data.url = merchant.images.first().url
        }
    }

    private fun bindMap() {
        map = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        map.getMapAsync { map ->
            val pos = vm.camera().position()
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, ZOOM_DISTANCE))
        }
    }

    private fun markOnMap(marks: List<MarkerOptions>) {
        map.getMapAsync { map ->
            marks.forEach { mark ->
                map.addMarker(mark)
            }
        }
    }
}