package com.feechanz.favoriteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.feechanz.favoriteapp.adapter.MovieListViewAdapter
import com.feechanz.favoriteapp.model.MovieShow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {


    private lateinit var adapter: MovieListViewAdapter

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initPresenter()
    }

    private fun initPresenter() {
        presenter = MainPresenter(this)

    }

    override fun onResume() {
        super.onResume()
        presenter.loadMovie(contentResolver)
    }

    private fun initRecyclerView() {
        adapter = MovieListViewAdapter()
        rvMovies.setHasFixedSize(true)
        rvMovies.layoutManager = LinearLayoutManager(this)
        rvMovies.adapter = adapter

        adapter.onMovieClick = object : MovieListViewAdapter.OnMovieClick {
            override fun onClick(movie: MovieShow) {
                selectMovie(movie)
            }
        }
    }

    override fun showMovies(movies: List<MovieShow>) {
        if(movies.size > 0) {
            adapter.addAll(movies)
        }else{
            Toast.makeText(this, "Daftar Favorite masih 0", Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectMovie(movie: MovieShow){
        Toast.makeText(this,
            "Movie: "+movie.title+ " diclick!",
            Toast.LENGTH_SHORT)
            .show()
    }
}
