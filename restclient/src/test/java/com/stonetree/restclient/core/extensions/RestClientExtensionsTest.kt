package com.stonetree.restclient.core.extensions

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.core.model.Status
import com.stonetree.restclient.feature.idling.RestClientIdling.getResource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import okhttp3.Request
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(RobolectricTestRunner::class)
class RestClientExtensionsTest {

    private class CallTest : Call<String> {
        override fun enqueue(callback: Callback<String>) {
            // Do Nothing
        }

        override fun isExecuted(): Boolean {
            TODO("not implemented")
        }

        override fun clone(): Call<String> {
            TODO("not implemented")
        }

        override fun isCanceled(): Boolean {
            TODO("not implemented")
        }

        override fun cancel() {
            TODO("not implemented")
        }

        override fun execute(): Response<String> {
            TODO("not implemented")
        }

        override fun request(): Request {
            TODO("not implemented")
        }
    }

    @Test
    fun test_enqueue_shouldIncrementIdling() {
        val live = MutableLiveData<NetworkState>()
        CallTest().enqueue(live) {
            live.value?.apply {
                assertEquals(status, Status.RUNNING)
                onResponse = {
                    assertEquals(status, Status.SUCCESS)
                }

                onFailure = {
                    assertEquals(status, Status.FAILED)
                }
            }
        }
        assertFalse(getResource().isIdleNow)
    }
}