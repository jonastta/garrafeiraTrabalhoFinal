package layout

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val NOME_BASE_DADOS = "Garrafeira.db"

private const val VERSÃO_BASE_DADOS = 1

class bdGarrafeira(
    context: Context?,
) : SQLiteOpenHelper(context, "Garrafeira.db", null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, OldVersion: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}