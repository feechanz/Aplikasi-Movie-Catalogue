package com.feechanz.aplikasimoviecatalogue.presentation.movie


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.adapter.MovieListViewAdapter
import com.feechanz.aplikasimoviecatalogue.data.MovieRepository
import com.feechanz.aplikasimoviecatalogue.model.Movie
import com.feechanz.aplikasimoviecatalogue.presentation.MovieDetailActivity

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment(), MovieContract.View{
    private lateinit var adapter: MovieListViewAdapter
    private lateinit var presenter: MovieContract.Presenter

    private lateinit var rvMovies: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie, container, false)
        setup(view)
        return view
    }

    private fun setup(view: View) {
        initView(view)
        initPresenter()
        initRecyclerView()
        initData()
    }

    private fun initView(view: View){
        rvMovies = view.findViewById(R.id.rvMovies)
    }

    private fun initPresenter(){
        presenter = MoviePresenter(this, MovieRepository(context))
    }

    private fun initRecyclerView(){
        adapter = MovieListViewAdapter()
        rvMovies.setHasFixedSize(true)
        rvMovies.layoutManager = LinearLayoutManager(context)
        rvMovies.adapter = adapter

        adapter.onMovieClick = object : MovieListViewAdapter.OnMovieClick {
            override fun onClick(movie: Movie) {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.MOVIE_KEY, movie)
                startActivity(intent)
            }
        }
    }

    fun initData(){
        presenter.loadMovie()
    }

    override fun showMovies(movies: ArrayList<Movie>) {
        adapter.addAll(movies)
    }
}
