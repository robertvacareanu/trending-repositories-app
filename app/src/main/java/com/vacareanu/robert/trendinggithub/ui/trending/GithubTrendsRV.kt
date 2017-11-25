package com.vacareanu.robert.trendinggithub.ui.repositories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
    private lateinit var viewModel: TrendsViewModel
//    private var emptyHeartAnimation: AnimatedVectorDrawableCompat? = null
//    private var fillHeartAnimation: AnimatedVectorDrawableCompat? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_github_trends_rv, container, false)
        repositoriesRV = view.findViewById(R.id.repositories_rv)
        adapter = GithubTrendsRVAdapter(context.applicationContext)
        repositoriesRV.layoutManager = LinearLayoutManager(activity)
        repositoriesRV.adapter = adapter
        val itemCall = object : RepositoryViewClickListener.ItemClickCallback {
            override fun onItemClick(position: Int) {
                activity.makeToast("Item clicked")
                callback?.handleRepoClick(viewModel.repositories.value!![position])
            }

            override fun onHeartClick(heart: ImageView, position: Int) {
                //Some animation here. Fill/Unfill empty_heart.
                activity.makeToast("Heart clicked")

                val isFav = viewModel.repositories.value!![position].isFavorite
                if (isFav) {
                    val emptyHeartAnimation = AnimatedVectorDrawableCompat.create(context, R.drawable.heart_full_to_empty)
                    heart.setImageDrawable(emptyHeartAnimation)
                    emptyHeartAnimation?.start()
                } else {
                    val fillHeartAnimation = AnimatedVectorDrawableCompat.create(context, R.drawable.heart_empty_to_full)
                    heart.setImageDrawable(fillHeartAnimation)
                    fillHeartAnimation?.start()
                }
                callback?.handleHeartClick(viewModel.repositories.value!![position])
            }
        }

//        fillHeartAnimation = AnimatedVectorDrawableCompat.create(context, R.drawable.heart_empty_to_full)
//        emptyHeartAnimation = AnimatedVectorDrawableCompat.create(context, R.drawable.heart_full_to_empty)

        repositoriesRV.addOnItemTouchListener(RepositoryViewClickListener(itemCall, activity))


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity).get(TrendsViewModel::class.java)
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
    interface OnGithubTrendsRVInteractionListener {
        fun handleRepoClick(repo: Repository)
        fun handleHeartClick(repo: Repository)
    }

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
