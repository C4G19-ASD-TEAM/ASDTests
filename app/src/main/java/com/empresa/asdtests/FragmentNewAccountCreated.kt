package com.empresa.asdtests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class FragmentNewAccountCreated : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val fragmento = inflater.inflate(R.layout.fragment_new_account_created, container, false)
        val btnRegresar = fragmento.findViewById<Button>(R.id.btnRegresar)


        btnRegresar.setOnClickListener {
//            activity?.getSupportFragmentManager()?.beginTransaction()
//                ?.setReorderingAllowed(true)
//                ?.replace(R.id.fragmentContainerMain, CreateAccount::class.java, null, "CreateAccount")
//                ?.remove(this)
//                ?.commit()
        }


        return fragmento

    }


}