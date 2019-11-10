package com.feechanz.favoriteapp

import android.content.ContentResolver
import android.database.Cursor
import com.feechanz.favoriteapp.model.MovieShow
import com.feechanz.favoriteapp.provider.ContentProviderContract
import kotlinx.coroutines.*


/**
 * Created by Feechan on 11/10/2019.
 */
class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    override fun loadMovie(contentResolver: ContentResolver) {
        CoroutineScope(Dispatchers.IO).launch {
            val cursor = contentResolver.query(
            ContentProviderContract.CONTENT_URI,
                null,
                null,
            null,
                null) as Cursor

            val listMovie = ArrayList<MovieShow>()
            if(cursor != null){
                cursor.moveToFirst()
                do{
                    val id = cursor.getLong(cursor.getColumnIndexOrThrow(ContentProviderContract.MOVIE_ID_COLUMN))
                    val title = cursor.getString(cursor.getColumnIndexOrThrow(ContentProviderContract
                        .TITLE_COLUMN))
                    val overview = cursor.getString(cursor.getColumnIndexOrThrow
                        (ContentProviderContract.OVERVIEW_COLUMN))
                    val releaseDate = cursor.getString(cursor.getColumnIndexOrThrow
                        (ContentProviderContract.RELEASE_DATE_COLUMN))
                    val posterPath = cursor.getString(cursor.getColumnIndexOrThrow
                        (ContentProviderContract.POSTER_PATH_COLUMN))
                    val voteAverage = cursor.getDouble(cursor.getColumnIndexOrThrow
                        (ContentProviderContract.VOTE_AVERAGE_COLUMN))
                    val isMovie = cursor.getInt(cursor.getColumnIndexOrThrow
                        (ContentProviderContract.IS_MOVIE_COLUMN))

                    var isMovieBoolean = false
                    if(isMovie == 1){
                        isMovieBoolean = true
                    }

                    listMovie.add(MovieShow(id, title, overview, releaseDate, posterPath,
                        voteAverage, isMovieBoolean))
                }while(cursor.moveToNext())
            }
            view.showMovies(listMovie)
        }
    }
}