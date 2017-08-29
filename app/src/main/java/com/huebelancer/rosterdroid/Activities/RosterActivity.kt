package com.huebelancer.rosterdroid.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.huebelancer.rosterdroid.Activities.RosterList.RosterFragment
import com.huebelancer.rosterdroid.Helpers.Helpers
import com.huebelancer.rosterdroid.R

class RosterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roster)

        var fragment: RosterFragment? =
                supportFragmentManager
                    .findFragmentById(R.id.content)
                        as RosterFragment?

        if (fragment == null) {
            fragment = RosterFragment.newInstance()

            Helpers.addFragmentToActivity(
                    supportFragmentManager,
                    fragment,
                    R.id.content
            )
        }
    }
}
