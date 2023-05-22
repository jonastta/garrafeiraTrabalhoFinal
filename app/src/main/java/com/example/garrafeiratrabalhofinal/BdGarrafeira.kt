package layout

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.garrafeira.TabelaBebidas
import com.example.garrafeira.TabelaTipos

private const val NOME_BASE_DADOS = "Garrafeira.db"

private const val VERSÃO_BASE_DADOS = 1

class BdGarrafeira(
    context: Context?,
) : SQLiteOpenHelper(context, NOME_BASE_DADOS, null,VERSÃO_BASE_DADOS) {

        override fun onCreate(db: SQLiteDatabase?) {
            requireNotNull(db)
            TabelaTipos(db).cria()
            TabelaBebidas(db).cria()
        }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    companion object{
        const val NOME_BASE_DADOS = "Garrafeira.bd"
    }
}