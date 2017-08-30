package com.huebelancer.rosterdroid.ModelLayer

import com.huebelancer.rosterdroid.Helpers.Constants
import com.huebelancer.rosterdroid.Helpers.Helpers
import com.huebelancer.rosterdroid.Helpers.Helpers.Companion.allocatedCapRoom
import com.huebelancer.rosterdroid.ModelLayer.Models.Droid
import java.util.*
import kotlin.collections.LinkedHashMap

class ModelLayer {

    interface LoadDroidsCallback {
        fun onDroidsLoaded(droids: MutableList<Droid>, isResort: Boolean)
    }


    fun generateDroids(callback: LoadDroidsCallback) {
        /*
         * We're generating attribute spreads randomly using Random's nextInt
         * To make sure our players have somewhat of a balance, we're setting
         * min/max values for the nextInt function
        */
        val rand = Random()

        val droids = mutableListOf<Droid>()
        val starter_sub_pointValues: LinkedHashMap<String, Int> = Helpers.getPointValues()

        for (i in 1..15) {
            val isStarter = i > Constants.SUB_COUNT

            //figure out how much cap room this bot can consume
            val capRoom = allocatedCapRoom(i, remainingCap(droids), starter_sub_pointValues)

            //allocate given cap points to attributes
            val max: Int = capRoom / 2
            val min = if (isStarter) 2 else 1

            val strength = rand.nextInt((max - min) + 1) + min
            val speed    = rand.nextInt((max - min) + 1) + min
            val agility  = capRoom - (strength + speed)


            //generate a name
            val name = Constants.DROID_NAMES[i - 1] + numericSequence(rand)//simple 4 digit random number


            droids.add(
                Droid(name, strength, speed, agility, isStarter)
            )
        }

        droids.sortBy { droid ->
            droid.totalAttributes() * -1
        }

        //Interface back to fragment
        callback.onDroidsLoaded(droids, false)
    }


    private fun remainingCap(droids: MutableList<Droid>): Int {
        var capConsumed = 0

        droids.forEach { droid ->
            capConsumed += droid.totalAttributes()
        }

        return Constants.SALARY_CAP - capConsumed
    }


    private fun numericSequence(rand: Random): Int {
        return rand.nextInt(9000) + 1000
    }

}