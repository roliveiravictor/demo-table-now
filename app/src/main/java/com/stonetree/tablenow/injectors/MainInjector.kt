package com.stonetree.tablenow.injectors

import com.stonetree.restclient.feature.RestClient
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import com.stonetree.restclient.feature.httpclient.CoreHttpClientImpl
import com.stonetree.restclient.feature.interceptor.RestClientInterceptor
import com.stonetree.restclient.feature.interceptor.RestClientInterceptorImpl
import com.stonetree.restclient.feature.network.NetworkChangeReceiverImpl
import com.stonetree.restclient.feature.network.NetworkReceiver
import com.stonetree.tablenow.adapters.MerchantsAdapter
import com.stonetree.tablenow.factories.MerchantsSourceFactory
import com.stonetree.tablenow.repositories.DetailsRepository
import com.stonetree.tablenow.repositories.DetailsRepositoryImpl
import com.stonetree.tablenow.viewmodels.MerchantsViewModel
import com.stonetree.tablenow.repositories.MerchantsRepository
import com.stonetree.tablenow.repositories.MerchantsRepositoryImpl
import com.stonetree.tablenow.sources.MerchantsDataSource
import com.stonetree.tablenow.viewmodels.DetailsViewModel
import com.stonetree.tablenow.views.DetailsViewArgs
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class MainInjector {

    private val details = module {
        factory<DetailsRepository> { DetailsRepositoryImpl() }
        viewModel { (args: DetailsViewArgs) -> DetailsViewModel(args, get()) }
    }

    private val merchants = module {
        factory { MerchantsAdapter() }
        factory { MerchantsDataSource(get()) }
        factory { MerchantsSourceFactory(get()) }

        single<MerchantsRepository> { MerchantsRepositoryImpl(get()) }

        viewModel {
            MerchantsViewModel(
                get(),
                get()
            )
        }
    }

    private val rest = module {
        factory<RestClientInterceptor> { RestClientInterceptorImpl() }
        factory<CoreHttpClient> { CoreHttpClientImpl(get()) }
        single<RestClient> { RestClientImpl(get()) }
        single<NetworkReceiver> { NetworkChangeReceiverImpl() }
    }

    fun startModules(): List<Module> {
        return arrayListOf(rest, merchants, details)
    }
}
