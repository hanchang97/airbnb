package com.team16.airbnb.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team16.airbnb.data.model.SearchResult
import com.team16.airbnb.databinding.FragmentSearchResultBinding
import com.team16.airbnb.databinding.ItemSearchResultBinding

class SearchResultAdapter :
    ListAdapter<SearchResult, SearchResultAdapter.SearchResultViewHolder>(SearchResultDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            ItemSearchResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchResultViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchResult) {

        }
    }

    private object SearchResultDiffUtil : DiffUtil.ItemCallback<SearchResult>() {

        override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult) =
            oldItem.roomId == oldItem.roomId

        override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult) =
            oldItem == newItem

    }

}