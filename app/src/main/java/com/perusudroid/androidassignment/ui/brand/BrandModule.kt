package com.perusudroid.androidassignment.ui.brand

import com.perusudroid.androidassignment.ui.base.IBaseRecyclerListener
import dagger.Module
import dagger.Provides

@Module
class BrandModule {

    @Provides
    fun providesBrandAdapter(iBaseRecyclerListener: IBaseRecyclerListener) : BrandAdapter = BrandAdapter(iBaseRecyclerListener)

    @Provides
    fun providesIBaseRecyclerListener(brandActivity: BrandActivity) : IBaseRecyclerListener{
        return brandActivity
    }
}