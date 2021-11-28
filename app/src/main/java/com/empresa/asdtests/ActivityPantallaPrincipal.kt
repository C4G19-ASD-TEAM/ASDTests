package com.empresa.asdtests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.android.synthetic.main.activity_pantalla_principal.*
import kotlinx.android.synthetic.main.fragment_user_preguntas.*

class ActivityPantallaPrincipal : AppCompatActivity() {

//    private lateinit var listRecyclerView: RecyclerView
//    private lateinit var categoriasAdapter: RecyclerView.Adapter<CategoriasAdapter.ViewHolder>
    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityPantallaPrincipalBinding

    val database = Firebase.database
    val dbReferencePreguntas = database.getReference("preguntas")


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


        listaPreguntas = ArrayList<Pregunta>()



        //recibir y mostrar datos que llegan en el intent desde el fragment
        val tvUser = findViewById<TextView>(R.id.tvUser)
        val recibeParametrosIntent: Intent = intent
        var user: String? = recibeParametrosIntent.getStringExtra("user")
        var isAdmin: Boolean? = recibeParametrosIntent.getBooleanExtra("isAdmin",false)
        tvUser.text = user

/////////////////////////////////DATABASE//////////////////////////////


/*
        var pregunta: Pregunta = Pregunta(0,"aa", "bb", "cc", "dd")
        var listaPreguntas: ArrayList<Pregunta> = ArrayList()
        listaPreguntas.add(pregunta)
        val adapter = PreguntasAdapter(this, listaPreguntas)
        //mostrar en el listview
        lvPreguntas.adapter = adapter
*/




        btnAgregarPregunta.setOnClickListener {
            //Se oculta el listado de Peliculas para mostrar la ventana de adicionar Pelicula
            lvPreguntas.visibility = View.GONE

            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace( R.id.fragmentContainerPantallaPrincipal, FragmentUserPreguntas::class.java, null, "Preguntas" )
                .commit()
        }






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