import com.stonetree.restclient.feature.RestClient
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import com.stonetree.restclient.feature.httpclient.CoreHttpClientImpl
import com.stonetree.restclient.feature.interceptor.RestClientInterceptor
import com.stonetree.restclient.feature.interceptor.RestClientInterceptorImpl
import com.stonetree.restclient.feature.network.NetworkChangeReceiverImpl
import com.stonetree.restclient.feature.network.NetworkReceiver
import org.koin.core.module.Module
import org.koin.dsl.module

class Injector {

    private val rest = module {
        factory<RestClientInterceptor> { RestClientInterceptorImpl() }
        factory<CoreHttpClient> { CoreHttpClientImpl(get()) }
        single<RestClient> { RestClientImpl(get()) }
        single<NetworkReceiver> { NetworkChangeReceiverImpl() }
    }

    fun startModules(): List<Module> {
        return arrayListOf(rest)
    }
}