package com.huebelancer.rosterdroid.Helpers

import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.transition.Fade
import android.transition.Slide
import com.huebelancer.rosterdroid.ModelLayer.Enums.Attributes
import java.util.*


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

        fun allocatedCapRoom(remainingCap: Int, remainingDroids: Int): Int {
            val average: Int = remainingCap / remainingDroids

            if (remainingDroids > 5) {
                val rand: Random = Random()

                val starterAverage = (remainingCap - 25) / (remainingDroids - 5)
                val min = starterAverage - 3
                val max = starterAverage + 3

                return rand.nextInt(max - min) + min

            } else if (remainingDroids == 1) {
                return remainingCap

            } else {

                return average
            }
        }

        fun attributeToString(attr: Attributes?): String {
            if (attr != null) {
                when(attr) {
                    Attributes.SPEED -> {
                        return Constants.ATTR_SPEED
                    }
                    Attributes.AGILITY -> {
                        return Constants.ATTR_AGILITY
                    }
                    Attributes.STRENGTH -> {
                        return Constants.ATTR_STRENGTH
                    }
                }
            }

            return ""
        }

        //endregion
    }
}