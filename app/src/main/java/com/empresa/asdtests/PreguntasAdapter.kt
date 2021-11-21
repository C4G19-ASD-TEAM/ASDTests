package com.empresa.asdtests

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.empresa.asdtests.model.Pregunta
import kotlinx.android.synthetic.main.preguntas_item.view.*

class PreguntasAdapter (private val mContext: Context, val listaPreguntas: List<Pregunta>)
    : ArrayAdapter<Pregunta>( mContext, 0, listaPreguntas ){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContext).inflate(R.layout.preguntas_item,parent,false)
        val pregunta = listaPreguntas[position]
        layout.tvArea.text = pregunta.area
        layout.tvPreText.text = pregunta.pretexto

        return layout
    }
}
