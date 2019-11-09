package com.feechanz.aplikasimoviecatalogue.data.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.feechanz.aplikasimoviecatalogue.data.provider.ContentProviderContract.AUTHORITY
import com.feechanz.aplikasimoviecatalogue.data.provider.ContentProviderContract.BASE_PATH

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

    override fun onCreate(): Boolean {
        return true
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


    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var cursor: Cursor? = null

        return cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}
