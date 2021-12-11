package com.empresa.asdtests

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.empresa.asdtests.databinding.FragmentResponderPreguntaBinding
import com.empresa.asdtests.model.Pregunta
import com.empresa.asdtests.model.Test
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.android.synthetic.main.fragment_editar_pregunta.*
import kotlinx.android.synthetic.main.fragment_responder_pregunta.*
import java.util.*


class FragmentResponderPregunta : Fragment() {

    private var _binding: FragmentResponderPreguntaBinding? = null
    private val binding get() = _binding!!

    val database = Firebase.database
    val dbReferencePreguntasTest = database.getReference("tests")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentResponderPreguntaBinding.inflate(inflater, container, false)

        //se inicializa (si estamos en una actividad el contexto es this
        Firebase.initialize(requireActivity())

//        args.putString("testUniqueId", test.id)
//        args.putString("testId", test.testId)
//        args.putString("userId", test.userId)
//        args.putString("preguntaId", test.preguntaId)
//        args.putString("preguntaTexto", test.preguntaTexto)
//
//        args.putString("preguntaOpcion1", pregunta.opcion1)
//        args.putString("preguntaRespuesta", pregunta.respuesta)


        val testUniqueId =  requireArguments().getString("testUniqueId")
        val testId =  requireArguments().getString("testId")
        val userId =  requireArguments().getString("userId")
        val preguntaId =  requireArguments().getString("preguntaId")
        val preguntaArea =  requireArguments().getString("preguntaArea")
        val preguntaTexto =  requireArguments().getString("preguntaTexto")
        val preguntaOpcion1 =  requireArguments().getString("preguntaOpcion1")
        val preguntaRespuesta =  requireArguments().getString("preguntaRespuesta")



        binding.edtTestUniqueId.setText(testUniqueId)
        binding.edtTestId.setText(testId)
        binding.edtUserId.setText(userId)
        binding.edtPreguntaId.setText(preguntaId)
        binding.tvArea.setText(preguntaArea)
        binding.edtTestPreguntaTexto.setText(preguntaTexto)
        binding.tvOpcion1.setText(preguntaOpcion1)
        binding.tvRespuesta.setText(preguntaRespuesta)


        binding.btnEnviar.setOnClickListener {
            responderPregunta()

        }


        binding.chk1.setOnClickListener {
            if(chk1.isChecked) {
                chk2.setEnabled(false)
            }else
            {
                chk2.setEnabled(true)
            }
        }

        binding.chk2.setOnClickListener {
            if(chk2.isChecked) {
                chk1.setEnabled(false)
            }else
            {
                chk1.setEnabled(true)
            }
        }


        binding.btnCancelar.setOnClickListener {
            salir()
        }


        return binding.root


    }

//funciones
        private fun responderPregunta() {

            var respuestaCorrecta = 0
            if((chk1.isChecked and chk2.isChecked) or (!chk1.isChecked and !chk2.isChecked)){

                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                chk1.setError("Error debes escoger una y solo una respuesta")
                chk2.setError("Error debes escoger una y solo una respuesta")

            }else {

                if (chk1.isChecked) {
                    Log.e("FG", "la respuesta es INcorrecta")
                    //salir()
                }

                if (chk2.isChecked) {
                    Log.e("FG", "la respuesta es correcta")
                    respuestaCorrecta = 1
                //salir()
                }


                //actualizar test
                var test = Test(
                edtTestUniqueId.text.toString(),
                edtTestId.text.toString(),
                edtUserId.text.toString(),
                edtPreguntaId.text.toString(),
                edtTestPreguntaTexto.text.toString(),
                respuestaCorrecta
                )

                dbReferencePreguntasTest.child(test.id).setValue(test)

                Log.e("FG", "actualizar test " + test)
                salir()


            }


    }





    private fun salir(){
        val lvPreguntasTest = activity?.findViewById<ListView>( R.id.lvPreguntasTest )
        lvPreguntasTest?.visibility = View.VISIBLE

        activity?.supportFragmentManager?.beginTransaction()
            ?.remove( this )
            ?.commit()
    }
//fin funciones









}