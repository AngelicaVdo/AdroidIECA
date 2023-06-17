package com.example.a17junio_activity

import android.os.Bundle
import android.app.Activity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class activity_pantalla1: Activity() {

    lateinit var text1 : TextView
    lateinit var boton : Button
    var contador = 0
    override fun onCreate(savedInstantState: Bundle?) {
        super.onCreate(savedInstantState)
        //Esto enlaza el activity con el layout
        setContentView(R.layout.pantalla_uno)

         text1 = findViewById(R.id.text1)
        boton = findViewById(R.id.boton)

        boton.setOnClickListener {
            contador++
            text1.text = contador.toString()
        }

    }

    override fun onResume() {
        super.onResume()

        Toast.makeText(this,"Hole", Toast.LENGTH_LONG).show()
/*
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://ieca.conectatalentomx.com")
        startActivity(intent)*/
/*
        //Segunda pantalla
        val intent = Intent(this, MainActivity_segunda::class.java)
        intent.data = Uri.parse("https://ieca.conectatalentomx.com")
        startActivity(intent)
        */

    }
}