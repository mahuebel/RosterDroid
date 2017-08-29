package com.huebelancer.rosterdroid.Dependencies

import com.huebelancer.rosterdroid.Activities.DroidDetails.DroidFragment
import com.huebelancer.rosterdroid.Activities.DroidDetails.DroidPresenter
import com.huebelancer.rosterdroid.Activities.RosterList.RosterFragment
import com.huebelancer.rosterdroid.Activities.RosterList.RosterPresenter
import com.huebelancer.rosterdroid.Coordinators.Coordinator
import com.huebelancer.rosterdroid.ModelLayer.ModelLayer
import com.huebelancer.rosterdroid.ModelLayer.Models.Droid


class DependencyRegistry {
    companion object {
        val shared: DependencyRegistry = DependencyRegistry()
    }


    val coordinator = Coordinator()

    val modelLayer: ModelLayer = createModelLayer()
    private fun  createModelLayer(): ModelLayer {
        return ModelLayer()
    }

    fun inject(fragment: RosterFragment) {
        val presenter: RosterPresenter = RosterPresenter(modelLayer, fragment)
        fragment.configureWith(presenter, coordinator)
    }

    fun inject(fragment: DroidFragment, droid: Droid) {
        val presenter: DroidPresenter = DroidPresenter(fragment, droid)
        fragment.configureWith(presenter, coordinator)
    }
}