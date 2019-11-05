package com.stonetree.tablenow.models

import java.io.Serializable

data class Merchant(
    val name: String,
    val id: String,
    val reviewScore: Float,
    val location: Location,
    val images: List<Image>
) : Serializable
