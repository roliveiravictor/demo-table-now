package com.stonetree.tablenow.views

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.appham.mockinizer.RequestFilter
import com.stonetree.restclient.feature.RestClient
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import com.stonetree.restclient.feature.idling.RestClientIdling
import com.stonetree.tablenow.R
import com.stonetree.tablenow.constants.Constants
import com.stonetree.tablenow.stubs.HttpClientStub
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class BadResponseViewTest {

    private val mocks: Map<RequestFilter, MockResponse> = mapOf(
        RequestFilter(Constants.Endpoints.MERCHANTS) to MockResponse().apply {
            setResponseCode(500)
        }
    )

    @Rule
    @JvmField
    val rule = object : IntentsTestRule<NavigatorActivity>(
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
    fun request_badResponse_shouldReturnFailure() {
        onView(
            withId(R.id.bad_request_view)
        ).check(matches(isDisplayed()))
    }
}