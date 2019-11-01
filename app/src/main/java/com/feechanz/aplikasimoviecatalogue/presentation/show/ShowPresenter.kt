package com.feechanz.aplikasimoviecatalogue.presentation.show

import com.feechanz.aplikasimoviecatalogue.data.ShowRepository

/**
 * Created by Feechan on 11/1/2019.
 */
class ShowPresenter(private val view: ShowContract.View,
                    private val repository: ShowRepository) : ShowContract.Presenter{

    override fun loadShow() {
        val movies = repository.getShows()
        view.showShows(movies)
    }

}