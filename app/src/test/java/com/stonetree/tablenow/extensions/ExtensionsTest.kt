package com.stonetree.tablenow.extensions

import com.stonetree.tablenow.models.Address
import com.stonetree.tablenow.models.Coordinates
import com.stonetree.tablenow.models.Location
import com.stonetree.tablenow.models.Merchant
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ExtensionsTest {

    @Test
    fun createMapMark_shouldReturnFilled() {

        val coordinates = Coordinates(
            0.0,
            0.0
        )

        val address = Address(
            "mStreet",
            "mNumber"
        )

        val location = Location(coordinates, address)

        val merchant = Merchant(
            "mName",
            "mId",
            0.0f,
            location,
            arrayListOf()
        )

        merchant.apply {
            location.coordinates.apply {
                createMapMark().run {
                    assertEquals(id, title)
                    assertEquals(position.latitude, latitude)
                    assertEquals(position.longitude, longitude)
                }
            }
        }
    }
}