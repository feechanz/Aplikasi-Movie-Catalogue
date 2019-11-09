package com.feechanz.aplikasimoviecatalogue.presentation.favorite.show

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
class ShowFavoriteViewModel : ViewModel() {
    private var shows: MutableLiveData<List<MovieShow>>? = null
    private var localApi: LocalApi = RealmDataSource()

    fun getTvShows(): LiveData<List<MovieShow>> {
        if (shows == null) {
            shows = MutableLiveData()
            loadShows()
        }
        return shows as MutableLiveData<List<MovieShow>>
    }

    private fun loadShows() {
        CoroutineScope(Dispatchers.IO).launch {
            shows?.postValue(localApi.getTvShowFavorites())
        }
    }
}