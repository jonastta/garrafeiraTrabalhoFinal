package com.example.garrafeiratrabalhofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterbBebidas : RecyclerView.Adapter<AdapterbBebidas.ViewHolderTipos>() {

    var cursor:Cursor? = null
        set(value){
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolderTipos(itemView: View) :ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTipos {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return cursor?.count?:0
    }

    override fun onBindViewHolder(holder: ViewHolderTipos, position: Int) {
        TODO("Not yet implemented")
    }
}