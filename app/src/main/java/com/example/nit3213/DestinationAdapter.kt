package com.example.nit3213

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213.databinding.ItemDestinationBinding

class DestinationAdapter(private val onClick: (Destination) -> Unit) :
    ListAdapter<Destination, DestinationAdapter.DestinationViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val binding = ItemDestinationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DestinationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DestinationViewHolder(private val binding: ItemDestinationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(destination: Destination) {
            binding.destinationName.text = destination.destination
            binding.country.text = destination.country
            binding.bestSeason.text = destination.bestSeason
            binding.popularAttraction.text = destination.popularAttraction

            binding.root.setOnClickListener {
                onClick(destination)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Destination>() {
        override fun areItemsTheSame(oldItem: Destination, newItem: Destination): Boolean =
            oldItem.destination == newItem.destination

        override fun areContentsTheSame(oldItem: Destination, newItem: Destination): Boolean =
            oldItem == newItem
    }
}
