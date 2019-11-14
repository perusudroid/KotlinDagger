package com.perusudroid.androidassignment.di.builder

import com.perusudroid.androidassignment.ui.brand.BrandActivity
import com.perusudroid.androidassignment.ui.brand.BrandModule
import com.perusudroid.androidassignment.ui.product.ProductActivity
import com.perusudroid.androidassignment.ui.product.ProductModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [BrandModule::class])
    abstract fun bindBrandActivity(): BrandActivity

    @ContributesAndroidInjector(modules = [ProductModule::class])
    abstract fun bindProductActivity(): ProductActivity

}