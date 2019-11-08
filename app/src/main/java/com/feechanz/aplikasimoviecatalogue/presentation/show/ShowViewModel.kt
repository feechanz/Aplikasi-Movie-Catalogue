package com.feechanz.aplikasimoviecatalogue.presentation.show

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
class ShowViewModel : ViewModel(){

    private var shows: MutableLiveData<List<MovieShow>>? = null
    private var restApi: RestApi = RestApiImpl()

    fun getMovies(languageCode: String): LiveData<List<MovieShow>> {
        if(shows == null){
            shows = MutableLiveData()
            loadShows(languageCode)
        }
        return shows as MutableLiveData<List<MovieShow>>
    }

    private fun loadShows(languageCode: String){
        val moviesResult = arrayListOf<MovieShow>()

        CoroutineScope(Dispatchers.IO).launch {
            val showResponse = restApi.getTvShows(
                Constant.getEndpointPath(Constant.GET_TV_SHOW),
                Constant.API_KEY,
                languageCode
            )
            if (showResponse.isSuccessful) {
                showResponse.body().results?.map { m ->
                    moviesResult.add(MovieShow.getInstance(m))
                }
                shows?.postValue(moviesResult)
            }
        }

    }
}