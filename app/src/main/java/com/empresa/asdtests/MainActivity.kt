package com.empresa.asdtests


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private var listaUsuarios: MutableList<Usuario> = mutableListOf(
        Usuario( "Juan", "123", "test@test.com"),
        Usuario( "Luis", "123", "test2@test2.com")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //adicionar
        if(savedInstanceState == null) { //guardar el estado de la actividad que se está mostrando
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainerMain, CreateAccount::class.java, null, "Create Account")
                .commit()
        }




}