package com.vacareanu.robert.trendinggithub.ui.favorites

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vacareanu.robert.trendinggithub.R
import com.vacareanu.robert.trendinggithub.model.Repository


class FavoriteRVAdapter : RecyclerView.Adapter<FavoriteRVAdapter.FavoriteViewHolder>() {

    private var repositories: List<out Repository>? = null

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

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FavoriteViewHolder = FavoriteViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.view_favorite_item, parent, false))

    override fun onBindViewHolder(holder: FavoriteViewHolder?, position: Int) {
        holder?.url?.text = repositories!![position].url
    }

    override fun getItemCount(): Int = repositories?.size ?: 0

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var url: TextView

        init {
            url = itemView.findViewById(R.id.favorite_url)
        }
    }
}
