package com.perusudroid.androidassignment.ui.product

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.perusudroid.androidassignment.R
import com.perusudroid.androidassignment.data.model.NetworkState
import com.perusudroid.androidassignment.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_brand.*
import javax.inject.Inject

class ProductActivity : BaseActivity() {

    @Inject
    lateinit var factory : ProductProviderFactory
    @Inject
    lateinit var layoutManager: LinearLayoutManager
    @Inject
    lateinit var productsAdapter: ProductsAdapter

    private lateinit var productViewModel: ProductViewModel

    override fun postInit() {
        productViewModel = ViewModelProviders.of(this, factory).get(ProductViewModel::class.java)
    }

    override fun initObservers() {

        productViewModel.productListObserver.observe(this, Observer {
            productsAdapter.refresh(it)
            vsParent.setChildVisible()
        })

        productViewModel.initialLoad.observe(this, Observer {

            when(it.status){
                NetworkState.Status.FAILED-> {
                    vsParent.setChildVisible()
                    vsChild.setChildVisible()
                }
                NetworkState.Status.EMPTY->{
                    vsParent.setChildVisible()
                    vsChild.setChildVisible()
                }
                NetworkState.Status.NO_LOAD_MORE-> showMessage(it.msg!!)
                else -> printMessage("Still ${it.status}")
            }

        })

    }

    override fun setAssets() {
        supportActionBar?.title = getString(R.string.products)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = productsAdapter
    }

    override fun getLayoutIdToInflate(): Int = R.layout.activity_product


}
