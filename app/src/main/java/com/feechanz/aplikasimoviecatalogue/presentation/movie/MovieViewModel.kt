package com.feechanz.aplikasimoviecatalogue.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import com.feechanz.aplikasimoviecatalogue.data.network.retrofit.RestApi
import com.feechanz.aplikasimoviecatalogue.data.network.retrofit.RestApiImpl
import com.feechanz.aplikasimoviecatalogue.utils.Constant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Feechan on 11/1/2019.
 */
class MovieViewModel : ViewModel(){
    private var movies: MutableLiveData<List<MovieShow>>? = null
    private var restApi: RestApi = RestApiImpl()

    fun getMovies(languageCode: String): LiveData<List<MovieShow>> {
        if(movies == null){
            movies = MutableLiveData()
            loadMovies(languageCode)
        }
        return movies as MutableLiveData<List<MovieShow>>
    }

    private fun loadMovies(languageCode: String){
        val moviesResult = arrayListOf<MovieShow>()

        CoroutineScope(Dispatchers.IO).launch {
            val movieResponses = restApi.getMovies(
                Constant.getEndpointPath(Constant.GET_MOVIES),
                Constant.API_KEY,
                languageCode
            )
            if (movieResponses.isSuccessful) {
                movieResponses.body().results?.map { m ->
                    moviesResult.add(MovieShow.getInstance(m))
                }
                movies?.postValue(moviesResult)
            }
        }

    }
}