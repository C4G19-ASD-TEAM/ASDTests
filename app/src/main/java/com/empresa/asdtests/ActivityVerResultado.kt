package com.empresa.asdtests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.empresa.asdtests.databinding.ActivityVerResultadoBinding
import com.empresa.asdtests.model.Pregunta
import com.empresa.asdtests.model.Test
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_ver_resultado.*


class ActivityVerResultado : AppCompatActivity() {

    private lateinit var binding : ActivityVerResultadoBinding

    private lateinit var listTestActual: ArrayList<Test>
    private lateinit var listTestsAcumuladoUsuario: ArrayList<Test>

    val database = Firebase.database
    val dbReferencePreguntasTest = database.getReference("tests")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVerResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var testId: String
        testId = ""

        var userId: String
        userId = ""

        testId = intent.getStringExtra("testId").toString()
        userId = intent.getStringExtra("userId").toString()

        binding.tvIdTest.text = testId
        binding.tvUserId.text = userId

        listTestActual = ArrayList()

        verTestActual(testId)

        listTestsAcumuladoUsuario = ArrayList()
        verTestsUsuarioAcumulado(userId)



    }//fin oncreate


    ///////////////////////////////

    private fun verTestActual(testId: String) {
        var correctas = 0
        var totalPreguntas = 0
        var resultado = 0.0
        val testItemListener = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {

                for (dstest in datasnapshot.children){
                    var test = Test( "", "", "", "", "", 0)

                    //objeto MAP
                    val mapTest : Map<String, Any> = dstest.value as HashMap<String, Any>

                    test.id = mapTest.get("id").toString()
                    test.userId = mapTest.get("userId").toString()
                    test.testId = mapTest.get("testId").toString()
                    test.preguntaId = mapTest.get("preguntaId").toString()
                    test.preguntaTexto = mapTest.get("preguntaTexto").toString()
                    test.respuesta = mapTest.get("respuesta").toString().toInt()

                    //agregamos a la lista global de preguntas
                    listTestActual.add(test)
//                    Log.e("FG","test actual: " + test)

                    totalPreguntas++
                    correctas = correctas + test.respuesta

                }

                resultado = (correctas/totalPreguntas).toDouble()
                edtResultadoActual.setText(correctas.toString() + " / " + totalPreguntas.toString())




            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        val dbReferenceFiltrado = database.getReference("tests").orderByChild("testId").equalTo(testId)
        dbReferenceFiltrado.addValueEventListener(testItemListener)



    }




    private fun verTestsUsuarioAcumulado(userId: String) {

        var cantPreguntas = 0
        var cantPreguntasCorrectas = 0
        var resultado = 0.0

        val testItemListener = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {


                for (dstest in datasnapshot.children){

                    var test = Test( "", "", "", "", "", 0)

                    //objeto MAP
                    val mapTest : Map<String, Any> = dstest.value as HashMap<String, Any>

                    test.id = mapTest.get("id").toString()
                    test.userId = mapTest.get("userId").toString()
                    test.testId = mapTest.get("testId").toString()
                    test.preguntaId = mapTest.get("preguntaId").toString()
                    test.preguntaTexto = mapTest.get("preguntaTexto").toString()
                    test.respuesta = mapTest.get("respuesta").toString().toInt()

                    //agregamos a la lista global de preguntas
                    listTestsAcumuladoUsuario.add(test)

                    //Log.e("FG","test acumulado: " + test)


                    cantPreguntas++
                    cantPreguntasCorrectas = cantPreguntasCorrectas + test.respuesta

                }

                edtCantidadTestsRealizados.setText(listTestsAcumuladoUsuario.size.toString())
                edtCantidadTotalPreguntas.setText(cantPreguntas.toString())
                edtCantidadTotalPreguntasCorrectas.setText(cantPreguntasCorrectas.toString())

                resultado = (cantPreguntasCorrectas/cantPreguntas).toDouble()
                edtResultadoAcumulado.setText(resultado.toString())




            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        val dbReferenceFiltrado = database.getReference("tests").orderByChild("userId").equalTo(userId)
        dbReferenceFiltrado.addValueEventListener(testItemListener)


        var cantidadTests = listTestsAcumuladoUsuario.size
        binding.edtCantidadTestsRealizados.setText(cantidadTests.toString())


    }



    ////////////////////////////////




}