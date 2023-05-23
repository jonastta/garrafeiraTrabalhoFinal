package com.example.garrafeiratrabalhofinal

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import android.provider.BaseColumns
import com.example.garrafeira.TabelaBebidas
import com.example.garrafeira.TabelaTipos
import java.nio.ByteOrder

class BebidasCsontentProvider : ContentProvider(){

    private var bdOpenHelper : BdGarrafeira?= null
    override fun onCreate(): Boolean {
        bdOpenHelper = BdGarrafeira(context)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {

     val bd = bdOpenHelper!!.readableDatabase
     val id = uri.lastPathSegment

     val endereco = UriMatcher().match(uri)

      val tabela = when (endereco){
          URI_BEBIDAS -> TabelaBebidas(bd)
          URI_BEBIDAS_ID -> TabelaBebidas(bd)
          URI_TIPOS -> TabelaTipos(bd)
          URI_TIPOS_ID -> TabelaTipos(bd)
          else -> null
      }

        val (selecao, argsSel) = when (endereco) {
            URI_TIPOS_ID, URI_BEBIDAS_ID -> Pair("${BaseColumns._ID}=?", arrayOf(id))
            else -> Pair(selection, selectionArgs)
        }



          return tabela?.consulta(
              projection as Array<String>,
              selecao,
              argsSel as Array<String>,null,null,
              sortOrder
          )


    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {

        val bd = bdOpenHelper!!.writableDatabase


        val endereco = UriMatcher().match(uri)

        val tabela = when (endereco){
            URI_BEBIDAS -> TabelaBebidas(bd)
            URI_TIPOS -> TabelaTipos(bd)
            else -> return null
        }

        val id = tabela.insere(values!!)
        if (id == -1L){
            return null
        }

        return Uri.withAppendedPath(uri, id.toString())

    }

    override fun delete(uri: Uri, values: String?, selection: Array<out String>?): Int {
        val bd = bdOpenHelper!!.writableDatabase


        val endereco = UriMatcher().match(uri)

        val tabela = when (endereco){
            URI_BEBIDAS_ID -> TabelaBebidas(bd)
            URI_TIPOS_ID -> TabelaTipos(bd)
            else -> return 0
        }

        val id = uri.lastPathSegment!!

        return tabela.elimina("${BaseColumns._ID}=?", arrayOf(id))
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {

        val bd = bdOpenHelper!!.writableDatabase


        val endereco = UriMatcher().match(uri)

        val tabela = when (endereco){
            URI_BEBIDAS_ID -> TabelaBebidas(bd)
            URI_TIPOS_ID -> TabelaTipos(bd)
            else -> return 0
        }

        val id = uri.lastPathSegment!!

        return tabela.altera(values!!,"${BaseColumns._ID}=?", arrayOf(id))
    }

    companion object{
        private const val AUTORIDADE = "com.example.garrafeiratrabalhofinal"

        const val TIPOS = "Tipos"
        const val BEBIDAS = "Bebidas"

        private const val URI_TIPOS = 100
        private const val URI_TIPOS_ID = 101
        private const val URI_BEBIDAS = 200
        private const val URI_BEBIDAS_ID = 201
        fun UriMatcher() = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTORIDADE, TIPOS, URI_TIPOS)
            addURI(AUTORIDADE,"$TIPOS", URI_TIPOS)
            addURI(AUTORIDADE, BEBIDAS, URI_BEBIDAS)
            addURI(AUTORIDADE,"$BEBIDAS", URI_BEBIDAS)
        }
    }
}