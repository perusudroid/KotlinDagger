package com.perusudroid.androidassignment.ui.product

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.perusudroid.androidassignment.common.AppConstants
import com.perusudroid.androidassignment.common.rx.SchedulerProvider
import com.perusudroid.androidassignment.data.IDataManagerHelper
import com.perusudroid.androidassignment.data.model.NetworkState
import com.perusudroid.androidassignment.data.model.product.ProductResponse
import com.perusudroid.androidassignment.ui.base.BaseViewModel
import okhttp3.ResponseBody
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class ProductViewModel(private val iDataManagerHelper: IDataManagerHelper,
                       private val schedulerProvider: SchedulerProvider,
                       mBundle : Bundle
) : BaseViewModel() {

    var productListObserver : MutableLiveData<List<ProductResponse>> = MutableLiveData()

    var initialLoad = MutableLiveData<NetworkState>()

    init {
        doFetchApi(mBundle.getInt(AppConstants.BundleKeys.BRAND_ID, 1))
    }

   private fun doFetchApi(brand_id : Int){
        compositeDisposable.add(
            iDataManagerHelper.getProductList(getReqParams(brand_id))
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { prodResponse: ResponseBody ->

                       val responseStr = getStringFromByte(prodResponse.byteStream())

                        val pr = Gson().fromJson<List<ProductResponse>>(responseStr, object : TypeToken<List<ProductResponse>>() {}.type)

                        Log.d(" pr.size ",  pr.size.toString())

                        if(pr.isNotEmpty()){

                            pr.forEach {
                                it.image?.pic = getRealPic(it.id, responseStr)
                            }

                            productListObserver.postValue(pr)
                        }else{
                            initialLoad.postValue(NetworkState(NetworkState.Status.EMPTY,"Empty"))
                        }
                    },
                    {
                        initialLoad.postValue(NetworkState(NetworkState.Status.FAILED,it.localizedMessage))
                    })
        )
    }

    private fun getRealPic(id: Int?, responseStr: String): String? {

        val jsonArray = JSONArray(responseStr)

        for (i in 0 until jsonArray.length()) {

            val item = jsonArray.getJSONObject(i)

            if(item.getInt("id") == id){
                val picObj = item.getJSONObject("image")

                return picObj.getString("0")
            }

        }

        return ""
    }

    private fun getStringFromByte(paramInputStream: InputStream?): String {
        val localStringBuilder = StringBuilder()
        val localBufferedReader = BufferedReader(InputStreamReader(paramInputStream!!))
        try {
            while (true) {
                val str = localBufferedReader.readLine() ?: break
                localStringBuilder.append(str)
            }
        } catch (localIOException: IOException) {
            localIOException.printStackTrace()
        }

        return localStringBuilder.toString()
    }

    private fun getReqParams(brand_id : Int): Map<String, String> {
        val data = HashMap<String, String>()
        data["brand_id"] = brand_id.toString()
        data["start_limit"] = 1.toString()
        data["end_limit"] = 20.toString()
        return data
    }


}