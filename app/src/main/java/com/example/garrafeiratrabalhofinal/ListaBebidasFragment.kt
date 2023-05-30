package com.example.garrafeiratrabalhofinal

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.garrafeira.TabelaBebidas
import com.example.garrafeiratrabalhofinal.databinding.FragmentListaTiposBinding


private const val ID_LOADER_BEBIDAS = 0


class ListaBebidasFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentListaTiposBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaTiposBinding.inflate(inflater, container, false)
        return binding.root



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_tipos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterBebidas = AdapterbBebidas()
        binding.RecyclerViewTipos.adapter = adapterBebidas
        binding.RecyclerViewTipos.layoutManager = LinearLayoutManager(requireContext())


       val loader = LoaderManager.getInstance(this)
        loader.initLoader(ID_LOADER_BEBIDAS,null,this)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
    }
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            BebidasCsontentProvider.ENDERECO_BEBIDAS,
            TabelaBebidas.CAMPOS,
            null, null,
            TabelaBebidas.NOME_MARCA)

    }

    private val adapterbBebidas = AdapterbBebidas()
    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterbBebidas.cursor = null
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterbBebidas.cursor = data
    }
}