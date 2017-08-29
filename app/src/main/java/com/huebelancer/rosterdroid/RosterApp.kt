package com.huebelancer.rosterdroid

import android.app.Application
import com.huebelancer.rosterdroid.Dependencies.DependencyRegistry

class RosterApp : Application() {

    var registry: DependencyRegistry? = null

    override fun onCreate() {
        super.onCreate()

        registry = DependencyRegistry.shared
    }
}