package com.empresa.asdtests


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.empresa.asdtests.database.ASDTestsDB
import com.empresa.asdtests.model.Usuario
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




//obtener el List View para mostrar las peliculas
        var listaUsuarios = emptyList<Usuario>()

        //conectamos a la BDs
        val database = ASDTestsDB.getDataBase( this )

        //Consulta las peliculas que estan almacenadas en la BDs
        database.usuarioDAO().getAll().observe( this, Observer {
            listaUsuarios = it

            val adapter = UsuarioAdapter( this, listaUsuarios )

            //mostrarlo en el ListView de Peliculas
            lvUsuarios.adapter = adapter
        })



        //adicionar
        if (savedInstanceState == null) { //guardar el estado de la actividad que se está mostrando
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainerMain, CreateAccount::class.java, null, "Create Account")
                .commit()
        }


    }
}