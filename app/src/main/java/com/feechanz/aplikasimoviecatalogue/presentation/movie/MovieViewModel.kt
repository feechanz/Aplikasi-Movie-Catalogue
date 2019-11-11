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
class MovieViewModel : ViewModel() {
    private var movies: MutableLiveData<List<MovieShow>>? = null
    private var moviesQuery: MutableLiveData<List<MovieShow>>? = null
    private var errorMessage: MutableLiveData<String>? = null
    private var restApi: RestApi = RestApiImpl()
    private var moviesFiltered: MutableLiveData<List<MovieShow>>? = null

    fun getMovies(languageCode: String): LiveData<List<MovieShow>> {

        movies = MutableLiveData()
        loadMovies(languageCode)

        return movies as MutableLiveData<List<MovieShow>>
    }

    fun getMoviesQuery(languageCode: String, query: String): LiveData<List<MovieShow>> {

        moviesQuery = MutableLiveData()
        loadMoviesQuery(languageCode, query)

        return moviesQuery as MutableLiveData<List<MovieShow>>
    }

    fun getErrorMessage(): LiveData<String> {
        if (errorMessage == null) {
            errorMessage = MutableLiveData()
        }
        return errorMessage as MutableLiveData<String>
    }

    fun getMoviesFiltered(pattern: String): LiveData<List<MovieShow>> {

        moviesFiltered = MutableLiveData()
        loadMoviesFiltered(pattern)

        return moviesFiltered as MutableLiveData<List<MovieShow>>
    }

    private fun loadMovies(languageCode: String) {
        errorMessage = null
        val moviesResult = arrayListOf<MovieShow>()

        CoroutineScope(Dispatchers.IO).launch {
            try {
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
            } catch (ex: Exception) {
                errorMessage?.postValue(ex.message)
            }
        }
    }

    private fun loadMoviesQuery(languageCode: String, query: String) {
        errorMessage = null
        val moviesResult = arrayListOf<MovieShow>()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val showResponse = restApi.getMoviesSearch(
                    Constant.getEndpointPath(Constant.GET_MOVIES_SEARCH),
                    Constant.API_KEY,
                    languageCode,
                    query
                )
                if (showResponse.isSuccessful) {
                    showResponse.body().results?.map { m ->
                        moviesResult.add(MovieShow.getInstance(m))
                    }
                    moviesQuery?.postValue(moviesResult)
                }
            } catch (ex: Exception) {
                errorMessage?.postValue(ex.message)
            }
        }
    }

    private fun loadMoviesFiltered(pattern: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = ArrayList<MovieShow>()
            movies?.value?.map { m ->
                if (m.title!!.toLowerCase().contains(pattern.toLowerCase()))
                    result.add(m)
            }
            moviesFiltered?.postValue(result)
        }
    }
}