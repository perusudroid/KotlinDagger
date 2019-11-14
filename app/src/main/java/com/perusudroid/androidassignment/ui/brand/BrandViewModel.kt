package com.perusudroid.androidassignment.ui.brand

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.perusudroid.androidassignment.common.rx.SchedulerProvider
import com.perusudroid.androidassignment.data.IDataManagerHelper
import com.perusudroid.androidassignment.data.model.NetworkState
import com.perusudroid.androidassignment.data.model.brand.BrandResponse
import com.perusudroid.androidassignment.ui.base.BaseViewModel

class BrandViewModel(
    private val iDataManagerHelper: IDataManagerHelper,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    lateinit var itemPagedList: LiveData<PagedList<BrandResponse>>
    lateinit var liveDataSource: LiveData<PageKeyedDataSource<Int, BrandResponse>>

     var initialLoad = MutableLiveData<NetworkState>()

    init {
        doConfigureAndFetch()
    }

    private fun doConfigureAndFetch() {

        val itemDataSourceFactory = BrandDataSourceFactory(compositeDisposable, iDataManagerHelper, schedulerProvider,initialLoad)

        liveDataSource = itemDataSourceFactory.itemLiveDataSource

        val config = PagedList.Config.Builder()
            // Number of items to fetch at once. [Required]
            .setPageSize(5)
            // Number of items to fetch on initial load. Should be greater than Page size. [Optional]
            .setInitialLoadSizeHint(5 * 2)
            .setEnablePlaceholders(true) // Show empty views until data is available
            .build()

        //noinspection unchecked
        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()

    }


}