package com.vacareanu.robert.trendinggithub.ui.repositories

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vacareanu.robert.trendinggithub.R
import com.vacareanu.robert.trendinggithub.model.Repository

/**
 * Created by robert on 9/27/17.
 */
class GithubTrendsRVAdapter : RecyclerView.Adapter<GithubTrendsRVAdapter.GithubTrendsViewHolder>() {


    private var repositories: List<out Repository>? = null

    class GithubTrendsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var stars: TextView
        var title: TextView
        var description: TextView
        var heart: ImageView

        init {
            stars = view.findViewById(R.id.stars)
            title = view.findViewById(R.id.repo_title)
            description = view.findViewById(R.id.repo_description)
            heart = view.findViewById(R.id.heart)
        }

    }

    fun setRepositories(repo: List<out Repository>) {
        if (repositories == null) {
            repositories = repo
            notifyItemRangeChanged(0, repo.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = repositories!![oldItemPosition] == repo[newItemPosition]

                override fun getOldListSize(): Int = repositories!!.size

                override fun getNewListSize(): Int = repo.size

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = repositories!![newItemPosition] == repositories!![oldItemPosition]
            })
            repositories = repo
            result.dispatchUpdatesTo(this)
        }
    }


    override fun onBindViewHolder(holder: GithubTrendsViewHolder?, position: Int) {

        val repo = repositories!![position]
        holder?.let {
            it.title.text = repo.name
            it.description.text = repo.description
            it.stars.text = repo.stars.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GithubTrendsViewHolder = GithubTrendsViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.view_repository_item, parent, false))

    override fun getItemCount(): Int = repositories?.size ?: 0
}