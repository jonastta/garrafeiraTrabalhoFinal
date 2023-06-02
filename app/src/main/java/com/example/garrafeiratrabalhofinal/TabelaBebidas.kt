package com.example.garrafeira

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaBebidas(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $NOME_MARCA TEXT NOT NULL, $NOME_TEOR_ALCOOLICO BOOLEAN NOT NULL, $NOME_FK_TIPOS INTEGER NOT NULL, FOREIGN KEY ($NOME_FK_TIPOS) REFERENCES ${TabelaTipos.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    override fun consulta(
        colunas: Array<String>,
        selecao: String?,
        argsSelecao: Array<String>?,
        groupby: String?,
        having: String?,
        orderby: String?
    ) : Cursor {
        val sql = SQLiteQueryBuilder()
        sql.tables ="$NOME_TABELA INNER JOIN ${TabelaTipos.NOME_TABELA} ON ${TabelaTipos.CAMPO_ID}=$NOME_FK_TIPOS "
        return sql.query(db, colunas, selecao, argsSelecao, groupby, having, orderby )
    }
    companion object {

        const val NOME_TABELA = "Bebidas"

        const val  CAMPO_ID = "$NOME_TABELA.${BaseColumns._ID}"


        const val NOME_MARCA = "marca"
        const val NOME_TEOR_ALCOOLICO = "TEOR_ALCOOLICO"
        const val NOME_FK_TIPOS = "id_tipos"
        const val CAMPO_NOME_TIPO = TabelaTipos.CAMPO_TIPO;
        const val CAMPO_SABOR_TIPO = TabelaTipos.CAMPO_SABOR
        const val CAMPO_QUANTIDADE_TIPO = TabelaTipos.CAMPO_QUANTIDADE




        val CAMPOS = arrayOf(
            CAMPO_ID,
            NOME_MARCA,
            NOME_TEOR_ALCOOLICO,
            NOME_FK_TIPOS,
            CAMPO_NOME_TIPO,
            CAMPO_SABOR_TIPO,
            CAMPO_QUANTIDADE_TIPO)
    }
}