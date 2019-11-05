package com.stonetree.tablenow.models

data class MerchantsPool(
    val merchants: List<Merchant>,
    val size: Long,
    val offset: Long
)
