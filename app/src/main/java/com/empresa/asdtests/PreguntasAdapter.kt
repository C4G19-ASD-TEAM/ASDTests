package com.empresa.asdtests

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.empresa.asdtests.databinding.PreguntasItemBinding
import com.empresa.asdtests.model.Pregunta


class PreguntasAdapter (private val mContext: Context, val listaPreguntas: List<Pregunta>)
    : ArrayAdapter<Pregunta>( mContext, 0, listaPreguntas ){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContext).inflate(R.layout.preguntas_item,parent,false)

        val pregunta = listaPreguntas[position]
        layout.findViewById<TextView>(R.id.tvId).text = pregunta.id
        layout.findViewById<TextView>(R.id.tvArea).text = pregunta.area
        layout.findViewById<TextView>(R.id.tvPreText).text = pregunta.pretexto
//        layout.findViewById<TextView>(R.id.tvOpcion).text = pregunta.opcion1
//        layout.findViewById<TextView>(R.id.tvRespuesta).text = pregunta.respuesta


        return layout
    }
}
