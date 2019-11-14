package com.perusudroid.androidassignment.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.perusudroid.androidassignment.R
import com.perusudroid.androidassignment.data.model.product.ProductResponse

class ProductsAdapter() : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    var productList : List<ProductResponse> = ArrayList()

    fun refresh(newList : List<ProductResponse>){
        productList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.inflater_brand, parent, false))

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataToView(productList[position])
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val ivPic : ImageView = itemView.findViewById(R.id.ivPic)
        private val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)

        fun bindDataToView(productResponse: ProductResponse) {

            productResponse.let {

                Glide.with(itemView.context)
                    .load(it.image?.pic)
                    .placeholder(R.mipmap.ic_launcher)
                    .fitCenter()
                    .into(ivPic)

                tvTitle.text = it.name
            }
        }

    }
}