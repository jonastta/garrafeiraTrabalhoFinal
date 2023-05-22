package com.example.garrafeiratrabalhofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import com.example.garrafeira.TabelaBebidas

data class Bebidas(
    var marca : String?,
    var TEOR_ALCOOLICO: String?,
    var id_tipos : Long = -1,
    var id : Long = -1
) {
    fun toContentValues() : ContentValues{
        val valores = ContentValues()


        valores.put(TabelaBebidas.NOME_MARCA,marca)
        valores.put(TabelaBebidas.NOME_TEOR_ALCOOLICO,TEOR_ALCOOLICO)
        valores.put(TabelaBebidas.NOME_FK_TIPOS,id_tipos)


        return valores
    }
    companion object {
        fun fromCursor(cursor: Cursor): Bebidas{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posMarca = cursor.getColumnIndex(TabelaBebidas.NOME_MARCA)
            val posTeor_Alcoolico = cursor.getColumnIndex(TabelaBebidas.NOME_TEOR_ALCOOLICO)
            val posId_tipos = cursor.getColumnIndex(TabelaBebidas.NOME_FK_TIPOS)

            val teor_alcoolico = cursor.getString(posTeor_Alcoolico)
            val marca = cursor.getString(posMarca)
            val id_tipos = cursor.getLong(posId_tipos)
            val id = cursor.getLong(posId)


            return Bebidas(marca,teor_alcoolico,id_tipos,id)
        }
    }
}