package com.empresa.asdtests


import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.empresa.asdtests.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_main)
        setContentView(binding.root)


        auth = Firebase.auth

        val currentUser = auth.currentUser
        if(currentUser != null){
            verActivityUsuarioLogueado();
        }else{


            //adicionar el fragment CreateAccount
/*            if (savedInstanceState == null) { //guardar el estado de la actividad que se está mostrando
                supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerMain, CreateAccount::class.java, null, "Create Account")
                    .commit()
            }*/

            binding.btnSignIn.setOnClickListener {

                login(binding.etUsername.text.toString(), binding.etPassword.text.toString())

            }







        }



    }

    private fun login(email: String, password: String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LOG TAG", "signInWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user)

                    verActivityUsuarioLogueado()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("LOG TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }



    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            verActivityUsuarioLogueado();
        }
    }




    private fun verActivityUsuarioLogueado() {
        val intent = Intent(this, ActivityPantallaPrincipal::class.java)
        this.startActivity(intent)
    }


}