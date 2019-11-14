package com.perusudroid.androidassignment.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.perusudroid.androidassignment.common.rx.SchedulerProvider
import com.perusudroid.androidassignment.data.IDataManagerHelper
import com.perusudroid.androidassignment.ui.brand.BrandViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(private val iDataManagerHelper : IDataManagerHelper,
                                                   private val schedulerProvider: SchedulerProvider
                                                   ) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrandViewModel::class.java)) run {
            return BrandViewModel(iDataManagerHelper, schedulerProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: "+ modelClass.name)
    }

}