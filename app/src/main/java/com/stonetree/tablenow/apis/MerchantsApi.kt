package com.stonetree.tablenow.apis

import com.stonetree.tablenow.constants.Constants.Endpoints
import com.stonetree.tablenow.constants.Constants.Params.Merchants.LIMIT
import com.stonetree.tablenow.constants.Constants.Params.Merchants.OFF_SET
import com.stonetree.tablenow.constants.Constants.Params.Merchants.PAGE_LIMIT
import com.stonetree.tablenow.models.MerchantsPool
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MerchantsApi {

    @GET(Endpoints.MERCHANTS)
    fun get(
        @Query(OFF_SET) offSet: Long ?= 0,
        @Query(LIMIT) limit: Int ?= PAGE_LIMIT
        ): Call<MerchantsPool>
}