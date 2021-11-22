package com.empresa.asdtests


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //adicionar el fragment CreateAccount
        if (savedInstanceState == null) { //guardar el estado de la actividad que se está mostrando
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainerMain, CreateAccount::class.java, null, "Create Account")
                .commit()
        }


    }
}