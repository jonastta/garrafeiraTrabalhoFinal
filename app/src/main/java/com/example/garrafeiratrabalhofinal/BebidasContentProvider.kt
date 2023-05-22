package com.example.garrafeiratrabalhofinal

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class BebidasCsontentProvider : ContentProvider(){

    private var bdOpenHelper : BdGarrafeira ?= null
    override fun onCreate(): Boolean {
        bdOpenHelper = BdGarrafeira(context)
        return true
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    companion object{
        private const val AUTORIDADE = "com.example.garrafeiratrabalhofinal"

        const val TIPOS = "Tipos"
        const val BEBIDAS = "Bebidas"

        private const val URI_TIPOS = 100
        private const val URI_TELEMOVEIS = 200

        fun UriMatcher() = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTORIDADE, TIPOS, URI_TIPOS)
            addURI(AUTORIDADE, BEBIDAS, URI_TELEMOVEIS)
        }
    }
}