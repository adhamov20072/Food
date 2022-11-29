package com.aimardon.food.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aimardon.food.databinding.RecyclerItemBinding
import com.bumptech.glide.Glide

class Adapter :ListAdapter<com.aimardon.food.models.Result,Adapter.MyViewHolder>(diffUtil) {
    class MyViewHolder(val binding: RecyclerItemBinding):RecyclerView.ViewHolder(binding.root)
    companion object {
        val diffUtil=object : DiffUtil.ItemCallback<com.aimardon.food.models.Result>() {
            override fun areItemsTheSame(oldItem: com.aimardon.food.models.Result, newItem: com.aimardon.food.models.Result): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: com.aimardon.food.models.Result, newItem: com.aimardon.food.models.Result): Boolean {
                return oldItem.id==newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.title.text=item.title
        holder.binding.description.text=item.gaps
        Glide.with(holder.itemView).load(item.image).into(holder.binding.imageView)
    }
}