package com.stonetree.tablenow.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory.*
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.stonetree.tablenow.R
import com.stonetree.tablenow.constants.Constants.Map.Camera.ZOOM_DISTANCE
import com.stonetree.tablenow.databinding.ViewDetailsBinding
import com.stonetree.tablenow.viewmodels.DetailsViewModel
import com.stonetree.view.feature.fragment.CoreFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*
import kotlin.concurrent.timerTask

class DetailsView : CoreFragment() {

    private val args: DetailsViewArgs by navArgs()

    private val vm: DetailsViewModel by viewModel { parametersOf(args) }

    private lateinit var map: SupportMapFragment

    private var bannerPosition = 0

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

        startImageBanner(data)

        return data.root
    }

    private fun startImageBanner(data: ViewDetailsBinding) {
        val task = timerTask {
            args.merchant.apply {
                data.url = images[bannerPosition].url
                if (bannerPosition == images.size)
                    bannerPosition = 0
            }
        }
        Timer().schedule(task, 0, 3000)
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
        }
    }

    private fun bindMap() {
        map = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        map.getMapAsync { map ->
            val pos = vm.camera().position()
            map.animateCamera(newLatLngZoom(pos, ZOOM_DISTANCE))
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