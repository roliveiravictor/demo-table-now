package com.stonetree.tablenow.binders

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.view.View
import android.widget.ImageView
import androidx.test.core.app.ApplicationProvider
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.core.model.NetworkState.Companion.LOADED
import com.stonetree.restclient.core.model.NetworkState.Companion.LOADING
import com.stonetree.tablenow.binders.bindIsIdle
import com.stonetree.view.core.bindIsLoading
import junit.framework.TestCase.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BindersTest {

    private val view: ImageView = ImageView(ApplicationProvider.getApplicationContext())

    @Test
    fun test_bindIsIdleOnSuccess_shouldReturnVisible() {
        bindIsIdle(view, LOADED)
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun test_bindIsIdleOnFailed_shouldReturnVisible() {
        bindIsIdle(view, NetworkState.error("mError"))
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun test_bindIsIdleOnRunning_shouldReturnGone() {
        bindIsIdle(view, LOADING)
        assertEquals(View.GONE, view.visibility)
    }

    @Test
    fun test_bindViewModeLandScape_shouldReturnGone() {
        bindViewMode(view, ORIENTATION_LANDSCAPE)
        assertEquals(View.GONE, view.visibility)
    }

    @Test
    fun test_bindViewModePortrait_shouldReturnGone() {
        bindViewMode(view, ORIENTATION_PORTRAIT)
        assertEquals(View.VISIBLE, view.visibility)
    }
}