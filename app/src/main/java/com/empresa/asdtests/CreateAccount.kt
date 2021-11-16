package com.empresa.asdtests

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateAccount.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateAccount : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val fragmento = inflater.inflate(R.layout.fragment_create_account, container, false)
        val btnCreateAccount = fragmento.findViewById<Button>(R.id.btnCreateAccount)
        val btnSignIn = fragmento.findViewById<Button>(R.id.btnSignIn)

        btnCreateAccount.setOnClickListener {

            //mostrar el segundofragmento
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(
                    R.id.fragmentContainerMain,
                    FragmentCreateAccountDetail::class.java,
                    null,
                    "Crear Cuenta Detalle"
                )
                ?.addToBackStack("")
                ?.commit()
        }


        btnSignIn.setOnClickListener {

            val edtUsername = fragmento.findViewById<EditText>(R.id.etUsername)
            val edtPassword = fragmento.findViewById<EditText>(R.id.etPassword)

            if(edtUsername.text.toString().equals("123")&&edtPassword.text.toString().equals("123")){
                Toast.makeText(activity, "Ingreso OK", Toast.LENGTH_SHORT).show()

                val intent = Intent(activity, ActivityPantallaPrincipal::class.java)
                intent.putExtra("user", edtUsername.text.toString())
                startActivity(intent)
            }else
            {
                edtUsername.setError("Usuario o clave incorrecta. Usuario: 123")
                edtPassword.setError("Usuario o clave incorrecta. Clave: 123")
            }
        }


        return fragmento

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateAccount.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateAccount().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}