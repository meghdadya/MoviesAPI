package com.github.moviesapi.ui.movies_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.moviesapi.R
import com.github.moviesapi.databinding.ListItemBinding
import com.github.moviesapi.network.response.Result
import kotlinx.android.synthetic.main.list_item.view.*

class MainListAdapter : PagingDataAdapter<Result, MainListAdapter.ViewHolder>(DataDifferntiator) {

    class ViewHolder(val listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.listItemBinding.item = getItem(position)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    object DataDifferntiator : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
}