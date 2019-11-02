package com.stonetree.view.core

import android.view.View
import android.widget.ImageView
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BindersTest {

    private val view: ImageView = ImageView(ApplicationProvider.getApplicationContext())

    @Test
    fun isLoading_withFailedRequest_shouldReturnGone() {
        bindIsLoading( view, -1)
        assertEquals(view.visibility, View.GONE)
    }

    @Test
    fun isLoading_withRunningRequest_shouldReturnVisible() {
        bindIsLoading( view, 0)
        assertEquals(view.visibility, View.VISIBLE)
    }

    @Test
    fun isLoading_withLoadedRequest_shouldReturnGone() {
        bindIsLoading( view, 1)
        assertEquals(view.visibility, View.GONE)
    }

    @Test
    fun isFailure_withFailureRequest_shouldReturnVisible() {
        bindIsFailure( view, -1)
        assertEquals(view.visibility, View.VISIBLE)
    }

    @Test
    fun isFailure_withRunningRequest_shouldReturnGone() {
        bindIsFailure( view, 0)
        assertEquals(view.visibility, View.GONE)
    }

    @Test
    fun isFailure_withLoadedRequest_shouldReturnGone() {
        bindIsFailure( view, 1)
        assertEquals(view.visibility, View.GONE)
    }
}