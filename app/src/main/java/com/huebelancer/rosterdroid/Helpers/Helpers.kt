package com.huebelancer.rosterdroid.Helpers

import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.transition.Fade
import android.transition.Slide
import com.huebelancer.rosterdroid.ModelLayer.Enums.Attributes
import java.util.*
import kotlin.collections.LinkedHashMap


class Helpers {
    companion object {

        //region Fragment Helpers

        fun addFragmentToActivity(manager: FragmentManager, fragment: Fragment, frameId: Int) {
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.add(frameId, fragment)
            transaction.commit()
        }

        fun replaceFragmentInActivity(manager: FragmentManager, fragment: Fragment, frameId: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fragment.enterTransition = Slide()
                fragment.exitTransition = Slide()
            } else {
                fragment.enterTransition = Fade()
                fragment.exitTransition = Fade()
            }


            fragment.sharedElementEnterTransition = DetailViewTransition()

            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.replace(frameId, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        //endregion

        //region Roster Helpers

        fun getPointValues(): LinkedHashMap<String, Int> {
            //Calculate the sum of the positions for both starters and subs
            val substitutes_baseScore = baseScoreOf(Constants.SUB_COUNT)
            val starter_baseScore     = baseScoreOf(teamCount()) - substitutes_baseScore

            /*
            * Determine how many points are left over inside the cap
            * These points will be spread across starters and subs
            * so that we use all of the cap
            */
            val variable_points = Constants.SALARY_CAP - (substitutes_baseScore + starter_baseScore)
            var points_for_subs = 0

            when {
                variable_points % Constants.STARTER_COUNT == 0 -> {
                    points_for_subs = 4
                }
                variable_points % Constants.SUB_COUNT == 0 -> {
                    points_for_subs = 3
                }
                (Math.round(variable_points / 10.0) * 10) % 10 == 0L -> { //variable_points ended in a number between 5 and 10
                    points_for_subs = 3
                }
            }

            /*
            * If points_for_subs is 0 at this point, variable_points
            * must have ended in a number between 0 and 5
            */
            if (points_for_subs == 0){
                points_for_subs = 4
            }

            /*
            * If the cap were to go lower than 175, this line
            * corrects the balance so that starters still receive a
            * higher proportion of the points
            */
            if (variable_points < 55) {
                points_for_subs -= 2
            }

            val points_for_starters = (variable_points - (points_for_subs * Constants.SUB_COUNT)) / 10

            val map: LinkedHashMap<String, Int> = LinkedHashMap()
            map.put(Constants.STARTER_KEY, points_for_starters)
            map.put(Constants.SUB_KEY, points_for_subs)

            return map
        }

        fun allocatedCapRoom(player: Int, remainingCap: Int, pointValues: LinkedHashMap<String, Int>): Int {

            val points_for_starters = pointValues[Constants.STARTER_KEY]!!
            val points_for_subs = pointValues[Constants.SUB_KEY]!!

            return when {
                player > Constants.SUB_COUNT ->
                    player + points_for_starters
                player == teamCount() ->
                    //If salary cap isn't divisible by 5, the last starter created
                    //will be the best player. He will consume the rest of the cap
                    remainingCap
                else ->
                    player + points_for_subs
            }
        }

        fun attributeToString(attr: Attributes?): String {
            if (attr != null) {
                return when (attr) {
                    Attributes.SPEED -> {
                        Constants.ATTR_SPEED
                    }
                    Attributes.AGILITY -> {
                        Constants.ATTR_AGILITY
                    }
                    Attributes.STRENGTH -> {
                        Constants.ATTR_STRENGTH
                    }
                }
            }

            return ""
        }

        private fun baseScoreOf(number: Int): Int {
            return (1..number).sum()
        }

        private fun teamCount():Int {
            return Constants.STARTER_COUNT + Constants.SUB_COUNT
        }



        //endregion
    }
}