package com.example.garrafeiratrabalhofinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.garrafeiratrabalhofinal.databinding.FragmentNovaBebidaBinding
import com.example.garrafeiratrabalhofinal.databinding.FragmentSobreBinding


class NovaBebidaFragment : Fragment() {
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


        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista_bebidas
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}