package com.stonetree.restclient.feature

import android.content.Context
import kotlin.reflect.KClass

interface RestClient {

    fun <T : Any> generate(clazz: KClass<T>): T

    fun key(): String
}