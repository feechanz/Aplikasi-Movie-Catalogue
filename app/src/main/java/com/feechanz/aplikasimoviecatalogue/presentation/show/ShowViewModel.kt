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
import java.lang.Exception

/**
 * Created by Feechan on 11/1/2019.
 */
class ShowViewModel : ViewModel(){

    private var shows: MutableLiveData<List<MovieShow>>? = null
    private var errorMessage: MutableLiveData<String>? = null
    private var restApi: RestApi = RestApiImpl()

    fun getMovies(languageCode: String): LiveData<List<MovieShow>> {
        if(shows == null){
            shows = MutableLiveData()
            loadShows(languageCode)
        }
        return shows as MutableLiveData<List<MovieShow>>
    }

    fun getErrorMessage(): LiveData<String>{
        if(errorMessage == null){
            errorMessage = MutableLiveData()
        }
        return errorMessage as MutableLiveData<String>
    }

    private fun loadShows(languageCode: String){
        errorMessage = null
        val moviesResult = arrayListOf<MovieShow>()

        CoroutineScope(Dispatchers.IO).launch {
            try {
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
            }catch (ex: Exception){
                errorMessage?.postValue(ex.message)
            }
        }
    }
}