package com.empresa.asdtests

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FragmentPreguntaDetalleResponder : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val fragmento =  inflater.inflate(R.layout.fragment_pregunta_detalle_responder, container, false)

        //contexto de la aplicacion}
        val context = activity?.applicationContext

        val idPregunta =  requireArguments().getInt("idPregunta")

        Log.e("FG", "se recibió el id:"+idPregunta)
        Toast.makeText(activity, "aca ok", Toast.LENGTH_SHORT).show()
        verPregunta( fragmento, idPregunta )

        fragmento.findViewById<ImageButton>( R.id.btnCancelar).setOnClickListener {
            salir()
        }

        fragmento.findViewById<CheckBox>( R.id.chkEditar).setOnClickListener {
            activarActualizar(fragmento, fragmento.findViewById<CheckBox>( R.id.chkEditar).isChecked )
        }

        fragmento.findViewById<ImageButton>( R.id.btnActualizar).setOnClickListener {
            actualizarPregunta(fragmento, idPregunta)
        }

        fragmento.findViewById<ImageButton>( R.id.btnEliminar).setOnClickListener {
            eliminarPregunta(idPregunta)
        }

        return fragmento

    }

//funciones
private fun eliminarPregunta(idPregunta: Int) {
//    CoroutineScope( Dispatchers.IO ).launch {
//        val database = context?.let { ASDTestsDB.getDataBase(it) }
//        val pelicula = Pregunta(idPregunta, "", "", "", "")
//        database?.preguntaDAO()?.eliminar(pelicula)
//    }
//    salir()
}

    private fun actualizarPregunta(fragmento: View, idPregunta: Int) {
//        CoroutineScope( Dispatchers.IO).launch {
//            val database = context?.let { ASDTestsDB.getDataBase(it)}
//
//            val pelicula = Pregunta(
//                idPregunta,
//                fragmento.findViewById<EditText>(R.id.edtArea).text.toString(),
//                fragmento.findViewById<EditText>(R.id.edtPreguntaTexto).text.toString(),
//                fragmento.findViewById<EditText>(R.id.edtOpcion1).text.toString(),
//                fragmento.findViewById<EditText>(R.id.edtRespuesta).text.toString()
//            )
//            database?.preguntaDAO()?.actualizar(pelicula)
//        }
//        activarActualizar(fragmento, false)
//        fragmento.findViewById<CheckBox>( R.id.chkEditar).isChecked = false
//        fragmento.findViewById<ImageButton>(R.id.btnActualizar).visibility = View.GONE
    }

    private fun verPregunta( fragmento: View, idPregunta: Int) {
//        var  pregunta: Pregunta = Pregunta( 0, "", "" , "", "")
//
//        CoroutineScope( Dispatchers.IO ).launch {
//            //obtener la instancia de la BDs
//            val database = context?.let { ASDTestsDB.getDataBase(it) }
//
//            //consultamos la pregunta x ID en la BDs
//            pregunta = database?.preguntaDAO()?.getPregunta(idPregunta)!!
//
//            val edtArea = fragmento.findViewById<EditText>( R.id.edtArea)
//            val edtPreguntaTexto = fragmento.findViewById<EditText>( R.id.edtPreguntaTexto)
//            val edtOpcion1 = fragmento.findViewById<EditText>( R.id.edtOpcion1)
//            val edtRespuesta = fragmento.findViewById<EditText>( R.id.edtRespuesta)
//            edtArea.setText( pregunta.area )
//            edtPreguntaTexto.setText( pregunta.pretexto )
//            edtOpcion1.setText( pregunta.opcion1 )
//            edtRespuesta.setText( pregunta.respuesta )
//        }
    }

    private fun activarActualizar(fragmento: View, activo: Boolean ){
        fragmento.findViewById<EditText>( R.id.edtArea).setEnabled( activo )
        fragmento.findViewById<EditText>( R.id.edtPreguntaTexto).setEnabled( activo )
        fragmento.findViewById<EditText>( R.id.edtOpcion1).setEnabled( activo )
        fragmento.findViewById<EditText>( R.id.edtRespuesta).setEnabled( activo )

        fragmento.findViewById<ImageButton>(R.id.btnActualizar).visibility = View.VISIBLE
    }

    private fun salir(){
        val lvPreguntas = activity?.findViewById<ListView>( R.id.lvPreguntas )
        lvPreguntas?.visibility = View.VISIBLE

        activity?.supportFragmentManager?.beginTransaction()
            ?.remove( this )
            ?.commit()
    }
//fin funciones    
    
    
    
    
    
    
    
    
    
}