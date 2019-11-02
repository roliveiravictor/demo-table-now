package com.stonetree.restclient.feature.network

import android.app.Instrumentation
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.intent.Intents.*
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import com.stonetree.restclient.core.constants.RestclientConstants
import junit.framework.TestCase.assertNotNull
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.any
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowNetworkInfo


@RunWith(RobolectricTestRunner::class)
class NetworkReceiverImplTest {

    private val context: Context = ApplicationProvider.getApplicationContext()

    private val dummy = Instrumentation.ActivityResult(
        -1,
        Intent()
    )

    private lateinit var network: NetworkChangeReceiverImpl

    private lateinit var manager: ConnectivityManager

    private lateinit var info: ShadowNetworkInfo

    @Before
    fun setup() {
        network = NetworkChangeReceiverImpl()
        manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        network.registerOfflineIntent(
            RestclientConstants.ACTIONS.NETWORK_ERROR,
            "mMessage"
        )

        context.registerReceiver(
            network.get(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    @Test
    fun get_coreRepository_shouldReturnInstance() {
        val result = network.get()
        assertNotNull(result)
        assertThat(result, `is`(any(BroadcastReceiver::class.java)))
    }

    @Test
    fun offlineMessageKey_shouldReturnNotNull() {
        assertNotNull(network.offlineMessageKey())
    }

    @Test
    @Ignore
    fun test() {
        /* Review NoSuchMethodError getActiveNetwork */
        toPackage("com.stonetree.restclient").apply {
            intending(this).respondWith(dummy)

            val intent = Intent(ConnectivityManager.CONNECTIVITY_ACTION)
            context.sendBroadcast(intent)

            intended(this)
        }
    }
}