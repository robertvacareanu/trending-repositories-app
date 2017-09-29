package com.vacareanu.robert.trendinggithub.ui.favorites

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vacareanu.robert.trendinggithub.R

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_favorites_rv, container, false)

        return view
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
