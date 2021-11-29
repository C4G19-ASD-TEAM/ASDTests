package com.empresa.asdtests


import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.empresa.asdtests.databinding.ActivityMainBinding
import com.empresa.asdtests.model.Pregunta
import com.empresa.asdtests.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import java.util.regex.Matcher
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    //binding
    private lateinit var binding : ActivityMainBinding

    //firebase auth
    private lateinit var auth: FirebaseAuth

    //firebase database firestore
    val database = Firebase.database
    val dbReferenceUsuarios = database.getReference("usuarios")

    //otras variables
    //private lateinit var listaUsuarios: ArrayList<Usuario>
    private lateinit var role: String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        //inicializacion firebase
        Firebase.initialize(this)
        auth = Firebase.auth

        //listaUsuarios = ArrayList<Usuario>()

        role = "User"

        val currentUser = auth.currentUser
        if(currentUser != null){

            //var role = roleUsuarioLogueado(currentUser?.uid.toString())
            if(currentUser?.uid.toString().equals("JNnBxYGYNuWjrNy28iljdXJArdr2")) {
                role = "Admin"
            }

            verActivityUsuarioLogueado(role);
        }else{


            binding.btnSignIn.setOnClickListener {

                if(binding.etUsername.text.toString().length < 1 ){
                    binding.etUsername.setError("Este campo no puede ser vácio")
                }
                if(binding.etPassword.text.toString().length < 1){
                    binding.etPassword.setError("Este campo no puede ser vácio")
                }

                if(validarCampo(binding.etUsername, "email")) {
                    login(binding.etUsername.text.toString(), binding.etPassword.text.toString())
                }else{
                    binding.etUsername.setError("No has ingresado un correo válido")
                }


            }


            binding.btnCreateAccount.setOnClickListener {

                verActivityCreateAccount()

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

                    Log.e("FB", "user ID: " + user?.uid.toString())


                    //var role = roleUsuarioLogueado(user?.uid.toString())

                    Log.e("FB", "usuario: "+role)


                    if(user?.uid.toString().equals("JNnBxYGYNuWjrNy28iljdXJArdr2")) {
                        role = "Admin"
                    }
                    verActivityUsuarioLogueado(role)

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
            //var role = roleUsuarioLogueado(currentUser?.uid.toString())
            if(currentUser?.uid.toString().equals("JNnBxYGYNuWjrNy28iljdXJArdr2")) {
                role = "Admin"
            }
            verActivityUsuarioLogueado(role);
        }
    }




    private fun verActivityUsuarioLogueado(role: String) {

        val intent = Intent(this, ActivityPantallaPrincipal::class.java)
        intent.putExtra("role", role)
        this.startActivity(intent)
    }

    private fun verActivityCreateAccount(){
        val intent = Intent(this, ActivityCreateAccount::class.java)
        this.startActivity(intent)
    }


    private fun validarCampo (campo: EditText, tipo: String): Boolean {
        var pattern: Pattern
        var matcher: Matcher
        var patron: String
        when (tipo){
            "nombre" -> patron = "^[a-zA-Z]{3,}$"
            "email" -> patron = Patterns.EMAIL_ADDRESS.toString()
            "clave" -> patron = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&.])[A-Za-z\\d@$!%*#?&.]{8,}$"
            "telefono" -> patron = "^[0-9]{7,15}$"
            else -> patron = "^{1,}$"
        }

        pattern = Pattern.compile(patron)

        matcher = pattern.matcher(campo.text.toString())
        return matcher.matches();
    }





    private fun roleUsuarioLogueado(uid: String):String {

        val usuarioItemListener = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {

                for (usr in datasnapshot.children){
                    var usuario = Usuario( "", "", "", "", "", "")

                    //objeto MAP
                    val mapUsuario : Map<String, Any> = usr.value as HashMap<String, Any>

                    usuario.id = mapUsuario.get("id").toString()
                    usuario.nombre = mapUsuario.get("nombre").toString()
                    usuario.apellido = mapUsuario.get("apellido").toString()
                    usuario.numerotelefono = mapUsuario.get("numerotelefono").toString()
                    usuario.email = mapUsuario.get("email").toString()
                    usuario.role = mapUsuario.get("role").toString()

                    //listaUsuarios.add(usuario)


                    if(uid.equals(usuario.id)){
                        role = usuario.role
                        Log.e("FB", "uid aca: "+usuario.id +" y el enviado es: "+uid +"y el role es:"+role )
                    }


                }




            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        dbReferenceUsuarios.addValueEventListener(usuarioItemListener)


        Log.e("FB", "en la funcion voy a retornar el role: "+role)
        return role
    }




}