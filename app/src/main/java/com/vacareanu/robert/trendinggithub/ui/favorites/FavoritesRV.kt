package com.vacareanu.robert.trendinggithub.ui.favorites

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vacareanu.robert.trendinggithub.FavoritesViewModelFactory

import com.vacareanu.robert.trendinggithub.R
import com.vacareanu.robert.trendinggithub.model.Repository
import kotlinx.android.synthetic.main.fragment_favorites_rv.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FavoritesRV.OnFavoritesInteractionListener] interface
 * to handle interaction events.
 * Use the [FavoritesRV.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesRV : Fragment() {


    private var callback: OnFavoritesInteractionListener? = null
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var adapter: FavoriteRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites_rv, container, false)
        viewModel = ViewModelProviders.of(this, FavoritesViewModelFactory(activity.applicationContext)).get(FavoritesViewModel::class.java)


        val rv: RecyclerView = view.findViewById(R.id.favorites_rv)
        rv.layoutManager = LinearLayoutManager(context)
        adapter = FavoriteRVAdapter()
        rv.adapter = adapter


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.repositories.observe(this, Observer<List<Repository>> { t: List<Repository>? ->
            if (t == null) {

            } else {
                adapter.setRepositories(t)
            }
        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFavoritesInteractionListener) {
            callback = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFavoritesInteractionListener")
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
    interface OnFavoritesInteractionListener

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment FavoritesRV.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): FavoritesRV {
            val fragment = FavoritesRV()
            return fragment
        }
    }
}// Required empty public constructor
