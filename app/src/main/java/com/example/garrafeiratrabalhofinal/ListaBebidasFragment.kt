package com.example.garrafeiratrabalhofinal

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.garrafeira.TabelaBebidas
import com.example.garrafeiratrabalhofinal.databinding.FragmentListaTiposBinding



private const val ID_LOADER_BEBIDAS = 0


class ListaBebidasFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentListaTiposBinding? = null

    private val binding get() = _binding!!

    var bebedidaSelecionada : Bebidas? = null
        set(value){
            field = value

            val mostarEleminarAlterar = (value != null)

            val activity = activity as MainActivity
            activity.mostraBotaoMenu(R.id.action_Editar,mostarEleminarAlterar)
            activity.mostraBotaoMenu(R.id.action_Eliminar,mostarEleminarAlterar)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaTiposBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var adapterBebidas:  AdapterBebidas ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterBebidas = AdapterBebidas(this)
        binding.RecyclerViewTipos.adapter = adapterBebidas
        binding.RecyclerViewTipos.layoutManager = LinearLayoutManager(requireContext())


       val loader = LoaderManager.getInstance(this)
        loader.initLoader(ID_LOADER_BEBIDAS,null,this)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista_bebidas
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            BebidasContentProvider.ENDERECO_BEBIDAS,
            TabelaBebidas.CAMPOS,
            null, null,
            TabelaBebidas.NOME_MARCA)

    }


    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterBebidas!!.cursor = null
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterBebidas!!.cursor = data
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_Adicionar -> {
                adicionaBebidas()
                true
            }
            R.id.action_Editar -> {
                editarBebidas()
                true
            }
            R.id.action_Eliminar -> {
                eliminarBebidas()
                true
            }
            else -> false
        }
    }

    private fun eliminarBebidas() {
        val acao = ListaBebidasFragmentDirections.actionListaTiposFragmentToEliminarBebida(bebedidaSelecionada!!)
        findNavController().navigate(acao)
    }

    private fun editarBebidas() {
        val acao = ListaBebidasFragmentDirections.actionListaTiposFragmentToEditarBebidaFragment(bebedidaSelecionada!!)
        findNavController().navigate(acao)
    }

    private fun adicionaBebidas() {
        val acao = ListaBebidasFragmentDirections.actionListaTiposFragmentToEditarBebidaFragment(null)
        findNavController().navigate(acao)
}

}