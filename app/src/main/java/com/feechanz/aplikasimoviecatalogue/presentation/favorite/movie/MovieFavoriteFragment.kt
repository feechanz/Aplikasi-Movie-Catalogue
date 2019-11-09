package com.feechanz.aplikasimoviecatalogue.presentation.favorite.movie

import android.content.Intent
import android.view.View
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
 * Created by Feechan on 11/9/2019.
 */
class MovieFavoriteFragment : BaseFragment() {
    private lateinit var adapter: MovieListViewAdapter
    private lateinit var movieFavoriteViewModel: MovieFavoriteViewModel
    private lateinit var rvMovies: RecyclerView

    override fun getContentView(): Int {
        return R.layout.fragment_movie
    }

    override fun setup(view: View) {
        initView(view)
        initViewModel()
        initRecyclerView()
    }

    private fun initView(view: View) {
        rvMovies = view.findViewById(R.id.rvMovies)
        progressBar = view.findViewById(R.id.progressBar)
    }

    private fun initViewModel() {
        showLoadingBar()
        movieFavoriteViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MovieFavoriteViewModel::class.java)
        movieFavoriteViewModel.getMovies()
            .observe(this, Observer { movies ->
                hideLoadingBar()
                if (movies != null) {
                    adapter.addAll(movies)
                }
            })
    }

    private fun initRecyclerView() {
        adapter = MovieListViewAdapter()
        rvMovies.setHasFixedSize(true)
        rvMovies.layoutManager = LinearLayoutManager(context)
        rvMovies.adapter = adapter

        adapter.onMovieClick = object : MovieListViewAdapter.OnMovieClick {
            override fun onClick(movie: MovieShow) {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.MOVIE_KEY, movie)
                startActivity(intent)
            }
        }
    }
}