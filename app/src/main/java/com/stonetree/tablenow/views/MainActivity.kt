package com.stonetree.tablenow.views

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import com.stonetree.tablenow.constants.Constants.Actions.NAVIGATOR
import com.stonetree.restclient.core.constants.RestClientConstants
import com.stonetree.restclient.feature.network.NetworkReceiver
import com.stonetree.tablenow.R
import com.stonetree.view.feature.activity.CoreActivity
import org.koin.android.ext.android.inject

open class MainActivity : CoreActivity() {

    private val receiver: NetworkReceiver by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiver.registerOfflineIntent(
            RestClientConstants.ACTIONS.NETWORK_ERROR,
            getString(R.string.offline_message)
        )
        receiver.registerOnlineIntent(NAVIGATOR)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(
            receiver.get(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver.get())
    }
}
