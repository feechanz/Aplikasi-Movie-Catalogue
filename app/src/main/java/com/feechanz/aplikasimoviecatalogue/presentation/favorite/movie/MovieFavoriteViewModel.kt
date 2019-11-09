package com.feechanz.aplikasimoviecatalogue.presentation.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import com.feechanz.aplikasimoviecatalogue.data.realm.LocalApi
import com.feechanz.aplikasimoviecatalogue.data.realm.RealmDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Feechan on 11/9/2019.
 */
class MovieFavoriteViewModel : ViewModel() {
    private var movies: MutableLiveData<List<MovieShow>>? = null
    private var localApi: LocalApi = RealmDataSource()

    fun getMovies(): LiveData<List<MovieShow>> {
        if (movies == null) {
            movies = MutableLiveData()
            loadMovies()
        }
        return movies as MutableLiveData<List<MovieShow>>
    }

    private fun loadMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            movies?.postValue(localApi.getMovieFavorites())
        }
    }
}