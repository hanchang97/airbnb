package com.team16.airbnb.ui.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team16.airbnb.data.model.DayInfo
import com.team16.airbnb.databinding.ItemDaysBinding

class DayAdapter: ListAdapter<DayInfo, DayAdapter.DayViewHolder>(DayDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(ItemDaysBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DayViewHolder(private val binding: ItemDaysBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dayInfo: DayInfo) {
            binding.item = dayInfo
        }
    }

    private object DayDiffUtil: DiffUtil.ItemCallback<DayInfo>() {
        override fun areItemsTheSame(oldItem: DayInfo, newItem: DayInfo) =
            oldItem.hashCode() == newItem.hashCode()

        override fun areContentsTheSame(oldItem: DayInfo, newItem: DayInfo) =
            oldItem == newItem

    }

}