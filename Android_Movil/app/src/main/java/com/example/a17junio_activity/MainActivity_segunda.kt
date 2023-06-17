package com.example.a17junio_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity_segunda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_segunda)


    }

    override fun onResume() {
        super.onResume()

        Toast.makeText(this, "Paso al segundo activity", Toast.LENGTH_LONG).show()

    }
}