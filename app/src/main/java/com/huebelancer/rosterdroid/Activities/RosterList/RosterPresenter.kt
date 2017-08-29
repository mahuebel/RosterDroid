package com.huebelancer.rosterdroid.Activities.RosterList

import com.huebelancer.rosterdroid.ModelLayer.Enums.Sorters
import com.huebelancer.rosterdroid.ModelLayer.ModelLayer
import com.huebelancer.rosterdroid.ModelLayer.Models.Droid


class RosterPresenter(val modelLayer: ModelLayer, val fragment: RosterFragment) {
    fun loadDroids() {
        modelLayer.generateDroids(fragment)
    }

    fun sort(sorter: Sorters, droids: MutableList<Droid>) {
        when (sorter) {
            Sorters.OVR -> {
                droids.sortBy { droid ->
                    droid.totalAttributes() * -1
                }
            }
            Sorters.SPD -> {
                droids.sortBy { droid ->
                    droid.speed * -1
                }
            }
            Sorters.AGI -> {
                droids.sortBy { droid ->
                    droid.agility * -1
                }
            }
            Sorters.STR -> {
                droids.sortBy { droid ->
                    droid.strength * -1
                }
            }
        }

        fragment.onDroidsLoaded(droids, true)
    }
}