package com.vacareanu.robert.trendinggithub.ui

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.vacareanu.robert.trendinggithub.BaseViewModel
import com.vacareanu.robert.trendinggithub.MainActivityViewModelFactory
import com.vacareanu.robert.trendinggithub.R
import com.vacareanu.robert.trendinggithub.makeToast
import com.vacareanu.robert.trendinggithub.model.Repository
import com.vacareanu.robert.trendinggithub.ui.details.DetailsFragment
import com.vacareanu.robert.trendinggithub.ui.details.DetailsViewModel
import com.vacareanu.robert.trendinggithub.ui.favorites.FavoritesRV
import com.vacareanu.robert.trendinggithub.ui.repositories.GithubTrendsRV
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, GithubTrendsRV.OnGithubTrendsRVInteractionListener, FavoritesRV.OnFavoritesInteractionListener, DetailsFragment.OnDetailsInteractionListener {


    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = viewModelWithFactory(MainActivityViewModelFactory(applicationContext))

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        if (savedInstanceState == null) {
            nav_view.setCheckedItem(R.id.nav_trending)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, GithubTrendsRV.newInstance()).commit()
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_favorites -> {
                // Handle the camera action
                makeToast("Favorite")
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FavoritesRV.newInstance()).addToBackStack(null).commit()
            }
            R.id.nav_trending -> {
                makeToast("Trending")
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, GithubTrendsRV.newInstance()).addToBackStack(null).commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    inline fun <reified T : BaseViewModel> viewModel(): T = ViewModelProviders.of(this).get(T::class.java)


    inline fun <reified T : BaseViewModel> viewModelWithFactory(factory: ViewModelProvider.Factory): T = ViewModelProviders.of(this, factory).get(T::class.java)


    override fun handleRepoClick(repo: Repository) {
        val detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        detailsViewModel.init(repo)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DetailsFragment.newInstance(detailsViewModel)).addToBackStack(detailsViewModel.tag).commit()
    }

    override fun handleHeartClick(repo: Repository) {
        if (repo.isFavorite) {
            repo.isFavorite = false
            viewModel.delete(repo)
        } else {
            repo.isFavorite = true
            viewModel.save(repo)
        }

    }
}
