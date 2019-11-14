package com.perusudroid.androidassignment.ui.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity  : AppCompatActivity(), IView{

    override fun onCreate(savedInstanceState: Bundle?) {
        preInit()
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutIdToInflate())
        postInit()
        setAssets()
        initObservers()
    }

    abstract fun initObservers()

    fun preInit(){}
    abstract fun postInit()
    abstract fun setAssets()
    abstract fun getLayoutIdToInflate(): Int

    fun Context.showMessage(msg : String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun Context.printMessage(msg : String){
        Log.d(localClassName, msg)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}