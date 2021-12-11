package com.empresa.asdtests


import android.content.Context
import android.graphics.Color
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.empresa.asdtests.databinding.PreguntasItemBinding
import com.empresa.asdtests.model.Pregunta
import com.empresa.asdtests.model.Test


class TestPreguntasAdapter (private val mContext: Context, val listaTestPreguntas: List<Test>)
    : ArrayAdapter<Test>( mContext, 0, listaTestPreguntas ){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContext).inflate(R.layout.test_preguntas_item,parent,false)

        val test = listaTestPreguntas[position]
        layout.findViewById<TextView>(R.id.tvTestUniqueId).text = test.id
        layout.findViewById<TextView>(R.id.tvTestId).text = test.testId
        layout.findViewById<TextView>(R.id.tvPreguntaId).text = test.preguntaId
        layout.findViewById<TextView>(R.id.tvPreguntaTexto).text = test.preguntaTexto
//        layout.findViewById<TextView>(R.id.tvOpcion).text = pregunta.opcion1
//        layout.findViewById<TextView>(R.id.tvRespuesta).text = pregunta.respuesta

//        if(pregunta.respuesta != "NA" ){
//            layout.findViewById<LinearLayout>(R.id.lyLayout).setBackgroundColor(Color.GRAY)
//            Log.e("FG", "respuesta: " + pregunta.respuesta)
//        }else{
//            layout.findViewById<LinearLayout>(R.id.lyLayout).setBackgroundColor(Color.WHITE)
//        }

        return layout
    }
}
