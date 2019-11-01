package com.feechanz.aplikasimoviecatalogue.presentation.movie

import com.feechanz.aplikasimoviecatalogue.data.MovieRepository

/**
 * Created by Feechan on 11/1/2019.
 */
class MoviePresenter(private val view: MovieContract.View,
                     private val repository: MovieRepository) : MovieContract.Presenter{
    override fun loadMovie() {
        val movies = repository.getMovies()
        view.showMovies(movies)
    }
}