package com.vacareanu.robert.trendinggithub.ui.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vacareanu.robert.trendinggithub.R
import com.vacareanu.robert.trendinggithub.model.Repository

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DetailsFragment.OnDetailsInteractionListener] interface
 * to handle interaction events.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

    private var callback: OnDetailsInteractionListener? = null
    private lateinit var viewModel: DetailsViewModel

    lateinit var url: TextView
    lateinit var name: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_details, container, false)

        url = view.findViewById(R.id.tv_url)
        name = view.findViewById(R.id.tv_name)


        savedInstanceState?.let { viewModel = ViewModelProviders.of(activity).get(DetailsViewModel::class.java) }

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel.repo.observe(this, Observer<Repository> { t: Repository? ->
//            t?.let {
//                url.text = t.url
//                name.text = t.name
//            }
//        })
        url.text = viewModel.repo.url
        name.text = viewModel.repo.name


    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnDetailsInteractionListener) {
            callback = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnDetailsInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnDetailsInteractionListener

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment DetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(viewModel: DetailsViewModel): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.viewModel = viewModel
            return fragment
        }
    }
}// Required empty public constructor
