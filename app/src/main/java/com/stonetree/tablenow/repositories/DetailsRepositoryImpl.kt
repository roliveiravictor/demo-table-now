package com.stonetree.tablenow.repositories

import com.stonetree.tablenow.extensions.createMapMark
import com.stonetree.tablenow.models.Camera
import com.stonetree.tablenow.views.DetailsViewArgs

class DetailsRepositoryImpl : MainRepository(), DetailsRepository {

    override fun camera(args: DetailsViewArgs) = Camera(
        args.merchant.location.coordinates
    )

    override fun selectedMerchant(args: DetailsViewArgs) = args.merchant.createMapMark()
}
