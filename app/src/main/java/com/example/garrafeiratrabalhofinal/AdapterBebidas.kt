package com.example.garrafeiratrabalhofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterBebidas(val fragment: ListaBebidasFragment) : RecyclerView.Adapter<AdapterBebidas.ViewHolderBebidas>() {

    var cursor:Cursor? = null
        set(value){
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolderBebidas(contentor: View) :ViewHolder(contentor) {

        private val  textViewTipo = contentor.findViewById<TextView>(R.id.textViewTipo)
        private val textViewBebida = contentor.findViewById<TextView>(R.id.textViewBebida)


            init {
                contentor.setOnClickListener {
                    viewHolderSelecionado?.desSeleciona()
                    seleciona()
                }
            }
        internal var bebidas:Bebidas? = null
            set(value){
                field = value
                textViewBebida.text = bebidas?.marca?:""
                textViewTipo.text = bebidas?.tipos?.tipos?:""

            }

        fun seleciona(){
            viewHolderSelecionado = this
            fragment.bebedidaSelecionada = bebidas
            itemView.setBackgroundResource(R.color.item_selecionado)
        }

        fun desSeleciona (){
            itemView.setBackgroundResource(R.color.white)
        }
    }

    private var viewHolderSelecionado : ViewHolderBebidas ?= null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBebidas {
        return ViewHolderBebidas(
        fragment.layoutInflater.inflate(R.layout.item_bebidas,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return cursor?.count?:0
    }

    override fun onBindViewHolder(holder: ViewHolderBebidas, position: Int) {
        cursor!!.moveToPosition(position)
        holder.bebidas=Bebidas.fromCursor(cursor!!)
    }
}