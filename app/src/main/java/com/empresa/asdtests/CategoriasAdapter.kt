package com.empresa.asdtests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoriasAdapter (private val datos: ArrayList<Categoria>) : RecyclerView.Adapter<CategoriasAdapter.ViewHolder> (){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvCategoriaNombre: TextView
        val tvCategoriaDescripcion: TextView

        init{
            tvCategoriaNombre = view.findViewById(R.id.tvCategoriaNombre)
            tvCategoriaDescripcion = view.findViewById(R.id.tvCategoriaDescripcion)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.categorias_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //viewHolder.tvCategoriaNombre.text = datos[position].categoriaNombre
        viewHolder.tvCategoriaNombre.text = datos[position].categoriaNombre
        viewHolder.tvCategoriaDescripcion.text = datos[position].categoriaDescripcion

    }

    override fun getItemCount(): Int {
        return datos.size
    }

}