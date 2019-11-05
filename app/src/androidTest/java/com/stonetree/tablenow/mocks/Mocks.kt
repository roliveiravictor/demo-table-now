package com.stonetree.tablenow.mocks

import com.stonetree.tablenow.models.Address
import com.stonetree.tablenow.models.Coordinates
import com.stonetree.tablenow.models.Location
import com.stonetree.tablenow.models.Merchant

object Mocks {

    private val address = Address(
        "",
        ""
    )

    private val coordinates = Coordinates(
        0.0,
        0.0
    )

    private val location = Location(
        coordinates,
        address
    )

    val merchant: Merchant = Merchant(
        "mName",
        "",
        0F,
        location,
        arrayListOf()
    )
}