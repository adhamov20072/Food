package com.aimardon.food.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aimardon.food.databinding.RecyclerItemBinding
import com.aimardon.food.models.Result
import com.bumptech.glide.Glide

class Adapter :ListAdapter<Result,Adapter.MyViewHolder>(diffUtil) {
    class MyViewHolder(val binding: RecyclerItemBinding):RecyclerView.ViewHolder(binding.root)
    companion object {
        val diffUtil=object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
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
        holder.binding.description.text= item.cuisines.toString()
        Glide.with(holder.itemView).load(item.image).into(holder.binding.imageView)
    }
}