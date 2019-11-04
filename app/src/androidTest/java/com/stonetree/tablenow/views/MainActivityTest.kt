package com.stonetree.tablenow.views

import android.Manifest
import android.app.Instrumentation
import android.content.Intent
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.intent.Intents.*
import androidx.test.espresso.intent.VerificationMode
import androidx.test.espresso.intent.VerificationModes
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule.grant
import com.linkedin.android.testbutler.TestButler
import com.stonetree.restclient.core.constants.RestClientConstants.ACTIONS.NETWORK_ERROR
import com.stonetree.tablenow.actions.waitFor
import okhttp3.internal.tls.OkHostnameVerifier.verify
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mockito.atLeast
import org.mockito.Mockito.atLeastOnce

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val rule = IntentsTestRule(MainActivity::class.java)

    private val dummy = Instrumentation.ActivityResult(
        -1,
        Intent()
    )

    @After
    fun tearDown() {
        TestButler.setWifiState(true)
        TestButler.setGsmState(true)
        onView(isRoot()).perform(waitFor(1500))
    }

    @Test
    @Ignore /* need to find a solution for atLeastOnce  mockito-espress */
    fun network_offline_shouldReturnNetworkError() {
        hasAction(NETWORK_ERROR).apply {
            intending(this).respondWith(dummy)
            TestButler.setWifiState(false)
            TestButler.setGsmState(false)
            onView(isRoot()).perform(waitFor(1500))
            intended(this) // -> intended(this, atLeastOnce()
        }
    }
}