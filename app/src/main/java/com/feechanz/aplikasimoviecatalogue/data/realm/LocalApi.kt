package com.feechanz.aplikasimoviecatalogue.data.realm

import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow

/**
 * Created by Feechan on 11/9/2019.
 */
interface LocalApi {
    fun getMovieFavorites(): List<MovieShow>
    fun getTvShowFavorites(): List<MovieShow>
    fun insertMovieShowFavorite(movie: MovieShow): Boolean
    fun getMovieShowFavorite(movieId: Long, isMovie: Boolean): MovieShow?
    fun removeMovieShowFavorite(movieId: Long, isMovie: Boolean): Boolean
}