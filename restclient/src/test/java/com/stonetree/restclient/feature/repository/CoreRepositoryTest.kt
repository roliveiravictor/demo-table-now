package com.stonetree.restclient.feature.repository

import androidx.lifecycle.MutableLiveData
import junit.framework.TestCase.*
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class CoreRepositoryTest: AutoCloseKoinTest() {

    private val repository: Repository by inject()

    private class CoreRepositoryStub : CoreRepository()

    private val core = module {
        factory<Repository> { CoreRepositoryStub() }
    }

    @Before
    fun setup() {
        startKoin {
            loadKoinModules(core)
        }
    }

    @Test
    fun network_shouldReturnMutableLiveDataInstance() {
        repository.network().apply {
            assertNotNull(this)
            assertThat(
                this,
                `is`(any(MutableLiveData::class.java))
            )
        }
    }
}