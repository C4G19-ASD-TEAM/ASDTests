package com.empresa.asdtests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.empresa.asdtests.databinding.ActivityRealizarTestBinding
import com.empresa.asdtests.model.Pregunta
import com.empresa.asdtests.model.Test
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.android.synthetic.main.activity_pantalla_principal.*
import kotlinx.android.synthetic.main.activity_realizar_test.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.random.Random

class ActivityRealizarTest : AppCompatActivity() {

    private lateinit var binding : ActivityRealizarTestBinding
    private lateinit var listaPreguntas: ArrayList<Pregunta>
    private lateinit var listaPreguntasTest: ArrayList<Pregunta>
    private lateinit var preguntaAdapter : ArrayAdapter<Pregunta>
    private lateinit var userId: String


    val database = Firebase.database
    val dbReferencePreguntas = database.getReference("preguntas")
    val dbReferencePreguntasTest = database.getReference("tests")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_realizar_test)

        binding = ActivityRealizarTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = intent.getStringExtra("userId").toString()

        Log.e("FG", "User id recibido en el activity Realizar Test: " + userId)

        Firebase.initialize(this)

        listaPreguntas = ArrayList<Pregunta>()

        verListadoPreguntas()

        binding.lvPreguntasTest.setOnItemClickListener { parent, view, position, id ->
            var pregunta = listaPreguntas[position]


            val args = Bundle ()
            args.putString("id", pregunta.id)
            args.putString("area", pregunta.area)
            args.putString("pretexto", pregunta.pretexto)
            args.putString("opcion1", pregunta.opcion1)
            args.putString("respuesta", pregunta.respuesta)


            Toast.makeText(this, "${pregunta}", Toast.LENGTH_SHORT ).show()

            lvPreguntasTest.visibility = View.GONE


        }




    }

    //funciones

    private fun verListadoPreguntas() {
        val preguntaItemListener = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {

                for (preg in datasnapshot.children){
                    var pregunta = Pregunta( "", "", "", "", "")

                    //objeto MAP
                    val mapPregunta : Map<String, Any> = preg.value as HashMap<String, Any>

                    pregunta.id = mapPregunta.get("id").toString()
                    pregunta.area = mapPregunta.get("area").toString()
                    pregunta.pretexto = mapPregunta.get("pretexto").toString()
                    pregunta.opcion1 = mapPregunta.get("opcion1").toString()
                    pregunta.respuesta = mapPregunta.get("respuesta").toString()
                    //agregamos a la lista
                    listaPreguntas.add(pregunta)

                }


                listaPreguntasTest = generarTest ( listaPreguntas, userId )





                Log.e("FG", "Lista de preguntas: " + listaPreguntasTest)


                //linkear adapter
                preguntaAdapter = TestPreguntasAdapter(this@ActivityRealizarTest, listaPreguntasTest)
                binding.lvPreguntasTest.adapter = preguntaAdapter

                //cojemos 5 items aleatorios de la lista de preguntas y llevamos a lista preguntas test





            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        dbReferencePreguntas.addValueEventListener(preguntaItemListener)



    }



    fun generarTest(lista: ArrayList<Pregunta>, userId: String): ArrayList<Pregunta> {

        var listaPreguntasTests: ArrayList<Pregunta>
        listaPreguntasTests = ArrayList<Pregunta>()

//        val list = listOf("one", "two", "three", "four", "five")
          val numberOfElements = 2
//
//        val randomElements = list.asSequence().shuffled().take(numberOfElements).toList()

        var i: Int = 0
        while (i < 2 ){

            val randomIndex = Random.nextInt(lista.size)
            Log.e("FG", "valor random: " + lista[randomIndex].pretexto)

            if(lista[randomIndex] !in listaPreguntasTests) {
                listaPreguntasTests.add(lista[randomIndex])
                i++
            }else{
                Log.e("FG", "La pregunta ya estaba repetida")
            }

        }


        //recorremos la lista  y la vamos insertando en la base de datos



        for(preguntaTest in listaPreguntasTests) {
            var test = Test (

                UUID.randomUUID().toString(),
                userId,
                preguntaTest.id,
                "Campo pendiente para cuando el usuario responda"
            )

            dbReferencePreguntasTest.child(test.id).setValue(test)
        }


        return listaPreguntasTests

    }




}