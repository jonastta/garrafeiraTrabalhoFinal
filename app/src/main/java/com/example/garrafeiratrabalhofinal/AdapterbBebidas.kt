package com.example.garrafeiratrabalhofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterbBebidas(val fragment: ListaBebidasFragment) : RecyclerView.Adapter<AdapterbBebidas.ViewHolderBebidas>() {

    var cursor:Cursor? = null
        set(value){
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolderBebidas(itemView: View) :ViewHolder(itemView) {
        internal var bebidas:Bebidas? = null

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBebidas {
        return ViewHolderBebidas(
        fragment.layoutInflater.inflate(R.layout.item_bebidas,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return cursor?.count?:0
    }

    override fun onBindViewHolder(holder: ViewHolderBebidas, position: Int) {
        cursor!!.move(position)
        holder.bebidas=Bebidas.fromCursor(cursor!!)
        //holder
    }
}