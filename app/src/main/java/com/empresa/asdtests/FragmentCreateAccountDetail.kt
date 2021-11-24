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
import com.empresa.asdtests.database.ASDTestsDB
import com.empresa.asdtests.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern


class FragmentCreateAccountDetail : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            val cbIsAdmin = fragmento.findViewById<CheckBox>(R.id.cbIsAdmin)

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
                //todo bien (form validado)

                Toast.makeText(activity, "TODO OK... Insertando usuario en la base de datos", Toast.LENGTH_LONG).show()

                //insertar en la BD
                val context = activity?.applicationContext

                //Instanciamos un objeto pelicula par Guardar en la BD
                //val usuario =  Usuario( 0, "${edtTitulo.text}", edtDuracion.text.toString().toInt(), "${edtProtagonista.text}")

                var role: String
                if(cbIsAdmin.isChecked){
                    role = "Admin"
                }else{
                    role = "User"
                }


                val usuario = Usuario(0, edtEmail.text.toString(), edtClave.text.toString(), role )


/*                //insertamos en la BDs utilizando una Coroutine
                CoroutineScope( Dispatchers.IO ).launch {
                    val database = context?.let{ ASDTestsDB.getDataBase( it ) }
                    if ( database != null){
                        database.usuarioDAO().insert( usuario )
                        Log.e("FG", "Entro a insertar")
                        Log.e("FG", "Entro a insertar")

                    }
                }*/

                //FIN INSERTAR USUARIO EN BD

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



}