package com.example.nit3213

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DashboardAdapter(
    private val onItemClick: (Destination) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    private val destinations = mutableListOf<Destination>()

    inner class DashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val destinationName: TextView = itemView.findViewById(R.id.destinationName)
        val country: TextView = itemView.findViewById(R.id.country)
        val bestSeason: TextView = itemView.findViewById(R.id.bestSeason)
        val popularAttraction: TextView = itemView.findViewById(R.id.popularAttraction)

        fun bind(destination: Destination) {
            destinationName.text = destination.destination
            country.text = destination.country
            bestSeason.text = "Best season: ${destination.bestSeason}"
            popularAttraction.text = "Attraction: ${destination.popularAttraction}"

            itemView.setOnClickListener {
                onItemClick(destination)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_destination, parent, false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bind(destinations[position])
    }

    override fun getItemCount(): Int = destinations.size

    fun submitList(newDestinations: List<Destination>) {
        destinations.clear()
        destinations.addAll(newDestinations)
        notifyDataSetChanged()
    }
}
