package com.feechanz.aplikasimoviecatalogue.data.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.feechanz.aplikasimoviecatalogue.data.provider.ContentProviderContract.AUTHORITY
import com.feechanz.aplikasimoviecatalogue.data.provider.ContentProviderContract.BASE_PATH
import com.feechanz.aplikasimoviecatalogue.data.realm.LocalApi
import com.feechanz.aplikasimoviecatalogue.data.realm.RealmDataSource
import android.database.MatrixCursor
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import io.realm.Realm
import io.realm.RealmConfiguration
import java.lang.Exception


class MovieFavoriteContentProvider : ContentProvider() {
    companion object {
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$BASE_PATH")
        private val MOVIES = 1
        private val MOVIES_ID = 2

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, BASE_PATH, MOVIES)
            uriMatcher.addURI(AUTHORITY, "$BASE_PATH/#", MOVIES_ID)
        }
    }

    private lateinit var localApi: LocalApi

    override fun onCreate(): Boolean {
        localApi = RealmDataSource()
        try{
            val realm = Realm.getDefaultInstance()
            realm.close()
        }catch (ex: Exception){
            Realm.init(context)
            val realmConfiguration = RealmConfiguration.Builder()
                .build()

            Realm.setDefaultConfiguration(realmConfiguration)
        }

        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var cursor: Cursor? = null
        when(uriMatcher.match(uri)){
            MOVIES ->  {
                val myCursor = MatrixCursor(
                    arrayOf(
                        ContentProviderContract.MOVIE_ID_COLUMN,
                        ContentProviderContract.TITLE_COLUMN,
                        ContentProviderContract.OVERVIEW_COLUMN,
                        ContentProviderContract.RELEASE_DATE_COLUMN,
                        ContentProviderContract.POSTER_PATH_COLUMN,
                        ContentProviderContract.VOTE_AVERAGE_COLUMN,
                        ContentProviderContract.IS_MOVIE_COLUMN
                    )
                )
                val listMovie: ArrayList<MovieShow> = ArrayList(localApi.getMovieFavorites())

                listMovie.addAll(localApi.getTvShowFavorites())


                listMovie.map {m ->
                    var isMovieInt = 0
                    if(m.isMovie){
                        isMovieInt = 1
                    }
                    val rowData = arrayOf(
                        m.id,
                        m.title,
                        m.overview,
                        m.releaseDate,
                        m.posterPath,
                        m.voteAverage,
                        isMovieInt
                    )
                    myCursor.addRow(rowData)
                }

                cursor = myCursor
            }
            MOVIES_ID ->  cursor = null
            else -> cursor = null
        }
        return cursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return ContentUris.withAppendedId(CONTENT_URI, 1)
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}
