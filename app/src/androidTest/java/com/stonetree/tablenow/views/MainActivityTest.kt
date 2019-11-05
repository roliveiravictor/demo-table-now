package com.stonetree.tablenow.views

import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.Intents.times
import androidx.test.espresso.intent.Intents.getIntents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.linkedin.android.testbutler.TestButler
import com.stonetree.restclient.core.constants.RestClientConstants.ACTIONS.NETWORK_ERROR
import com.stonetree.tablenow.actions.waitFor
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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
    fun network_offline_shouldReturnNetworkError() {
        hasAction(NETWORK_ERROR).apply {
            intending(this).respondWith(dummy)
            TestButler.setWifiState(false)
            TestButler.setGsmState(false)

            /*
            *  Workaround to prevent Espresso's lack of atLeastOnce()
            *  times(getIntents().size) [Espresso] -> atLeastOnce() [Mockito]
            */
            intended(this, times(getIntents().size))
        }
    }
}