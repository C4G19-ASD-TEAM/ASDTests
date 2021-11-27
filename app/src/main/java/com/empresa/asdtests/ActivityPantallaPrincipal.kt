package com.empresa.asdtests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.empresa.asdtests.database.ASDTestsDB
import com.empresa.asdtests.model.Pregunta
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_pantalla_principal.*
import kotlinx.android.synthetic.main.fragment_user_preguntas.*

class ActivityPantallaPrincipal : AppCompatActivity() {

    private lateinit var listRecyclerView: RecyclerView
    private lateinit var categoriasAdapter: RecyclerView.Adapter<CategoriasAdapter.ViewHolder>
    private lateinit var auth: FirebaseAuth




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)

        setSupportActionBar(toolbarMyToolbar)

        auth = Firebase.auth



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

        //obtener la lista de las preguntas
        var listaPreguntas = emptyList<Pregunta>()

        //conectamos a la DB
        val database = ASDTestsDB.getDataBase(this)

        //consultar la info desde la db
        database.preguntaDAO().getAll().observe(this, Observer {
            listaPreguntas = it

            val adapter = PreguntasAdapter(this, listaPreguntas)

            //mostrar en el listview
            lvPreguntas.adapter = adapter

        })


        btnAgregarPregunta.setOnClickListener {
            //Se oculta el listado de Peliculas para mostrar la ventana de adicionar Pelicula
            lvPreguntas.visibility = View.GONE

            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace( R.id.fragmentContainerPantallaPrincipal, FragmentUserPreguntas::class.java, null, "Preguntas" )
                .commit()
        }






        //Seleccionar un Item de las peliculas para ver el detalle en el Fragmento DetallePelicula}
        lvPreguntas.setOnItemClickListener { parent, view, position, id ->

            //recopilamos la información de la pelicula para enviarla al detallePelicula
            val pregunta = Bundle()
            pregunta.putInt( "idPregunta", listaPreguntas[position].id )

            lvPreguntas.visibility = View.GONE

            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace( R.id.fragmentContainerPantallaPrincipal, FragmentPreguntaDetalleResponder::class.java, pregunta, "detallePelicula")
                .commit()

            Toast.makeText(this, "Mensaje " , Toast.LENGTH_SHORT).show()
        }





///////////////////////////////END DATABASE////////////////////////////

//
//
//        //si es usuario mostramos fragment de usuario o si es admin mostramos el fragment de admin
//
//        if(isAdmin == false){
//            //mostrar el segundofragmento
//            this?.getSupportFragmentManager()?.beginTransaction()
//                ?.setReorderingAllowed(true)
//                ?.replace(
//                    R.id.fragmentContainerPantallaPrincipal,
//                    FragmentUserPreguntas::class.java,
//                    null,
//                    "User Access Preguntas"
//                )
//                ?.addToBackStack("")
//                ?.commit()
//        }else{
//            //mostrar el fragmento de administrador
//            this?.getSupportFragmentManager()?.beginTransaction()
//                ?.setReorderingAllowed(true)
//                ?.replace(
//                    R.id.fragmentContainerPantallaPrincipal,
//                    FragmentAdminMenu::class.java,
//                    null,
//                    "Admin Access Preguntas"
//                )
//                ?.addToBackStack("")
//                ?.commit()
//
//        }
//
//





        //datos que va a mostrar
        val listaCategorias: ArrayList<Categoria> = ArrayList()
        listaCategorias.add(Categoria(categoriaNombre = "Matematicas", categoriaDescripcion = "categoría de preguntas del tema de Matematicas", categoriaNumeroPreguntas = 10))
        listaCategorias.add(Categoria(categoriaNombre = "Sociales", categoriaDescripcion = "categoría de preguntas del tema de Sociales", categoriaNumeroPreguntas = 10))
        listaCategorias.add(Categoria(categoriaNombre = "Lectura", categoriaDescripcion = "categoría de preguntas del tema de Lectura", categoriaNumeroPreguntas = 10))
        listaCategorias.add(Categoria(categoriaNombre = "Ingles", categoriaDescripcion = "categoría de preguntas del tema de Ingles", categoriaNumeroPreguntas = 10))
        listaCategorias.add(Categoria(categoriaNombre = "Ciencias Naturales", categoriaDescripcion = "categoría de preguntas del tema de Ciencias Naturales", categoriaNumeroPreguntas = 10))





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