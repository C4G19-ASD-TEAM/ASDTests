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



//        val btnNuevoUsuario = findViewById<Button>(R.id.btnCrear)
//
//        btnNuevoUsuario.setOnClickListener {
//            val edtNombre = findViewById<EditText>(R.id.etNombre)
//
//            val edtEmail = findViewById<EditText>(R.id.etEmail)
//            val edtClave = findViewById<EditText>(R.id.etClave)
//
//            if (validarNombre(edtNombre.text.toString())) {
//                listaUsuarios.add(Usuario(edtNombre.text.toString(), edtClave.text.toString(), edtEmail.toString() ))
//                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(this, "Nombre no cumple", Toast.LENGTH_SHORT).show()
//            }
//
//
//            if (validarContraseña(edtClave.text.toString())) {
//                listaUsuarios.add(Usuario(edtNombre.text.toString(), edtClave.text.toString(), edtEmail.toString()))
//                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(this, "Contraseña no cumple", Toast.LENGTH_SHORT).show()
//            }
//            println(listaUsuarios)
//        }
//
//        Log.e("FG", "aca ok" )

    }


/*
    private fun validarNombre (nombre: String): Boolean {
        var pattern: Pattern
        var matcher: Matcher

        Log.e("ERRORNOMBRE", "ERROR: "+nombre)

        val PATRON_NOMBRE = "^[a-zA-Z]*\$"
        pattern = Pattern.compile(PATRON_NOMBRE)
        matcher = pattern.matcher(nombre)

        return matcher.matches();
    }
*/




/*
    private fun validarContraseña (pass: String): Boolean {
        var pattern: Pattern
        var matcher: Matcher

        val PATRON_CONTRASEÑA = ".*[!@#$%^&*+=?-].*"
        pattern = Pattern.compile(PATRON_CONTRASEÑA)
        matcher = pattern.matcher(pass)

        return matcher.matches();
    }
*/


}