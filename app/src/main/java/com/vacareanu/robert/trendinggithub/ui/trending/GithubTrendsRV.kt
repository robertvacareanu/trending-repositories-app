package com.vacareanu.robert.trendinggithub.ui.repositories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vacareanu.robert.trendinggithub.R
import com.vacareanu.robert.trendinggithub.makeToast
import com.vacareanu.robert.trendinggithub.model.Repository


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [GithubTrendsRV.OnGithubTrendsRVInteractionListener] interface
 * to handle interaction events.
 * Use the [GithubTrendsRV.newInstance] factory method to
 * create an instance of this fragment.
 */
class GithubTrendsRV : Fragment() {

    private var callback: OnGithubTrendsRVInteractionListener? = null

    private lateinit var adapter: GithubTrendsRVAdapter
    private lateinit var repositoriesRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_github_trends_rv, container, false)
        repositoriesRV = view.findViewById(R.id.repositories_rv)
        adapter = GithubTrendsRVAdapter()
        repositoriesRV.layoutManager = LinearLayoutManager(activity)
        repositoriesRV.adapter = adapter
        val itemCall = object : RepositoryViewClickListener.ItemClickCallback {
            override fun onItemClick(clickedView: View, position: Int) {
                Log.v("GTRV", "Clicked")
            }

            override fun onHeartClick(heart: View, position: Int) {
                Log.v("GTRV",
                        "Heart clicked of ${ViewModelProviders.of(activity).get(RepositoryViewModel::class.java).repositories.value!![position].name}"
                )
            }
        }
        repositoriesRV.addOnItemTouchListener(RepositoryViewClickListener(itemCall, activity))


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: RepositoryViewModel = ViewModelProviders.of(activity).get(RepositoryViewModel::class.java)
        viewModel.repositories.observe(this, Observer<List<Repository>> { t: List<Repository>? ->
                if (t == null) {

                } else {
                    adapter.setRepositories(t)
                }
        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnGithubTrendsRVInteractionListener) {
            callback = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnGithubTrendsRVInteractionListener")
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
    interface OnGithubTrendsRVInteractionListener

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment GithubTrendsRV.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): GithubTrendsRV {
            val fragment = GithubTrendsRV()
            return fragment
        }
    }
}// Required empty public constructor