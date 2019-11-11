package com.feechanz.aplikasimoviecatalogue.presentation.show


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.adapter.MovieListViewAdapter
import com.feechanz.aplikasimoviecatalogue.base.BaseFragment
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import com.feechanz.aplikasimoviecatalogue.presentation.detail.MovieDetailActivity

/**
 * A simple [Fragment] subclass.
 */
class ShowFragment : BaseFragment() {
    private lateinit var adapter: MovieListViewAdapter
    private lateinit var showViewModel: ShowViewModel

    private lateinit var rvShows: RecyclerView

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        searchView = (menu.findItem(R.id.search_view).actionView as SearchView?)
        searchView?.setOnQueryTextListener(object
            : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(pattern: String): Boolean {
                if (TextUtils.isEmpty(pattern)) {
                    queryAllShow()
                } else {
                    queryShows(pattern)
                }
                return true
            }
        })
    }

    private fun queryAllShow() {
        showLoadingBar()
        showViewModel.getShows(getString(R.string.language_code)).observe(this, Observer { shows ->
            hideLoadingBar()
            if (shows != null) {
                adapter.addAll(shows)
            }
        })
    }

    private fun queryShows(filter: String) {
        showLoadingBar()
        showViewModel.getShowsQuery(getString(R.string.language_code), filter).observe(this,
            Observer { shows ->
                hideLoadingBar()
                if (shows != null) {
                    adapter.addAll(shows)
                }
            })
    }

    override fun getContentView(): Int {
        return R.layout.fragment_show
    }

    override fun setup(view: View) {
        initView(view)
        initViewModel()
        initRecyclerView()
    }

    private fun initView(view: View) {
        rvShows = view.findViewById(R.id.rvShows)
        progressBar = view.findViewById(R.id.progressBar)
    }

    private fun initViewModel() {
        showLoadingBar()
        showViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(ShowViewModel::class.java)
        showViewModel.getShows(getString(R.string.language_code)).observe(this, Observer { shows ->
            hideLoadingBar()
            if (shows != null) {
                var pattern: String = ""
                if (searchView != null) {
                    pattern = searchView?.query.toString()
                }
                if (TextUtils.isEmpty(pattern)) {
                    adapter.addAll(shows)
                } else {
                    queryShows(pattern)
                }
            }
        })

        showViewModel.getErrorMessage().observe(this, Observer { errorMessage ->
            hideLoadingBar()
            if (errorMessage != null) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecyclerView() {
        adapter = MovieListViewAdapter()
        rvShows.setHasFixedSize(true)
        rvShows.layoutManager = LinearLayoutManager(context)
        rvShows.adapter = adapter

        adapter.onMovieClick = object : MovieListViewAdapter.OnMovieClick {
            override fun onClick(show: MovieShow) {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.MOVIE_KEY, show)
                startActivity(intent)
            }
        }
    }
}
