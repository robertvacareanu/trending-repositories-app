package com.vacareanu.robert.trendinggithub.ui.favorites

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.vacareanu.robert.trendinggithub.FavoritesViewModelFactory
import com.vacareanu.robert.trendinggithub.R
import com.vacareanu.robert.trendinggithub.makeToast
import com.vacareanu.robert.trendinggithub.model.Repository
import com.vacareanu.robert.trendinggithub.ui.repositories.GithubTrendsRV
import com.vacareanu.robert.trendinggithub.ui.repositories.GithubTrendsRVAdapter
import com.vacareanu.robert.trendinggithub.ui.repositories.RepositoryViewClickListener

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FavoritesRV.OnFavoritesInteractionListener] interface
 * to handle interaction events.
 * Use the [FavoritesRV.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesRV : Fragment(), FavoriteView {


    private var callback: GithubTrendsRV.OnGithubTrendsRVInteractionListener? = null
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var adapter: GithubTrendsRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites_rv, container, false)
        viewModel = ViewModelProviders.of(this, FavoritesViewModelFactory(activity.applicationContext)).get(FavoritesViewModel::class.java)


        val rv: RecyclerView = view.findViewById(R.id.favorites_rv)
        rv.layoutManager = LinearLayoutManager(context)
        adapter = GithubTrendsRVAdapter(context)

        rv.adapter = adapter
        val itemCall = object : RepositoryViewClickListener.ItemClickCallback {
            override fun onItemClick(position: Int) {
                activity.makeToast("Item clicked")
                viewModel.handleRepoClick(position, this@FavoritesRV)
            }

            override fun onHeartClick(heart: ImageView, position: Int) {
                //Some animation here. Fill/Unfill empty_heart.
                activity.makeToast("Heart clicked")

                val emptyHeartAnimation = AnimatedVectorDrawableCompat.create(context, R.drawable.heart_full_to_empty)
                heart.setImageDrawable(emptyHeartAnimation)
                emptyHeartAnimation?.start()
//                callback?.handleHeartClick(viewModel.repositories.value!![position])
                viewModel.handleHeartClick(position, callback!!)
            }
        }

        rv.addOnItemTouchListener(RepositoryViewClickListener(itemCall, activity))

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
        if (context is GithubTrendsRV.OnGithubTrendsRVInteractionListener) {
            callback = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFavoritesInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun launchPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
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
