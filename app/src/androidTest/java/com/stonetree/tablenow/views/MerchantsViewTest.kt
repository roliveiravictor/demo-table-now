package com.stonetree.tablenow.views

import android.content.Context
import android.widget.GridLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.appham.mockinizer.RequestFilter
import com.stonetree.restclient.feature.RestClient
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import com.stonetree.restclient.feature.idling.RestClientIdling
import com.stonetree.tablenow.R
import com.stonetree.tablenow.constants.Constants.Endpoints
import com.stonetree.tablenow.extensions.readFile
import com.stonetree.tablenow.stubs.HttpClientStub
import junit.framework.TestCase
import junit.framework.TestCase.*
import kotlinx.android.synthetic.main.view_merchants.merchants
import okhttp3.mockwebserver.MockResponse
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class MerchantsViewTest {

    private val context: Context = ApplicationProvider.getApplicationContext()

    private val mocks: Map<RequestFilter, MockResponse> = mapOf(
        RequestFilter(Endpoints.MERCHANTS) to MockResponse().apply {
            setResponseCode(200)
            setBody("merchants.json".readFile(context))
        }
    )

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
                        mocks
                    )
                }
                single<RestClient>(override = true) { RestClientImpl(get()) }
            }

            loadKoinModules(rest)
        }
    }

    @Before
    fun setup() {
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
    fun recyclerView_shouldReturnDefaultValues() {
        rule.activity.merchants.apply {
            assertTrue(layoutManager is GridLayoutManager)
            val grid = (layoutManager as GridLayoutManager)
            grid.apply {
                assertTrue(spanCount == 3)
                assertTrue(orientation == GridLayout.VERTICAL)
            }
        }
    }
}