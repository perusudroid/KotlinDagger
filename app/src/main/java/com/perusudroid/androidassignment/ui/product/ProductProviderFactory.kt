package com.perusudroid.androidassignment.ui.product

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.perusudroid.androidassignment.common.rx.SchedulerProvider
import com.perusudroid.androidassignment.data.IDataManagerHelper

class ProductProviderFactory(private val iDataManagerHelper : IDataManagerHelper,
                             private val schedulerProvider: SchedulerProvider,
                             private val mBundle : Bundle
                             ) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) run {
            return ProductViewModel(iDataManagerHelper, schedulerProvider,mBundle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: "+ modelClass.name)
    }
}