package com.empresa.asdtests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.empresa.asdtests.databinding.ActivityRealizarTestBinding
import com.empresa.asdtests.model.Pregunta
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.android.synthetic.main.activity_pantalla_principal.*
import kotlinx.android.synthetic.main.activity_realizar_test.*

class ActivityRealizarTest : AppCompatActivity() {

    private lateinit var binding : ActivityRealizarTestBinding
    private lateinit var listaPreguntas: ArrayList<Pregunta>
    private lateinit var preguntaAdapter : ArrayAdapter<Pregunta>

    val database = Firebase.database
    val dbReferencePreguntas = database.getReference("preguntas")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_realizar_test)

        binding = ActivityRealizarTestBinding.inflate(layoutInflater)
        setContentView(binding.root)


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


                    listaPreguntas.add(pregunta)


                    //linkear adapter

                    preguntaAdapter = TestPreguntasAdapter(this@ActivityRealizarTest, listaPreguntas)
                    binding.lvPreguntasTest.adapter = preguntaAdapter


                }




            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        dbReferencePreguntas.addValueEventListener(preguntaItemListener)



    }




}