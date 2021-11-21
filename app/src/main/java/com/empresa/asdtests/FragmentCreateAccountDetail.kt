package com.empresa.asdtests

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Matcher
import java.util.regex.Pattern


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCreateAccountDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentCreateAccountDetail : Fragment() {
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
        val fragmento =  inflater.inflate(R.layout.fragment_create_account_detail, container, false)
        val btnCrearCuenta = fragmento.findViewById<Button>(R.id.btnCrearCuenta)
        val btnRegresar = fragmento.findViewById<Button>(R.id.btnRegresar)
        val btnPoliticaPrivacidad = fragmento.findViewById<Button>(R.id.btnPoliticaPrivacidad)

        btnCrearCuenta.setOnClickListener {
            val edtNombre = fragmento.findViewById<EditText>(R.id.etNombre)
            val edtApellido = fragmento.findViewById<EditText>(R.id.etApellido)
            val edtEmail = fragmento.findViewById<EditText>(R.id.etEmail)
            val edtNumTelefono = fragmento.findViewById<EditText>(R.id.etNumTelefono)
            val edtClave = fragmento.findViewById<EditText>(R.id.etClave)
            val cbAceptaTerminos = fragmento.findViewById<CheckBox>(R.id.cbAceptaTerminos)

            var flagValidform: Boolean = false

            if (!cbAceptaTerminos.isChecked){
                Toast.makeText(activity, "Debe aceptar terminos y condiciones para poder ingresar", Toast.LENGTH_SHORT).show()
                cbAceptaTerminos.setError("Debe aceptar terminos y condiciones para poder ingresar")
                flagValidform = false
            }else{
                flagValidform = true
            }



            if(!validarCampo(edtNombre, "nombre")){
                edtNombre.setError("Este campo debe tener más de 3 caracteres y unicamente alfabeticos")
                flagValidform = false
            }
            if(!validarCampo(edtApellido, "nombre")){
                edtApellido.setError("Este campo debe tener más de 3 caracteres y unicamente alfabeticos")
                flagValidform = false
            }
            if(!validarCampo(edtNumTelefono, "telefono")){
                edtNumTelefono.setError("Este campo debe tener un número telefonico (mínimo 7 y máximo 15 caracteres númericos)")
                flagValidform = false
            }
            if(!validarCampo(edtEmail, "email")){
                edtEmail.setError("Este campo debe tener un correo electronico de tipo nombre@dominio")
                flagValidform = false
            }
            if(!validarCampo(edtClave, "clave")){
                edtClave.setError("La clave debe contener mínimo 8 caracteres alfanumericos y contener al menos: una mayuscula, una minuscula y un caracter especial de estos:  @\$!%*#?&. ")
                flagValidform = false
            }

            if(flagValidform){
                Toast.makeText(activity, "TODO OK", Toast.LENGTH_LONG).show()


                //mostrar el segundofragmento
                activity?.getSupportFragmentManager()?.beginTransaction()
                    ?.setReorderingAllowed(true)
                    ?.replace(
                        R.id.fragmentContainerMain,
                        FragmentNewAccountCreated::class.java,
                        null,
                        "Cuenta creada"
                    )
                    ?.addToBackStack("")
                    ?.commit()


            }





        }

        btnRegresar.setOnClickListener {
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.fragmentContainerMain, CreateAccount::class.java, null, "CreateAccount")
                ?.remove(this)
                ?.commit()
        }


        btnPoliticaPrivacidad.setOnClickListener {

            //mostrar el fragmento de politica de privacidad
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(
                    R.id.fragmentContainerMain,
                    FragmentPoliticaPrivacidad::class.java,
                    null,
                    "Ver Politica Privacidad"
                )
                ?.addToBackStack("")
                ?.commit()
        }




        return fragmento


    }


    private fun validarCampo (campo: EditText, tipo: String): Boolean {
        var pattern: Pattern
        var matcher: Matcher
        var patron: String
        when (tipo){
            "nombre" -> patron = "^[a-zA-Z]{3,}$"
            "email" -> patron = EMAIL_ADDRESS.toString()
            "clave" -> patron = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&.])[A-Za-z\\d@$!%*#?&.]{8,}$"
            "telefono" -> patron = "^[0-9]{7,15}$"
        else -> patron = "^{1,}$"
        }

        pattern = Pattern.compile(patron)

        matcher = pattern.matcher(campo.text.toString())
        return matcher.matches();
    }

/*
    private fun validarContraseña (pass: String): Boolean {
        var pattern: Pattern
        var matcher: Matcher

        val PATRON_CONTRASEÑA = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"
        pattern = Pattern.compile(PATRON_CONTRASEÑA)
        matcher = pattern.matcher(pass)

        return matcher.matches();
    }
*/


/*    private fun verificar(componente: EditText, tipo: String):String{

        if(tipo.equals("nombre")){
            if(componente.text.toString().isEmpty()){
                return "Error"
            }
        }

    }*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentCreateAccountDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCreateAccountDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}