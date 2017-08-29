package com.huebelancer.rosterdroid.Activities.RosterList

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.huebelancer.rosterdroid.ModelLayer.Models.Droid
import com.huebelancer.rosterdroid.R

class DroidViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var context: Context? = null
    var cv: CardView? = null
    var name: TextView? = null
    var speed: TextView? = null
    var strength: TextView? = null
    var agility: TextView? = null
    var status: TextView? = null
    var totalScore: TextView? = null
    var icon: ImageView? = null

    //something to denote starter/bench

    init {
        context = view.context
        cv = view.findViewById(R.id.cardview)
        name = view.findViewById(R.id.droid_name)
        speed = view.findViewById(R.id.droid_speed)
        agility = view.findViewById(R.id.droid_agility)
        strength = view.findViewById(R.id.droid_strength)
        status = view.findViewById(R.id.droid_status)
        totalScore = view.findViewById(R.id.droid_totalScore)
        icon = view.findViewById(R.id.droid_icon)
    }

    fun configureWith(droid: Droid, position: Int) {
        name?.text = droid.name
        speed?.text = "${droid.speed}"
        strength?.text = "${droid.strength}"
        agility?.text = "${droid.agility}"
        totalScore?.text = "${droid.totalAttributes()}"
        if (droid.isStarter) {
            status?.text = context?.getString(R.string.droid_starter)
            icon?.setImageResource(R.drawable.ic_droidstarter)
        } else {
            status?.text = context?.getString(R.string.droid_substitute)
            icon?.setImageResource(R.drawable.ic_droidsub)
        }

    }
}