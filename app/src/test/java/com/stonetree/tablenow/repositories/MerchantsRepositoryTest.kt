package com.stonetree.tablenow.repositories

import com.stonetree.restclient.feature.RestClient
import com.stonetree.restclient.feature.repository.CoreRepository
import junit.framework.TestCase.*
import org.hamcrest.MatcherAssert.*
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.any
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MerchantsRepositoryTest : AutoCloseKoinTest() {

    private val rest = mock(RestClient::class.java)

    private val repository: MerchantsRepository by inject()

    @Test
    fun get_shouldReturnCoreRepositoryInstance() {
        repository.get().apply {
            assertNotNull(this)
            assertThat(
                this,
                `is`(any(CoreRepository::class.java))
            )
        }
    }
}