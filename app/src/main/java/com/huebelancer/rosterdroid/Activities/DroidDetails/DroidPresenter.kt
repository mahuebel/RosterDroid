package com.huebelancer.rosterdroid.Activities.DroidDetails

import com.huebelancer.rosterdroid.Helpers.Constants
import com.huebelancer.rosterdroid.Helpers.Helpers
import com.huebelancer.rosterdroid.ModelLayer.Enums.PlayerType
import com.huebelancer.rosterdroid.ModelLayer.Enums.Sorters
import com.huebelancer.rosterdroid.ModelLayer.Models.Droid
import com.huebelancer.rosterdroid.R
import java.util.*


class DroidPresenter(val fragment: DroidFragment, val droid: Droid) {

    fun getDroidIcon(): Int {
        return if (droid.isStarter) R.drawable.ic_droidstarterprofile else R.drawable.ic_droidprofile
    }

    fun getDroidStatus(): Int {
        return if (droid.isStarter) R.string.droid_starter else R.string.droid_substitute
    }

    fun getDroidReport(): String {
        when(droid.playerType()) {
            PlayerType.SPEED -> {
                fragment.activity.getString(R.string.speedy_writeup, droid.name)
            }
            PlayerType.STRENGTH -> {
                fragment.activity.getString(R.string.strong_writeup, droid.name)
            }
            PlayerType.AGILE -> {
                fragment.activity.getString(R.string.agile_writeup, droid.name)
            }
            PlayerType.BALANCE -> {
                fragment.activity.getString(R.string.balanced_writeup, droid.name)
            }
        }
        return ""
    }

    fun getDroidStrengths(): String {
        var strengths = ""

        //Start off with the strongest attribute, if any
        if (droid.biggestStrength() != null) {
            strengths = fragment.activity.getString(
                    R.string.strengths,
                    Helpers.attributeToString(droid.biggestStrength())
            ) + "\n"
        }

        val strs: LinkedHashSet<String> = LinkedHashSet()
        val rand = Random()

        while (strs.size < 3) { //Until I have 3 random strengths
            //Add a random strength
            strs.add(
                    Constants.STRENGTHS[rand.nextInt(Constants.STRENGTHS.size)]
            )
        }

        strs.forEach { str ->
            strengths += str
        }

        return strengths
    }

    fun getDroidWeaknesses(): String {
        var weaknesses = ""

        //Start off with the actual weakest attribute, if any
        if (droid.biggestWeakness() != null) {
            weaknesses = fragment.activity.getString(
                    R.string.weaknesses,
                    Helpers.attributeToString(droid.biggestWeakness())
            ) + "\n"
        }

        //Using a LinkedHashSet to exclude duplicates
        val wks: LinkedHashSet<String> = LinkedHashSet()
        val rand = Random()

        while (wks.size < 3) { //Until I have 3 random weaknesses...
            //Add a random weakness.
            wks.add(
                    Constants.WEAKNESSES[rand.nextInt(Constants.WEAKNESSES.size)]
            )
        }

        wks.forEach { wk ->
            weaknesses += wk
        }

        return weaknesses
    }

}