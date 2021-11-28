package com.empresa.asdtests

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.empresa.asdtests.databinding.FragmentUserPreguntasBinding
import com.empresa.asdtests.model.Pregunta
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import java.util.*


class FragmentUserPreguntas : Fragment() {

    private var _binding: FragmentUserPreguntasBinding? = null
    private val binding get() = _binding!!

    val database = Firebase.database
    val dbReferencePreguntas = database.getReference("preguntas")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        _binding = FragmentUserPreguntasBinding.inflate(inflater, container, false)

        //se inicializa (si estamos en una actividad el contexto es this
        Firebase.initialize(requireActivity())

        binding.btnGuardarPregunta.setOnClickListener {
            //val context = activity?.applicationContext

            var pregunta = Pregunta (

                UUID.randomUUID().toString(),
                binding.etArea.text.toString(),
                binding.etPreText.text.toString(),
                binding.etPreOpcion1.text.toString(),
                binding.etPreRespuesta.text.toString()

            )




            val dialogBuilder = AlertDialog.Builder(requireActivity())

                .setPositiveButton("Ok", DialogInterface.OnClickListener {
                        dialog, id ->

                    dbReferencePreguntas.child(pregunta.id).setValue(pregunta)

                    Toast.makeText(requireActivity(), "Pregunta agregada con éxito", Toast.LENGTH_SHORT).show()
                    limpiarForm()
                    dialog.dismiss()

                })

                .setNegativeButton("Cancel", { dialog, whichButton ->

                })

            val alert = dialogBuilder.create()
            alert.setTitle("Test")
            alert.setMessage("Estas seguro de agregar esta pregunta?")
            alert.setCancelable(false)

            alert.show()






        }


        binding.btnCancelarGuardarPregunta.setOnClickListener {
            //salir()
        }

        return binding.root

    }

    private fun limpiarForm() {
        binding.etArea.setText("")
        binding.etPreText.setText("")
        binding.etPreRespuesta.setText("")
        binding.etPreOpcion1.setText("")

    }

//    private fun salir(){
//        val lvPreguntas = activity?.findViewById<ListView>( R.id.lvPreguntas )
//        lvPreguntas?.visibility = View.VISIBLE
//
//        activity?.supportFragmentManager?.beginTransaction()
//            ?.remove( this )
//            ?.commit()
//    }


}