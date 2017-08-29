package com.huebelancer.rosterdroid.Coordinators

import com.huebelancer.rosterdroid.Activities.DroidDetails.DroidFragment
import com.huebelancer.rosterdroid.Activities.RosterActivity
import com.huebelancer.rosterdroid.Helpers.Helpers
import com.huebelancer.rosterdroid.ModelLayer.Models.Droid


class Coordinator {
    fun handleDroidClick(activity: RosterActivity, droid: Droid, content: Int) {
        val fragment: DroidFragment = DroidFragment.newInstance(droid)
        Helpers.replaceFragmentInActivity(
                activity.supportFragmentManager,
                fragment,
                content
        )
    }

}