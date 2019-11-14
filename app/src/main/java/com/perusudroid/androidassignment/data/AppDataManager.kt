package com.perusudroid.androidassignment.data

import com.perusudroid.androidassignment.data.model.brand.BrandResponse
import com.perusudroid.androidassignment.data.remote.ApiInterface
import io.reactivex.Single
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(private val apiInterface: ApiInterface) : IDataManagerHelper{

    override fun getProductList(request: Map<String, String>): Single<ResponseBody> = apiInterface.getProductList(request)

    override fun getBrandList(request: Map<String, String>): Single<List<BrandResponse>> = apiInterface.getBrandList(request)

}