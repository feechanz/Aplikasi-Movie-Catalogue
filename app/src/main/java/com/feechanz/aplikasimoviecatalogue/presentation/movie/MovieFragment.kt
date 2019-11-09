package com.feechanz.aplikasimoviecatalogue.presentation.movie


import android.content.Intent
import android.view.View
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
class MovieFragment : BaseFragment() {
    private lateinit var adapter: MovieListViewAdapter
    private lateinit var movieViewModel: MovieViewModel

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
        movieViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MovieViewModel::class.java)
        movieViewModel.getMovies(getString(R.string.language_code))
            .observe(this, Observer { movies ->
                hideLoadingBar()
                if (movies != null) {
                    adapter.addAll(movies)
                }
            })
        movieViewModel.getErrorMessage().observe(this, Observer { errorMessage ->
            hideLoadingBar()
            if (errorMessage != null) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
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
