package com.huebelancer.rosterdroid.ModelLayer

import com.huebelancer.rosterdroid.Helpers.Constants
import com.huebelancer.rosterdroid.Helpers.Helpers.Companion.allocatedCapRoom
import com.huebelancer.rosterdroid.ModelLayer.Models.Droid
import java.util.*

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

        for (i in 0..14) {
            //figure out how much cap room I want to take
            val capRoom = allocatedCapRoom(remainingCap(droids), 15 - i)
            val isStarter = i < 10

            //allocate given cap points to attributes
            val max: Int = capRoom / 2
            val min = if (isStarter) 2 else 1

            val strength = rand.nextInt((max - min) + 1) + min
            val speed    = rand.nextInt((max - min) + 1) + min
            val agility  = capRoom - (strength + speed)


            //generate a name
            val name = Constants.DROID_NAMES[i] + numericSequence(rand)//simple 4 digit random number


            droids.add(
                Droid(name, strength, speed, agility, isStarter)
            )
        }

        droids.sortBy { droid ->
            droid.totalAttributes()
        }

        droids.reverse()

        //Interface back to fragment
        callback.onDroidsLoaded(droids, false)
    }


    fun remainingCap(droids: MutableList<Droid>): Int {
        var capConsumed = 0

        droids.forEach { droid ->
            capConsumed += droid.totalAttributes()
        }

        return Constants.SALARY_CAP - capConsumed
    }


    fun numericSequence(rand: Random): Int {
        return rand.nextInt(9000) + 1000
    }

}