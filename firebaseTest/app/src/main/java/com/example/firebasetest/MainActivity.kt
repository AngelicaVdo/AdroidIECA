package com.example.firebasetest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firebasetest.ui.theme.FirebaseTestTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Inicializar Firebaseñuth
        auth = FirebaseAuth.getInstance()

        //loginAnonymous()
        val email = "agvillalpando24@gmail.com"
        val password = "A1a1a1a1"

        // Inicializar FirebaseDatabase
        database = FirebaseDatabase.getInstance()
        //getSeasons()
        setSeason("8")

    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser?  = auth.currentUser
        if (currentUser != null){
            if (currentUser.email !="" ){
                Toast.makeText(this, "Bienvenido" + currentUser.email, Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Bienvenido anonimo", Toast.LENGTH_LONG).show()  }
        }
        else {
            //login("agvillalpando24@gmail.com", "A1a1a1a1")
            loginAnonymous() }
 }

    fun login (email :String, password : String)
    {// Iniciar sesión con correo electrónico y contraseña
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


    fun loginAnonymous(){
        auth.signInAnonymously().addOnCompleteListener{task ->
            if (task.isSuccessful){
                // Inicio se sesión anónimo exitoso
                val user = auth.currentUser
                // Acción con el usuario autenticado
                Toast.makeText(this, "Inicio de sesión anónimo exitoso",Toast.LENGTH_LONG).show()
            }else{
                // Error en el inicio de sesión anónimo
                Toast.makeText(this, "Error en el inicio de sesión anónimo: ${task.exception?.message}",Toast.LENGTH_LONG).show()
            }
        }
    }

   fun getSeasons(){
       val reference = database.getReference("seasons")

       reference.addListenerForSingleValueEvent(object : ValueEventListener {
           override fun onDataChange(dataSnapshot: DataSnapshot) {
               for (seasonSnapshot in dataSnapshot.children){
                   val season = seasonSnapshot.getValue(Season::class.java)

                   val message = "{ name : ${season?.name} , description : ${season?.description}, status : ${season?.status}"

                   Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
               }
           }

           override fun onCancelled(databaseError : DatabaseError){
               println("Error al leer los datos ${databaseError.message}")
           }
       })
   }
    fun setSeason(seasionID : String){
        val reference = database.getReference("seasons")
        //crea objeto de la sesion
        var season = Season("Sesion agregada", "descripcion de prueba")
        // escribe datos en base de datos
        reference.child(seasionID).setValue(season).addOnCanceledListener {
            Toast.makeText(this@MainActivity, "Se actualizo BD", Toast.LENGTH_LONG).show()
        }

    }




}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirebaseTestTheme {
        Greeting("Android")
    }
}