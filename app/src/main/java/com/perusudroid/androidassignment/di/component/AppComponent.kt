package com.perusudroid.androidassignment.di.component

import android.app.Application
import com.perusudroid.androidassignment.common.AppController
import com.perusudroid.androidassignment.di.builder.ActivityBuilder
import com.perusudroid.androidassignment.di.module.AppModule
import com.perusudroid.androidassignment.ui.brand.BrandModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,AppModule::class,BrandModule::class, ActivityBuilder::class])
interface AppComponent {

    fun inject(appController: AppController)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder

    }

}