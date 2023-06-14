package com.example.garrafeiratrabalhofinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.garrafeiratrabalhofinal.databinding.FragmentEliminarBebidaBinding


class Eliminar_Bebida : Fragment() {
    private lateinit var bebidas: Bebidas
    private var _binding: FragmentEliminarBebidaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEliminarBebidaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_eliminar

        bebidas = Eliminar_BebidaArgs.fromBundle(requireArguments()).bebidas

        binding.textViewMarca2.text = bebidas.marca
        binding.textViewTeorAlcoolico2.text = bebidas.TEOR_ALCOOLICO
        binding.textViewTipo3.text = bebidas.tipos.tipos

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_eliminar -> {
                eliminar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaLivros()
                true
            }
            else -> false
        }
    }

    private fun voltaListaLivros() {
        findNavController().navigate(R.id.action_eliminar_Bebida_to_ListaTiposFragment)
    }

    private fun eliminar() {
    }
}