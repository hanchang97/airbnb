package com.team16.airbnb.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team16.airbnb.data.dto.near.NearResult
import com.team16.airbnb.data.model.Data
import com.team16.airbnb.data.model.NearInfo
import com.team16.airbnb.databinding.ItemPopularBinding
import com.team16.airbnb.databinding.ItemPopularHeaderBinding
import com.team16.airbnb.databinding.ItemSearchAreaResultBinding

class SearchAreaAdapter(
    private val startActivity: () -> Unit
): ListAdapter<String, SearchAreaAdapter.SearchAreaHolder>(diffUtil) {

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


    private object diffUtil: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

    }
}