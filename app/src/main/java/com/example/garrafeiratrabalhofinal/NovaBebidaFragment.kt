package com.example.garrafeiratrabalhofinal

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.example.garrafeira.TabelaBebidas
import com.example.garrafeira.TabelaTipos
import com.example.garrafeiratrabalhofinal.databinding.FragmentNovaBebidaBinding
import com.example.garrafeiratrabalhofinal.databinding.FragmentSobreBinding


private const val ID_LOADER_TIPOS = 0

class NovaBebidaFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    private var _binding: FragmentNovaBebidaBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNovaBebidaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loader = LoaderManager.getInstance(this)
        loader.initLoader(ID_LOADER_TIPOS,null,this)


        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista_bebidas
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_Guardar -> {
                guardar()
                true
            }
            R.id.action_Cancelar-> {
                cancelar()
                true
            }
            else -> false
        }
    }

    private fun cancelar() {
        findNavController().navigate(R.id.action_novaBebidaFragment_to_ListaTiposFragment)
    }

    private fun guardar() {
        val nome = binding.editTextText.text.toString()
        if(nome.isBlank()){
            binding.editTextText.error = getString(R.string.)
            binding.editTextText.requestFocus()
            return
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            BebidasContentProvider.ENDERECO_TIPOS,
            TabelaTipos.CAMPOS,
            null, null,
            TabelaTipos.CAMPO_TIPO)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
       binding.spinner.adapter = null
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        if(data == null){
            binding.spinner.adapter = null
            return
        }
        binding.spinner.adapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_expandable_list_item_1,
            data,
            arrayOf(TabelaTipos.CAMPO_TIPO),
            intArrayOf(android.R.id.text1),
            0
        )
    }
}