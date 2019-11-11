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
class ShowViewModel : ViewModel() {

    private var shows: MutableLiveData<List<MovieShow>>? = null
    private var showsQuery: MutableLiveData<List<MovieShow>>? = null
    private var errorMessage: MutableLiveData<String>? = null
    private var restApi: RestApi = RestApiImpl()
    private var showsFiltered: MutableLiveData<List<MovieShow>>? = null

    fun getShows(languageCode: String): LiveData<List<MovieShow>> {
        shows = MutableLiveData()
        loadShows(languageCode)

        return shows as MutableLiveData<List<MovieShow>>
    }

    fun getShowsQuery(languageCode: String, query: String): LiveData<List<MovieShow>> {

        showsQuery = MutableLiveData()
        loadShowsQuery(languageCode, query)

        return showsQuery as MutableLiveData<List<MovieShow>>
    }

    fun getErrorMessage(): LiveData<String> {
        if (errorMessage == null) {
            errorMessage = MutableLiveData()
        }
        return errorMessage as MutableLiveData<String>
    }

    fun getShowsFiltered(pattern: String): LiveData<List<MovieShow>> {

        showsFiltered = MutableLiveData()
        loadShowsFiltered(pattern)

        return showsFiltered as MutableLiveData<List<MovieShow>>
    }

    private fun loadShowsQuery(languageCode: String, query: String) {
        errorMessage = null
        val moviesResult = arrayListOf<MovieShow>()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val showResponse = restApi.getTvShowsSearch(
                    Constant.getEndpointPath(Constant.GET_TV_SHOW_SEARCH),
                    Constant.API_KEY,
                    languageCode,
                    query
                )
                if (showResponse.isSuccessful) {
                    showResponse.body().results?.map { m ->
                        moviesResult.add(MovieShow.getInstance(m))
                    }
                    showsQuery?.postValue(moviesResult)
                }
            } catch (ex: Exception) {
                errorMessage?.postValue(ex.message)
            }
        }
    }

    private fun loadShows(languageCode: String) {
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
            } catch (ex: Exception) {
                errorMessage?.postValue(ex.message)
            }
        }
    }

    private fun loadShowsFiltered(pattern: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = ArrayList<MovieShow>()
            shows?.value?.map { m ->
                if (m.title!!.toLowerCase().contains(pattern.toLowerCase()))
                    result.add(m)
            }
            showsFiltered?.postValue(result)
        }
    }
}