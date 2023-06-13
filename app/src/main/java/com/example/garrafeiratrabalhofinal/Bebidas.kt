package com.example.garrafeiratrabalhofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import com.example.garrafeira.TabelaBebidas

data class Bebidas(
    var marca : String?,
    var TEOR_ALCOOLICO: String?,
    var tipos : Tipos,
    var id : Long = -1
) {
    fun toContentValues() : ContentValues{
        val valores = ContentValues()


        valores.put(TabelaBebidas.NOME_MARCA,marca)
        valores.put(TabelaBebidas.NOME_TEOR_ALCOOLICO,TEOR_ALCOOLICO)
        valores.put(TabelaBebidas.NOME_FK_TIPOS,tipos.id)


        return valores
    }
    companion object {
        fun fromCursor(cursor: Cursor): Bebidas{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posMarca = cursor.getColumnIndex(TabelaBebidas.NOME_MARCA)
            val posTeor_Alcoolico = cursor.getColumnIndex(TabelaBebidas.NOME_TEOR_ALCOOLICO)
            val posId_tipos = cursor.getColumnIndex(TabelaBebidas.NOME_FK_TIPOS)
            val posNomeTipos = cursor.getColumnIndex(TabelaBebidas.CAMPO_NOME_TIPO)
            val posSaborTipos = cursor.getColumnIndex(TabelaBebidas.CAMPO_SABOR_TIPO)
            val posQuantidadeTipos = cursor.getColumnIndex(TabelaBebidas.CAMPO_QUANTIDADE_TIPO)


            val id = cursor.getLong(posId)
            val teor_alcoolico = cursor.getString(posTeor_Alcoolico)
            val marca = cursor.getString(posMarca)
            val Nome_Tipo = cursor.getString(posNomeTipos)
            val tipos = cursor.getLong(posId_tipos)
            val Sabor_tipos = cursor.getString(posSaborTipos)
            val Quantidade_tipos = cursor.getString(posQuantidadeTipos)

            return Bebidas(marca, teor_alcoolico, Tipos (Nome_Tipo,Sabor_tipos,Quantidade_tipos, tipos) , id)
        }
    }
}