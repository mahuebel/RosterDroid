package com.huebelancer.rosterdroid.Activities.DroidDetails


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.huebelancer.rosterdroid.Coordinators.Coordinator
import com.huebelancer.rosterdroid.Dependencies.DependencyRegistry
import com.huebelancer.rosterdroid.ModelLayer.Enums.PlayerType
import com.huebelancer.rosterdroid.ModelLayer.Enums.Sorters
import com.huebelancer.rosterdroid.ModelLayer.Models.Droid

import com.huebelancer.rosterdroid.R


class DroidFragment : Fragment() {

    private var droid: Droid?              = null
    private var presenter: DroidPresenter? = null
    private var coordinator: Coordinator?  = null

    var icon: ImageView?             = null
    var name: TextView?              = null
    var status: TextView?            = null
    var agility: TextView?           = null
    var strength: TextView?          = null
    var speed: TextView?             = null
    var total: TextView?             = null
    var writeup: TextView?           = null
    var writeupStrengths: TextView?  = null
    var writeupWeaknesses: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            droid = arguments.getSerializable(ARG_DROID) as Droid
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_droid, container, false)

        attachUI(view)

        if (droid != null) {
            DependencyRegistry.shared.inject(this, droid!!)
            loadUIData(droid!!)
        }

        return view
    }

    private fun loadUIData(d: Droid) {
        name?.text     = d.name
        strength?.text = d.strength.toString()
        speed?.text    = d.speed.toString()
        agility?.text  = d.agility.toString()
        total?.text    = d.totalAttributes().toString()

        if (presenter != null) {
            icon?.setImageResource(presenter!!.getDroidIcon())
            status?.text = getText(presenter!!.getDroidStatus())

            writeup?.text           = presenter!!.getDroidReport()
            writeupStrengths?.text  = presenter!!.getDroidStrengths()
            writeupWeaknesses?.text = presenter!!.getDroidWeaknesses()
        }
    }

    fun attachUI(view: View?) {
        if (view != null) {
            icon              = view.findViewById(R.id.icon)
            name              = view.findViewById(R.id.name)
            status            = view.findViewById(R.id.status)
            strength          = view.findViewById(R.id.droid_strength)
            agility           = view.findViewById(R.id.droid_agility)
            speed             = view.findViewById(R.id.droid_speed)
            total             = view.findViewById(R.id.total)
            writeup           = view.findViewById(R.id.writeup)
            writeupStrengths  = view.findViewById(R.id.writeupStrengths)
            writeupWeaknesses = view.findViewById(R.id.writeupWeaknesses)
        }
    }


    companion object {

        private val ARG_DROID = "droid"

        fun newInstance(droid: Droid): DroidFragment {
            val fragment = DroidFragment()
            val args = Bundle()
            args.putSerializable(ARG_DROID, droid)
            fragment.arguments = args
            return fragment
        }
    }

    fun configureWith(presenter: DroidPresenter, coordinator: Coordinator) {
        this.presenter   = presenter
        this.coordinator = coordinator
    }

}
