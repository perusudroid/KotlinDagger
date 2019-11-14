package com.perusudroid.androidassignment.ui.brand

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.perusudroid.androidassignment.R
import com.perusudroid.androidassignment.data.model.brand.BrandResponse
import com.perusudroid.androidassignment.ui.base.IBaseRecyclerListener

class BrandAdapter(private val iBaseRecyclerListener: IBaseRecyclerListener) : PagedListAdapter<BrandResponse,BrandAdapter.ViewHolder>(DIFF()) {

    class DIFF  : DiffUtil.ItemCallback<BrandResponse>() {

        override fun areContentsTheSame(oldItem: BrandResponse, newItem: BrandResponse): Boolean {
            return oldItem.id!! == newItem.id!!
        }

        override fun areItemsTheSame(oldItem: BrandResponse, newItem: BrandResponse): Boolean {
            return oldItem.id!! == newItem.id!!
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.inflater_brand, parent, false), iBaseRecyclerListener)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataToView(getItem(position))
    }

    class ViewHolder(itemView : View, iBaseRecyclerListener : IBaseRecyclerListener) : RecyclerView.ViewHolder(itemView){

        private val ivPic : ImageView = itemView.findViewById(R.id.ivPic)
        private val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        private val rootLay : ConstraintLayout = itemView.findViewById(R.id.rootLay)

        init {
            rootLay.setOnClickListener {
                iBaseRecyclerListener.onClickItem(itemView.tag as BrandResponse)
            }
        }

        fun bindDataToView(brandResponse: BrandResponse?) {
            itemView.tag = brandResponse

            brandResponse?.let {

                Glide.with(itemView.context)
                    .load(it.logo)
                    .placeholder(R.mipmap.ic_launcher)
                    .fitCenter()
                    .into(ivPic)

                tvTitle.text = brandResponse.name
            }
        }

    }
}