package com.feechanz.aplikasimoviecatalogue.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShowDetail
import com.feechanz.aplikasimoviecatalogue.data.network.retrofit.RestApi
import com.feechanz.aplikasimoviecatalogue.data.network.retrofit.RestApiImpl
import com.feechanz.aplikasimoviecatalogue.data.realm.LocalApi
import com.feechanz.aplikasimoviecatalogue.data.realm.RealmDataSource
import com.feechanz.aplikasimoviecatalogue.utils.Constant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Feechan on 11/9/2019.
 */
class MovieDetailViewModel : ViewModel() {
    private var movieDetail: MutableLiveData<MovieShowDetail>? = null
    private var errorMessage: MutableLiveData<String>? = null
    private var isFavorite: MutableLiveData<Boolean>? = null
    private var restApi: RestApi = RestApiImpl()
    private var localApi: LocalApi = RealmDataSource()

    fun getMovieShow(id: Long, isMovie: Boolean, languageCode: String): LiveData<MovieShowDetail> {
        if (movieDetail == null) {
            movieDetail = MutableLiveData()
            loadMovie(id, isMovie, languageCode)
        }
        return movieDetail as MutableLiveData<MovieShowDetail>
    }

    fun getFavorite(movieId: Long, isMovie: Boolean): LiveData<Boolean> {
        if (isFavorite == null) {
            isFavorite = MutableLiveData()
            loadFavorite(movieId, isMovie)
        }
        return isFavorite as MutableLiveData<Boolean>
    }

    fun getErrorMessage(): LiveData<String> {
        if (errorMessage == null) {
            errorMessage = MutableLiveData()
        }
        return errorMessage as MutableLiveData<String>
    }

    fun addFavorite(movieShow: MovieShow) {
        localApi.insertMovieShowFavorite(movieShow)
        loadFavorite(movieShow.id, movieShow.isMovie)
    }

    fun removeMovie(movieId: Long, isMovie: Boolean) {
        localApi.removeMovieShowFavorite(movieId, isMovie)
        loadFavorite(movieId, isMovie)
    }

    private fun loadFavorite(movieId: Long, isMovie: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            isFavorite?.postValue(localApi.getMovieShowFavorite(movieId, isMovie) != null)
        }
    }

    private fun loadMovie(id: Long, isMovie: Boolean, languageCode: String) {
        errorMessage = null
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (isMovie) {
                    val movieResponse = restApi.getMovieDetail(
                        Constant.getEndpointPath(Constant.GET_MOVIE_DETAIL) + id,
                        Constant.API_KEY,
                        languageCode
                    )
                    if (movieResponse.isSuccessful) {
                        movieDetail?.postValue(MovieShowDetail.getInstance(movieResponse.body()))
                    }
                } else {
                    val tvShowResponse = restApi.getTvShowDetail(
                        Constant.getEndpointPath(Constant.GET_TV_SHOW_DETAIL) + id,
                        Constant.API_KEY,
                        languageCode
                    )
                    if (tvShowResponse.isSuccessful) {
                        movieDetail?.postValue(MovieShowDetail.getInstance(tvShowResponse.body()))
                    }
                }
            } catch (ex: Exception) {
                errorMessage?.postValue(ex.message)
            }
        }
    }
}