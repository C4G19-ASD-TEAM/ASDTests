package com.empresa.asdtests

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.empresa.asdtests.databinding.FragmentCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CreateAccount : Fragment() {


    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!


    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)


        auth = Firebase.auth




        //val fragmento = inflater.inflate(R.layout.fragment_create_account, container, false)
        //val btnCreateAccount = fragmento.findViewById<Button>(R.id.btnCreateAccount)
        //val btnSignIn = fragmento.findViewById<Button>(R.id.btnSignIn)

        binding.btnCreateAccount.setOnClickListener {

            //mostrar el segundofragmento
/*            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(
                    R.id.fragmentContainerMain,
                    FragmentCreateAccountDetail::class.java,
                    null,
                    "Crear Cuenta Detalle"
                )
                ?.addToBackStack("")
                ?.commit()*/
        }


        binding.btnSignIn.setOnClickListener {

            //val edtUsername = fragmento.findViewById<EditText>(R.id.etUsername)
            //val edtPassword = fragmento.findViewById<EditText>(R.id.etPassword)
            //val cbIsAdmin = fragmento.findViewById<CheckBox>(R.id.cbIsAdmin)

            if(binding.etUsername.text.toString().equals("123")&&binding.etPassword.text.toString().equals("123")){
                Toast.makeText(activity, "Ingreso OK", Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, ActivityPantallaPrincipal::class.java)
                intent.putExtra("user", binding.etUsername.text.toString())
                //intent.putExtra("isAdmin", cbIsAdmin.isChecked)

                startActivity(intent)
            }else
            {
                binding.etUsername.setError("Usuario o clave incorrecta. Usuario: 123")
                binding.etUsername.setError("Usuario o clave incorrecta. Clave: 123")
            }
        }


        //return fragmento
        return binding.root



    }



}