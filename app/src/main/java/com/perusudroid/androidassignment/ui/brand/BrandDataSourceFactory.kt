package com.perusudroid.androidassignment.ui.brand

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.perusudroid.androidassignment.common.rx.SchedulerProvider
import com.perusudroid.androidassignment.data.IDataManagerHelper
import com.perusudroid.androidassignment.data.model.NetworkState
import com.perusudroid.androidassignment.data.model.brand.BrandResponse
import io.reactivex.disposables.CompositeDisposable


class BrandDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                             private val iDataManagerHelper: IDataManagerHelper,
                             private val schedulerProvider: SchedulerProvider,
                             private val initLoad : MutableLiveData<NetworkState>) : DataSource.Factory<Int, BrandResponse>() {

    val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, BrandResponse>>()


    override fun create(): DataSource<Int, BrandResponse> {
        val bds = BrandDataSource(compositeDisposable, iDataManagerHelper, schedulerProvider,initLoad)
        itemLiveDataSource.postValue(bds)
        return bds
    }


}