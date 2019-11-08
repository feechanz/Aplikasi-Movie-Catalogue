package com.feechanz.aplikasimoviecatalogue.presentation.show


import android.content.Intent
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.adapter.MovieListViewAdapter
import com.feechanz.aplikasimoviecatalogue.base.BaseFragment
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import com.feechanz.aplikasimoviecatalogue.presentation.MovieDetailActivity

/**
 * A simple [Fragment] subclass.
 */
class ShowFragment : BaseFragment() {
    private lateinit var adapter: MovieListViewAdapter
    private lateinit var showViewModel: ShowViewModel

    private lateinit var rvShows: RecyclerView

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
        showViewModel.getMovies(getString(R.string.language_code)).observe(this, Observer { shows ->
            if (shows != null) {
                adapter.addAll(shows)
                hideLoadingBar()
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
