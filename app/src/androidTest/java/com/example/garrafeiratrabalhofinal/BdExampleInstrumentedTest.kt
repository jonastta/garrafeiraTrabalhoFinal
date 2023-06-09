package com.example.garrafeiratrabalhofinal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.garrafeira.TabelaBebidas
import com.example.garrafeira.TabelaTipos

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BdExampleInstrumentedTest {

    private fun getAppContext(): Context =
        InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBaseDados(){
        //getAppContext().deleteDatabase(BdGarrafeira.NOME_BASE_DADOS)
    }
    @Test
    fun consegueAbrirBaseDados() {
        val openHelper = BdGarrafeira(getAppContext())
        val bd = openHelper.readableDatabase
        assert(bd.isOpen)
    }
    @Test
    fun ConsegueInserirTipos(){
        val bd = getWritableDatabase()

        val tipo = Tipos("sumos", "laranja",1.00)
        insereTipos(bd, tipo)
    }

    private fun insereTipos(bd: SQLiteDatabase, tipo: Tipos) {
        tipo.id = TabelaTipos(bd).insere(tipo.toContentValues())
        assertNotEquals(-1, tipo.id)
    }


    @Test
    fun ConsegueInserirBebidas(){
        val bd = getWritableDatabase()

        val tipo = Tipos("Vinho", "Tinto",0.75)
        insereTipos(bd, tipo)

        val Bebida1 = Bebidas("Joy","Não", tipo)
        insereBebida(bd, Bebida1)

        val Bebida2 = Bebidas("Papa Figos", "Sim", tipo)
        insereBebida(bd, Bebida2)
    }

    @Test
    fun consegueLerTipos() {
        val bd = getWritableDatabase()

        val tipo1 = Tipos("Vinhos", "Tinto",0.75)
        insereTipos(bd, tipo1)

        val tipo2 = Tipos("Sumos", "Laranja",1.00)
        insereTipos(bd, tipo2)

        val tabelaTipos = TabelaTipos(bd)

        val cursor = tabelaTipos.consulta(
            TabelaTipos.CAMPOS, "${BaseColumns._ID}=?", arrayOf(tipo2.id.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())

        val tipoBD = Tipos.fromCursor(cursor)

        assertEquals(tipo2,tipoBD)

        val cursorTodosTipos = tabelaTipos.consulta(
            TabelaTipos.CAMPOS,
            null,
            null,
            null,
            null,
            TabelaTipos.CAMPO_TIPO
        )

        assert(cursorTodosTipos.count > 1)
    }
    @Test
    fun consegueLerBebidas() {
        val bd = getWritableDatabase()

        val tipo = Tipos("Licor", "Tinto",0.75)
        insereTipos(bd, tipo)

        val Bebida = Bebidas("Cerveja","Não", tipo)
        insereBebida(bd, Bebida)

        val Bebida1 = Bebidas("Sumol","Não",tipo)
        insereBebida(bd, Bebida1)

        val tabelaBebidas = TabelaBebidas(bd)

        val cursor = tabelaBebidas.consulta(
            TabelaBebidas.CAMPOS,
            "${TabelaBebidas.CAMPO_ID}=?", arrayOf(Bebida.id.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())

        val bebidasBD = Bebidas.fromCursor(cursor)

        assertEquals(Bebida,bebidasBD)

        val cursorTodosAsBebidas = tabelaBebidas.consulta(
            TabelaBebidas.CAMPOS,
            null,
            null,
            null,
            null,
            TabelaBebidas.NOME_MARCA
        )

        assert(cursorTodosAsBebidas.count > 1)
    }


    private fun insereBebida(bd: SQLiteDatabase, bebida: Bebidas) {
        bebida.id = TabelaBebidas(bd).insere(bebida.toContentValues())
        assertNotEquals(-1, bebida.id)
    }

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BdGarrafeira(getAppContext())
        val bd = openHelper.writableDatabase
        return bd
    }

    @Test

    fun consegueAlterarTipos(){

        val bd = getWritableDatabase()

        val tipo = Tipos("Vinhos", "Tinto",0.75)
        insereTipos(bd, tipo)

        tipo.tipos = "Sumos"
        val registoAlterado = TabelaTipos(bd).altera(tipo.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(tipo.id.toString()),
        )

        assertEquals(1,registoAlterado)
    }

    @Test

    fun consegueAlterarBebidas(){

        val bd = getWritableDatabase()

        val tipo = Tipos("Vinhos", "Tinto",0.75)
        insereTipos(bd, tipo)

        val tipo2 = Tipos("Sumos", "Laranja",1.00)
        insereTipos(bd, tipo2)

        val Bebida1 = Bebidas("...","...",tipo)
        insereBebida(bd, Bebida1)

        Bebida1.tipos = tipo2
        Bebida1.marca = "Sumol"
        Bebida1.TEOR_ALCOOLICO="Não"


        val registoAlterado = TabelaBebidas(bd).altera(Bebida1.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(Bebida1.id.toString()),
        )

        assertEquals(1,registoAlterado)

    }

    @Test

    fun consegueEleminarTipos(){

        val bd = getWritableDatabase()

        val tipo = Tipos("Vinhos", "Tinto",0.75)
        insereTipos(bd, tipo)


        val registoEliminado = TabelaTipos(bd).elimina(
            "${BaseColumns._ID}=?",
            arrayOf(tipo.id.toString()),
        )

        assertEquals(1,registoEliminado)
    }

    @Test

    fun consegueEliminarBebidas(){

        val bd = getWritableDatabase()

        val tipo = Tipos("Vinhos", "Tinto",0.75)
        insereTipos(bd, tipo)


        val Bebida1 = Bebidas("Papa Figos","Sim",tipo)
        insereBebida(bd, Bebida1)




        val registoEliminado = TabelaTipos(bd).elimina(
            "${BaseColumns._ID}=?",
            arrayOf(tipo.id.toString()),
        )

        assertEquals(1,registoEliminado)

    }
}