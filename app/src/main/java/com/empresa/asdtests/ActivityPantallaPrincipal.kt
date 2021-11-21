package com.empresa.asdtests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActivityPantallaPrincipal : AppCompatActivity() {

    private lateinit var listRecyclerView: RecyclerView
    private lateinit var categoriasAdapter: RecyclerView.Adapter<CategoriasAdapter.ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)

        //recibir y mostrar datos que llegan en el intent desde el fragment
        val tvUser = findViewById<TextView>(R.id.tvUser)
        val recibeParametrosIntent: Intent = intent
        var user: String? = recibeParametrosIntent.getStringExtra("user")
        var isAdmin: Boolean? = recibeParametrosIntent.getBooleanExtra("isAdmin",false)
        tvUser.text = user


        //si es usuario mostramos fragment de usuario o si es admin mostramos el fragment de admin

        if(isAdmin == false){
            //mostrar el segundofragmento
            this?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(
                    R.id.fragmentContainerPantallaPrincipal,
                    FragmentUserPreguntas::class.java,
                    null,
                    "User Access Preguntas"
                )
                ?.addToBackStack("")
                ?.commit()
        }else{
            //mostrar el fragmento de administrador
            this?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(
                    R.id.fragmentContainerPantallaPrincipal,
                    FragmentAdminMenu::class.java,
                    null,
                    "Admin Access Preguntas"
                )
                ?.addToBackStack("")
                ?.commit()

        }







        //datos que va a mostrar
        val listaCategorias: ArrayList<Categoria> = ArrayList()
        listaCategorias.add(Categoria(categoriaNombre = "Matematicas", categoriaDescripcion = "categoría de preguntas del tema de Matematicas", categoriaNumeroPreguntas = 10))
        listaCategorias.add(Categoria(categoriaNombre = "Sociales", categoriaDescripcion = "categoría de preguntas del tema de Sociales", categoriaNumeroPreguntas = 10))
        listaCategorias.add(Categoria(categoriaNombre = "Lectura", categoriaDescripcion = "categoría de preguntas del tema de Lectura", categoriaNumeroPreguntas = 10))
        listaCategorias.add(Categoria(categoriaNombre = "Ingles", categoriaDescripcion = "categoría de preguntas del tema de Ingles", categoriaNumeroPreguntas = 10))
        listaCategorias.add(Categoria(categoriaNombre = "Ciencias Naturales", categoriaDescripcion = "categoría de preguntas del tema de Ciencias Naturales", categoriaNumeroPreguntas = 10))

/*
        val listaCategorias: ArrayList<String> = ArrayList()
        listaCategorias.add("test")
*/



        //mostramos Recycler View
        val rvCategorias = findViewById<RecyclerView>(R.id.recyclerCategorias)
        rvCategorias.layoutManager = LinearLayoutManager(this)
        val adapterCategorias = CategoriasAdapter(listaCategorias)
        rvCategorias.adapter = adapterCategorias







    }
}