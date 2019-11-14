package com.perusudroid.androidassignment.ui.brand

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.perusudroid.androidassignment.R
import com.perusudroid.androidassignment.common.AppConstants
import com.perusudroid.androidassignment.data.model.NetworkState
import com.perusudroid.androidassignment.data.model.brand.BrandResponse
import com.perusudroid.androidassignment.ui.base.BaseActivity
import com.perusudroid.androidassignment.ui.base.IBaseRecyclerListener
import com.perusudroid.androidassignment.ui.base.ViewModelProviderFactory
import com.perusudroid.androidassignment.ui.product.ProductActivity
import kotlinx.android.synthetic.main.activity_brand.*
import javax.inject.Inject

class BrandActivity : BaseActivity() ,IBaseRecyclerListener{


    @Inject
    lateinit var factory : ViewModelProviderFactory
    @Inject
    lateinit var layoutManager: LinearLayoutManager
    @Inject
    lateinit var brandAdapter: BrandAdapter

    private lateinit var brandViewModel: BrandViewModel

    override fun postInit() {
        brandViewModel = ViewModelProviders.of(this, factory).get(BrandViewModel::class.java)
    }


    override fun setAssets() {
        supportActionBar?.title = getString(R.string.brands)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = brandAdapter
    }

    override fun initObservers() {
        brandViewModel.itemPagedList.observe(this, Observer {
            brandAdapter.submitList(it)
            vsParent.setChildVisible()
        })

        brandViewModel.initialLoad.observe(this, Observer {

            when(it.status){
                NetworkState.Status.FAILED->{
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

    override fun onClickItem(data: Any) {
        if(data is BrandResponse){
            startActivity(Intent(this, ProductActivity::class.java).apply {
                putExtra(AppConstants.BundleKeys.BRAND_ID, data.id)
            })
        }
    }

    override fun getLayoutIdToInflate(): Int = R.layout.activity_brand
}
