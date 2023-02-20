package com.behad.adid

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri

private const val PACKAGE_NAME = "com.behad.adid"
private const val ADID = "ADID"

class AdIDContentProvider : ContentProvider() {

    private val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(PACKAGE_NAME, ADID, 1)
    }

    private var sharedPrefAdId: SharedPrefAdId? = null

    override fun onCreate(): Boolean {
        sharedPrefAdId = context?.let { SharedPrefAdId(it) }
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?,
    ): Cursor? {
        var cursor: Cursor? = null
        if (uriMatcher.match(uri) == 1 || sortOrder == BehadADID.DEFAULT_SORT_ORDER) {
            cursor = returnCursor()
        }
        return cursor
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return -1
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?,
    ): Int {
        return -1
    }

    private fun returnCursor(): Cursor {
        return MatrixCursor(arrayOf(ADID), 2).apply {
            addRow(arrayOf(sharedPrefAdId?.AdID.toString()))
        }
    }
}
