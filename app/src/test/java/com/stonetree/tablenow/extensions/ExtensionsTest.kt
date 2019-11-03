package com.stonetree.tablenow.extensions

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import androidx.test.core.app.ApplicationProvider
import com.stonetree.tablenow.extensions.ExtensionsTest.Constants.PROPORTION
import com.stonetree.tablenow.models.Address
import com.stonetree.tablenow.models.Coordinates
import com.stonetree.tablenow.models.Location
import com.stonetree.tablenow.models.Merchant
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ExtensionsTest : AutoCloseKoinTest() {

    private val context: Context = ApplicationProvider.getApplicationContext()

    object Constants {
        const val PROPORTION = 2
    }

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
                    assertNull(title)
                    assertEquals(position.latitude, latitude)
                    assertEquals(position.longitude, longitude)
                }
            }
        }
    }

    @Test
    fun calculateHeight_shouldReturnProportioned() {
        context.resources.displayMetrics.apply {
            val result = calculateHeight(PROPORTION)
            assertEquals(heightPixels / PROPORTION, result)
        }
    }

    @Test
    fun calculateWidth_shouldReturnProportioned() {
        context.resources.displayMetrics.apply {
            val result = calculateWidth(PROPORTION)
            assertEquals(widthPixels / PROPORTION, result)
        }
    }

    @Test
    fun resize_shouldReturnProportionedLayoutParams() {
        val view = RecyclerView(context).apply {
            layoutParams = LayoutParams(100, 100)
        }
        view.apply {
            resize(PROPORTION, PROPORTION)
            assertEquals(235, layoutParams.height)
            assertEquals(160, layoutParams.width)
        }
    }
}