package com.stonetree.tablenow.views

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.Visibility.GONE
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.stonetree.tablenow.R
import com.stonetree.tablenow.constants.Constants
import com.stonetree.tablenow.mocks.Mocks
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsViewTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(NavigatorActivity::class.java)

    @Before
    fun setup() {
        rule.activity.findNavController(R.id.navigator).apply {
            navigate(
                R.id.view_details, Bundle().apply {
                    putSerializable(Constants.Bundle.MERCHANT, Mocks.merchant)
                }
            )
        }
    }

    @Test
    fun screenRotation_shouldReturnPagerVisible() {
        rule.activity.requestedOrientation = SCREEN_ORIENTATION_PORTRAIT
        onView(withId(R.id.banner_pager)).check(
            matches(
                withEffectiveVisibility(
                    VISIBLE
                )
            )
        )
    }

    @Test
    fun screenRotation_shouldReturnPagerGone() {
        rule.activity.requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
        onView(withId(R.id.banner_pager)).check(
            matches(
                withEffectiveVisibility(
                    GONE
                )
            )
        )
    }

    @Test
    fun screenRotation_shouldRetainData() {
        rule.activity.requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
        onView(withId(R.id.title)).check(
            matches(
                withText("mName")
            )
        )
    }
}