package com.perusudroid.androidassignment.ui.brand

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.perusudroid.androidassignment.common.rx.SchedulerProvider
import com.perusudroid.androidassignment.data.IDataManagerHelper
import com.perusudroid.androidassignment.data.model.NetworkState
import com.perusudroid.androidassignment.data.model.brand.BrandResponse
import io.reactivex.disposables.CompositeDisposable


class BrandDataSource(private val compositeDisposable: CompositeDisposable,
                      private val iDataManagerHelper: IDataManagerHelper,
                      private val schedulerProvider: SchedulerProvider,
                      private val initLoad : MutableLiveData<NetworkState>
                      ) : PageKeyedDataSource<Int, BrandResponse>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, BrandResponse>
    ) {

        compositeDisposable.add(
            iDataManagerHelper.getBrandList(getReqParams(1))
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { brandResponse: List<BrandResponse> ->
                        if(brandResponse.isNotEmpty()){
                            callback.onResult(brandResponse, null, null)
                            //nextPageKey -> will change for pagination
                            //null to stop with first call
                        }else{
                            initLoad.postValue(NetworkState(NetworkState.Status.EMPTY,"Empty"))
                        }
                    },
                    {
                        initLoad.postValue(NetworkState(NetworkState.Status.FAILED,it.localizedMessage))
                    })
        )
    }


    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, BrandResponse>
    ) {


        compositeDisposable.add(
            iDataManagerHelper.getBrandList(getReqParams(params.key))
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { brandResponse: List<BrandResponse> ->
                        if (brandResponse.isNotEmpty()) {
                            val key = if (params.key > 1) params.key - 1 else null
                            callback.onResult(brandResponse, key)
                        } else {
                            initLoad.postValue(
                                NetworkState( NetworkState.Status.EMPTY, "No More data"))
                        }
                    },
                    {
                        initLoad.postValue(NetworkState(NetworkState.Status.FAILED, it.localizedMessage))
                    })
        )

    }


    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, BrandResponse>
    ) {

        compositeDisposable.add(
            iDataManagerHelper.getBrandList(getReqParams(params.key))
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { brandResponse: List<BrandResponse> ->
                        //do handling here!
                        callback.onResult( brandResponse,
                            if (params.key < 5) params.key + 1 else null
                        )
                    },
                    {
                        initLoad.postValue(NetworkState(NetworkState.Status.FAILED, it.localizedMessage))
                    })
        )

    }


    private fun getReqParams(start_limit : Int): Map<String, String> {
        val data = HashMap<String, String>()
        data["brand_type_id"] = 27.toString()
        data["start_limit"] = start_limit.toString()
        data["end_limit"] = 20.toString()
        return data
    }

}