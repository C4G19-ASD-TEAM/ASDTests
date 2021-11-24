package com.empresa.asdtests

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.empresa.asdtests.model.Usuario
import kotlinx.android.synthetic.main.preguntas_item.view.*
import kotlinx.android.synthetic.main.usuarios_item.view.*

class UsuarioAdapter (private val mContext: Context, val listaUsuarios: List<Usuario>)
    : ArrayAdapter<Usuario>( mContext, 0, listaUsuarios ){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContext).inflate(R.layout.usuarios_item,parent,false)
        val usuario = listaUsuarios[position]
        layout.tvUsername.text = usuario.username
        layout.tvPassword.text = usuario.password
        layout.tvRole.text = usuario.role

        return layout
    }
}
