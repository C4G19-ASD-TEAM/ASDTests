package com.empresa.asdtests

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.empresa.asdtests.database.ASDTestsDB
import com.empresa.asdtests.database.ASDTestsDB_Impl
import com.empresa.asdtests.model.Pregunta
import kotlinx.android.synthetic.main.fragment_user_preguntas.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FragmentUserPreguntas : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fragmento = inflater.inflate(R.layout.fragment_user_preguntas, container, false)

        val btnGuardarPregunta = fragmento.findViewById<Button>(R.id.btnGuardarPregunta)

        btnGuardarPregunta.setOnClickListener {
            val context = activity?.applicationContext


            //Instanciamos un objeto pelicula par Guardar en la BD
            val pregunta = Pregunta(0, "aaa", "bbb", "ccc", "ddd" )

            //insertamos en la BDs utilizando una Coroutine
            CoroutineScope(Dispatchers.IO).launch {
                val database = context?.let { ASDTestsDB.getDataBase(it) }
                if (database != null) {
                    database.preguntaDAO().insert(pregunta)
                    Log.e("FG", "Ingresó a insertar pregunta")
                }else{
                    Toast.makeText(activity, "DB null - Click en Guardar pregunta", Toast.LENGTH_SHORT).show()
                }

            }

           val btnCancelar = fragmento.findViewById<Button>( R.id.btnCancelarAgregarPregunta)
            btnCancelar.setOnClickListener {
                salir()
            }
        }




            return fragmento

    }

    private fun salir(){
        val lvPreguntas = activity?.findViewById<ListView>( R.id.lvPreguntas )
        lvPreguntas?.visibility = View.VISIBLE

        activity?.supportFragmentManager?.beginTransaction()
            ?.remove( this )
            ?.commit()
    }


}