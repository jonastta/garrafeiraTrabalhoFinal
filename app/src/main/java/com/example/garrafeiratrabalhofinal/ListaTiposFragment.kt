package com.example.garrafeiratrabalhofinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.garrafeiratrabalhofinal.databinding.FragmentListaTiposBinding


private var _binding: FragmentListaTiposBinding? = null
// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!

class ListaTiposFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_tipos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterTipos = AdapterTipos()
        binding.RecyclerViewTipos.adapter = adapterTipos
        binding.RecyclerViewTipos.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
    }
}