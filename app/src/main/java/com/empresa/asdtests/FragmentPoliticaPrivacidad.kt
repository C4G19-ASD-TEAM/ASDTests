package com.empresa.asdtests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPoliticaPrivacidad.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPoliticaPrivacidad : Fragment() {
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

        val fragmento = inflater.inflate(R.layout.fragment_politica_privacidad, container, false)
        val btnRegresarPolitica = fragmento.findViewById<Button>(R.id.btnRegresarPolitica)

        btnRegresarPolitica.setOnClickListener {
//            activity?.getSupportFragmentManager()?.beginTransaction()
//                ?.setReorderingAllowed(true)
//                ?.replace(R.id.fragmentContainerMain, FragmentCreateAccountDetail::class.java, null, "CreateAccount")
//                ?.remove(this)
//                ?.commit()
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
         * @return A new instance of fragment FragmentPoliticaPrivacidad.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentPoliticaPrivacidad().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}