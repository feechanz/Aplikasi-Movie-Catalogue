package com.feechanz.aplikasimoviecatalogue.data.realm

import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import com.feechanz.aplikasimoviecatalogue.data.realm.model.MovieShowRealm
import io.realm.Realm


/**
 * Created by Feechan on 11/9/2019.
 */
class RealmDataSource : LocalApi {
    override fun getMovieFavorites(): List<MovieShow> {
        val realm = Realm.getDefaultInstance()
        val query = realm.where(MovieShowRealm::class.java)
            .equalTo(MovieShowRealm.IS_MOVIE_COLUMN, true)
            .findAll()
        var results = arrayListOf<MovieShow>()

        query.toList().map { m ->
            results.add(m.transform())
        }
        realm.close()
        return results
    }

    override fun getTvShowFavorites(): List<MovieShow> {
        val realm = Realm.getDefaultInstance()
        val query = realm.where(MovieShowRealm::class.java)
            .equalTo(MovieShowRealm.IS_MOVIE_COLUMN, false)
            .findAll()
        var results = arrayListOf<MovieShow>()

        query.toList().map { m ->
            results.add(m.transform())
        }
        realm.close()
        return results
    }

    override fun insertMovieShowFavorite(movie: MovieShow): Boolean {
        val realm = Realm.getDefaultInstance()
        val maxId = realm.where(MovieShowRealm::class.java).max(MovieShowRealm.ID_COLUMN)
        var id: Long = 1
        if (maxId != null) {
            id = maxId.toLong() + 1
        }
        val movieRealm = MovieShowRealm.getInstance(id, movie)
        realm.executeTransaction { realm1 -> realm.copyToRealmOrUpdate(movieRealm) }
        realm.close()
        return realm.isClosed
    }

    override fun getMovieShowFavorite(movieId: Long, isMovie: Boolean): MovieShow? {
        val realm = Realm.getDefaultInstance()
        val query = realm.where(MovieShowRealm::class.java)
            .equalTo(MovieShowRealm.MOVIE_ID_COLUMN, movieId)
            .equalTo(MovieShowRealm.IS_MOVIE_COLUMN, isMovie)
            .findFirst()
        val result = query?.transform()
        realm.close()
        return result
    }

    override fun removeMovieShowFavorite(movieId: Long, isMovie: Boolean): Boolean {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(MovieShowRealm::class.java)
            .equalTo(MovieShowRealm.MOVIE_ID_COLUMN, movieId)
            .equalTo(MovieShowRealm.IS_MOVIE_COLUMN, isMovie)
            .findFirst()
        realm.executeTransaction { realm1 -> result?.deleteFromRealm() }
        realm.close()
        return realm.isClosed
    }
}