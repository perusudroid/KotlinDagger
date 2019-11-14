package com.perusudroid.androidassignment.data.remote

import com.perusudroid.androidassignment.data.model.brand.BrandResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("v3/brand/")
    fun getBrandList(@QueryMap request: Map<String, String>): Single<List<BrandResponse>>

    @GET("v2/seller_product/")
    fun getProductList(@QueryMap request: Map<String, String>): Single<ResponseBody>

}