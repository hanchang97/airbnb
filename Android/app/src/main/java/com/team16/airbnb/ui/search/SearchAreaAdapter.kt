package com.team16.airbnb.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team16.airbnb.databinding.ItemSearchAreaResultBinding

class SearchAreaAdapter(
    private val startActivity: () -> Unit
): ListAdapter<String, SearchAreaAdapter.SearchAreaHolder>(SearchAreaDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAreaHolder {
        val binding = ItemSearchAreaResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchAreaHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAreaHolder, position: Int) {
        holder.bind(getItem(position), startActivity)
    }

    class SearchAreaHolder(private val binding: ItemSearchAreaResultBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, startActivity: () -> Unit) {
            binding.tvAreaName.text = item

            binding.root.setOnClickListener {
                startActivity.invoke()
            }
        }

    }


    private object SearchAreaDiffUtil: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

    }
}