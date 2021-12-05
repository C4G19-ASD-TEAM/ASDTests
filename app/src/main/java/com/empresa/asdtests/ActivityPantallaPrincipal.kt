package com.empresa.asdtests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.empresa.asdtests.databinding.ActivityPantallaPrincipalBinding
import com.empresa.asdtests.model.Pregunta
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.android.synthetic.main.activity_pantalla_principal.*
import kotlinx.android.synthetic.main.fragment_user_preguntas.*

class ActivityPantallaPrincipal : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityPantallaPrincipalBinding

    val database = Firebase.database
    val dbReferencePreguntas = database.getReference("preguntas")

    private lateinit var userId: String

    private lateinit var listaPreguntas: ArrayList<Pregunta>
    private lateinit var preguntaAdapter : ArrayAdapter<Pregunta>


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //soportar la barra de menu toolbar
        setSupportActionBar(toolbarMyToolbar)

        binding = ActivityPantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Firebase.initialize(this)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        userId = currentUser?.uid.toString()

        listaPreguntas = ArrayList<Pregunta>()


        //segun el rol que muestra

        var role = intent.getStringExtra("role")

        Toast.makeText(this, "aca el role es: "+role, Toast.LENGTH_SHORT).show()

        tvUser.setText(role).toString()

        if(role.equals("Admin")){
            verListadoPreguntas()
        }else{
            binding.btnAgregarPregunta.visibility = View.GONE

        }


        binding.btnGenerarTest.setOnClickListener {
            verTest()
        }


        binding.lvPreguntas.setOnItemClickListener { parent, view, position, id ->
            var pregunta = listaPreguntas[position]


            val args = Bundle ()
            args.putString("id", pregunta.id)
            args.putString("area", pregunta.area)
            args.putString("pretexto", pregunta.pretexto)
            args.putString("opcion1", pregunta.opcion1)
            args.putString("respuesta", pregunta.respuesta)


            Toast.makeText(this, "${pregunta}", Toast.LENGTH_SHORT ).show()

            lvPreguntas.visibility = View.GONE
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace( R.id.fragmentContainerPantallaPrincipal, FragmentEditarPregunta::class.java, args, "Preguntas" )
                .commit()


        }




        btnAgregarPregunta.setOnClickListener {
            //Se oculta el listado de Peliculas para mostrar la ventana de adicionar Pelicula
            lvPreguntas.visibility = View.GONE

            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace( R.id.fragmentContainerPantallaPrincipal, FragmentUserPreguntas::class.java, null, "Preguntas" )
                .commit()
        }




        btnCerrarSesion.setOnClickListener {

            cerrarSesion()

        }


    }

    private fun verTest() {

        val intent = Intent(this, ActivityRealizarTest::class.java)

        Log.e("FG", "El userId es: " + userId )

        intent.putExtra("userId", userId)

        this.startActivity(intent)

    }

    private fun verListadoPreguntas() {
        val preguntaItemListener = object : ValueEventListener{
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

                    preguntaAdapter = PreguntasAdapter(this@ActivityPantallaPrincipal, listaPreguntas)
                    binding.lvPreguntas.adapter = preguntaAdapter


                }




            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        dbReferencePreguntas.addValueEventListener(preguntaItemListener)



    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.mnCerrarSesion -> {
            Toast.makeText(this, "Cerrar", Toast.LENGTH_SHORT).show()
            cerrarSesion()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

    fun cerrarSesion(){
        auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }





}