package com.perusudroid.androidassignment.ui.product

import android.os.Bundle
import com.perusudroid.androidassignment.common.rx.SchedulerProvider
import com.perusudroid.androidassignment.data.IDataManagerHelper
import dagger.Module
import dagger.Provides

@Module
class ProductModule  {

    @Provides
    fun providesProductAdapter() : ProductsAdapter = ProductsAdapter()

    @Provides
    fun providesBundle(productActivity: ProductActivity) : Bundle{
        return productActivity.intent.extras!!
    }

    @Provides
    fun providerProductProviderFactory(bundle : Bundle, dataManager: IDataManagerHelper, schedulerProvider: SchedulerProvider) : ProductProviderFactory{
        return ProductProviderFactory(dataManager, schedulerProvider, bundle)
    }

}