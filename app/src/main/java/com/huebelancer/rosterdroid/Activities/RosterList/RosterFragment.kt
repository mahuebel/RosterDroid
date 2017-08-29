package com.huebelancer.rosterdroid.Activities.RosterList


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import com.huebelancer.rosterdroid.Activities.RosterActivity
import com.huebelancer.rosterdroid.Coordinators.Coordinator
import com.huebelancer.rosterdroid.Dependencies.DependencyRegistry
import com.huebelancer.rosterdroid.Helpers.CustomItemClickListener
import com.huebelancer.rosterdroid.ModelLayer.Enums.Sorters
import com.huebelancer.rosterdroid.ModelLayer.ModelLayer
import com.huebelancer.rosterdroid.ModelLayer.Models.Droid

import com.huebelancer.rosterdroid.R


class RosterFragment : Fragment(), CustomItemClickListener, ModelLayer.LoadDroidsCallback {

    private var recyclerView: RecyclerView? = null
    private var presenter: RosterPresenter? = null
    private var coordinator: Coordinator? = null
    private var droids: MutableList<Droid> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View? = inflater!!.inflate(R.layout.fragment_roster, container, false)

        attachUI(view)

        DependencyRegistry.shared.inject(this)

        Log.d("RosterFragment", "onCreate")

        return view
    }

    private fun attachUI(view: View?) {
        val manager = LinearLayoutManager(activity)

        recyclerView = view?.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = manager
        recyclerView?.setHasFixedSize(true)

        initializeListView()
    }

    private fun initializeListView() {
        val adapter: RosterViewAdapter = RosterViewAdapter(droids, this)
        recyclerView?.adapter = adapter
    }

    companion object {
        fun newInstance(): RosterFragment {
            val fragment = RosterFragment()
            return fragment
        }
    }

    override fun onDroidsLoaded(droids: MutableList<Droid>, isResort: Boolean) {
        if (this.droids.size == 0 || isResort) {
            this.droids = droids

            Log.d("RosterFragment", "onDroidsLoaded " + droids.size)
            val adapter = recyclerView?.adapter as RosterViewAdapter
            adapter.droids = this.droids
            adapter.notifyDataSetChanged()
            Log.d("RosterViewAdapter", adapter.toString() + "  droids: " + adapter.itemCount)
        }
    }

    fun configureWith(presenter: RosterPresenter, coordinator: Coordinator) {
        this.presenter = presenter
        this.coordinator = coordinator

        loadDroids()
    }

    private fun loadDroids() {
        presenter!!.loadDroids()
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        if (menu != null)
            inflater?.inflate(R.menu.menu_roster, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var sortBy: Sorters? = null

        when(item?.itemId) {
            R.id.menu_ovr -> {
                sortBy = Sorters.OVR
            }
            R.id.menu_agi -> {
                sortBy = Sorters.AGI
            }
            R.id.menu_spd -> {
                sortBy = Sorters.SPD
            }
            R.id.menu_str -> {
                sortBy = Sorters.STR
            }
        }

        if (sortBy != null)
            presenter?.sort(sortBy, droids)

        return false
    }



    private fun goToDroidDetails(droid: Droid?) {
        if (droid != null) {
            coordinator?.handleDroidClick(
                    activity as RosterActivity,
                    droid,
                    R.id.content
            )
        }
    }


    override fun onItemClick(v: View, position: Int) {
        val droid = droids[position]
        goToDroidDetails(droid)
    }

}
