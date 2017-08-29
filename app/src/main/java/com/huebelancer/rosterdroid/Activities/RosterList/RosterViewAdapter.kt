package com.huebelancer.rosterdroid.Activities.RosterList

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huebelancer.rosterdroid.Helpers.CustomItemClickListener
import com.huebelancer.rosterdroid.ModelLayer.Models.Droid
import com.huebelancer.rosterdroid.R

class RosterViewAdapter(
        var droids: MutableList<Droid>,
        var listener: CustomItemClickListener)
    : RecyclerView.Adapter<DroidViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DroidViewHolder {
        val droidView: View = LayoutInflater.from(parent?.context).inflate(R.layout.droid_item, parent, false)

        val holder: DroidViewHolder = DroidViewHolder(droidView)
        droidView.setOnClickListener({listener.onItemClick(droidView, holder.adapterPosition)})

        return holder
    }

    override fun getItemCount(): Int {
       return droids.size
    }

    override fun onBindViewHolder(holder: DroidViewHolder?, position: Int) {
        val droid: Droid = droids.get(position)
        holder?.configureWith(droid, position)
    }

}