package com.feechanz.aplikasimoviecatalogue.widget

import android.content.Context
import android.content.Intent
import android.os.Binder
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import com.feechanz.aplikasimoviecatalogue.data.realm.LocalApi
import com.feechanz.aplikasimoviecatalogue.data.realm.RealmDataSource
import com.feechanz.aplikasimoviecatalogue.utils.Constant


/**
 * Created by Feechan on 11/10/2019.
 */
internal class StackRemoteViewsFactory(private val mContext: Context) :
    RemoteViewsService.RemoteViewsFactory {

    private val localApi: LocalApi = RealmDataSource()
    private val movieShow = ArrayList<MovieShow>()

    override fun onDataSetChanged() {
        movieShow.clear()
        val identityToken = Binder.clearCallingIdentity()
        movieShow.addAll(localApi.getMovieFavorites())
        Binder.restoreCallingIdentity(identityToken)
    }

    override fun onCreate() {}

    override fun onDestroy() {}

    override fun getCount(): Int = movieShow.size

    override fun getViewAt(position: Int): RemoteViews {
        val view = RemoteViews(mContext.packageName, R.layout.widget_item)
        setView(view, position)
        return view
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(i: Int): Long = 0

    override fun hasStableIds(): Boolean = false

    private fun setView(view: RemoteViews, position: Int) {
        val movieShow = movieShow[position]
        try {
            val bitmap = Glide.with(mContext)
                .asBitmap()
                .load(Constant.getPictureFullPath() + movieShow.posterPath)
                .submit(512, 512)
                .get()
            view.setImageViewBitmap(R.id.contentImageView, bitmap)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        view.setTextViewText(R.id.titleTextView, movieShow.title)
        val extras = bundleOf(
            FavoriteMovieWidget.EXTRA_ITEM to position
        )
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)

        view.setOnClickFillInIntent(R.id.contentImageView, fillInIntent)
    }
}