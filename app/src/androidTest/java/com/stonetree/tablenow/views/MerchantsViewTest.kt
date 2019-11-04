package com.stonetree.tablenow.views

import android.content.Context
import android.widget.GridLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.stonetree.restclient.feature.RestClient
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import com.stonetree.restclient.feature.idling.RestClientIdling
import com.stonetree.tablenow.R
import com.stonetree.tablenow.stubs.HttpClientStub
import junit.framework.TestCase
import kotlinx.android.synthetic.main.view_merchants.*
import org.hamcrest.Matchers.*
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class MerchantsViewTest {

    private val context: Context = ApplicationProvider.getApplicationContext()

    @Rule
    @JvmField
    val rule = object : ActivityTestRule<NavigatorActivity>(
        NavigatorActivity::class.java
    ) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            val rest = module {
                factory<CoreHttpClient>(override = true) {
                    HttpClientStub(
                        get(),
                        context
                    )
                }
                single<RestClient>(override = true) { RestClientImpl(get()) }
            }

            loadKoinModules(rest)
        }
    }

    @Before
    fun setup() {
        rule.activity.findNavController(R.id.navigator).apply {
            navigate(R.id.view_merchants)
        }

        IdlingRegistry
            .getInstance()
            .register(RestClientIdling.getResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry
            .getInstance()
            .unregister(RestClientIdling.getResource())
    }

    @Test
    fun merchant_withName_shouldReturnVisible() {
        onView(
            allOf(
                withId(R.id.merchants),
                hasDescendant(
                    withText(
                        "Restaurant Dynasty"
                    )
                )
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun onCreate_shouldNotBeEmpty() {
        Assert.assertNotEquals(
            rule.activity.merchants.adapter?.itemCount,
            0
        )
    }

    @Test
    fun recyclerView_shouldReturnDefaultValues() {
        rule.activity.merchants.apply {
            TestCase.assertTrue(layoutManager is GridLayoutManager)
            val grid = (layoutManager as GridLayoutManager)
            grid.apply {
                TestCase.assertTrue(spanCount == 3)
                TestCase.assertTrue(orientation == GridLayout.VERTICAL)
            }
        }
    }
}