package com.stonetree.tablenow.models

import java.lang.StringBuilder

data class Address(
    val street: String?,
    val number: String?
) {

    override fun toString(): String {
        val builder = StringBuilder()
        street?.apply { builder.append(street) }
        number?.apply {
            builder.append(", ")
            builder.append(number)
        }
        return builder.toString()
    }
}
