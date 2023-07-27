package com.example.firebasetest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvErrorMessage: TextView
    private lateinit var tvRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)
        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        //loginAnonymous()
        val email = "agvillalpando24@gmail.com"
        val password = "A1a1a1a1"

        etEmail = findViewById(R.id.editTextEmail)
        etPassword = findViewById(R.id.editTextPassword)
        btnLogin = findViewById(R.id.buttonLogin)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Favor de ingresar Correo y contraseña", Toast.LENGTH_LONG).show()
            }
        }


        // Inicializar FirebaseDatabase
        database = FirebaseDatabase.getInstance()
        //getSeasons()
        //setSeason("8")

    }
/*
    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        if (currentUser != null) {
            if (currentUser.email != "") {
                Toast.makeText(this, "Bienvenido" + currentUser.email, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Bienvenido anonimo", Toast.LENGTH_LONG).show()
            }
        } else {
            login("agvillalpando24@gmail.com", "A1a1a1a1")
            //loginAnonymous() }
        }


        fun loginAnonymous() {
            auth.signInAnonymously().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Inicio se sesión anónimo exitoso
                    val user = auth.currentUser
                    // Acción con el usuario autenticado
                    Toast.makeText(this, "Inicio de sesión anónimo exitoso", Toast.LENGTH_LONG)
                        .show()
                } else {
                    // Error en el inicio de sesión anónimo
                    Toast.makeText(
                        this,
                        "Error en el inicio de sesión anónimo: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        fun login(email: String, password: String) {
            // Iniciar sesión con correo electrónico y contraseña
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Inicio de sesión exitoso
                        val user = auth.currentUser
                        // Hacer algo con el usuario autenticado
                        Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show()
                    } else {
                        // Error en el inicio de sesión
                        Toast.makeText(
                            this,
                            "Error en el inicio de sesion: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }

        fun getSeasons() {
            val reference = database.getReference("seasons")
            reference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (seasonSnapshot in dataSnapshot.children) {
                        val season = seasonSnapshot.getValue(Season::class.java)
                        val message =
                            "{ name : ${season?.name} , description : ${season?.description}, status : ${season?.status}"
                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("Error al leer los datos ${databaseError.message}")
                }
            })
        }

        fun setSeason(seasionID: String) {
            val reference = database.getReference("seasons")
            //crea objeto de la sesion
            var season = Season("Sesion agregada", "descripcion de prueba")
            // escribe datos en base de datos
            reference.child(seasionID).setValue(season).addOnCanceledListener {
                Toast.makeText(this@MainActivity, "Se actualizo BD", Toast.LENGTH_LONG).show()
            }

        }


    }
*/
    private fun login(email: String, password: String) {
        // Iniciar sesión con correo electrónico y contraseña
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Inicio de sesión exitoso
                    val user = auth.currentUser
                    // Hacer algo con el usuario autenticado
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show()
                } else {
                    // Error en el inicio de sesión
                    Toast.makeText(
                        this,
                        "Error en el inicio de sesion: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Inicio de sesión exitoso: ${user?.email}", Toast.LENGTH_SHORT).show()
                    // Aquí puedes redirigir a la siguiente actividad después del inicio de sesión exitoso
                    val intent = Intent(this, PerfilActivity::class.java)
                    // Iniciar la actividad OtraActivity
                    startActivity(intent)
                    // Opcionalmente, puedes finalizar la actividad actual después de iniciar la otra actividad
                    finish()
                } else {
                    Toast.makeText(this, "Favor de ingresar Correo y contraseña", Toast.LENGTH_LONG).show()
                }
            }
    }


}