package com.example.proyecto_movil
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
//Almacenamiento interno
import java.io.File
//Almacenamiento Externo
import android.os.Environment
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader


class principal_activity : AppCompatActivity() {

    //Ejercicio de Camara
    private val CAMERA_PERMISSION_CODE =100
    //Almacenamiento Interno
    val nombreArchivoAI = "miarchivoAI.txt"
    val datosAI = "Contenido del archivo en almacenamiento Interno"
    //Almacenamiento Externo
    val nombreArchivoAE = "miarchivoAEtxt"
    val datosAE = "Contenido del archivo en almacenamiento Externo"
    //Almacenamiento CAche
    val clave = "Clave"
    val valor = "Mi valor de cache"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //aqui enlazo el activity con el layout correspondiente
        setContentView(R.layout.activity_principal)

        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        //aqui se agrega el fragmento al activity
      /* val pedaso_de_fragmento = fragmento_activity()
        supportFragmentManager.beginTransaction()
            .add(R.id.mi_primer_fragmento, pedaso_de_fragmento).commit()*/

        /*====================================================================
                Almacenamiento Camara
        ====================================================================*/
        //requesCameraPermision()

        /*====================================================================
                        Almacenamiento Interno
        ====================================================================*/
        //escribirDatosAlmacenamientoInterno(nombreArchivoAI , datosAI)

        /*====================================================================
                        Almacenamiento Externo
        ====================================================================*/
        //escribirDatosAlmacenamientoExterno()

        /*====================================================================
                        Almacenamiento Cache
        ====================================================================*/
       // escribirDatosAlmacenamientoCache(this, clave, valor)
    }

    override fun onResume() {
        super.onResume()

        /*====================================================================
                        Almacenamiento Interno
        ====================================================================*/
        /*val contenido = leerDatosAlmacenamientoInterno(nombreArchivoAI)
        Toast.makeText(this, contenido, Toast.LENGTH_LONG).show()*/

        /*====================================================================
                        Almacenamiento Externo
        ====================================================================*/
        /*val contenido = leerDatosAlamacenamientoExterno()
        Toast.makeText(this, contenido, Toast.LENGTH_LONG).show()*/
        //====================================================================

        /*====================================================================
                Almacenamiento cache
        ====================================================================*/
        //val contenido = leerDatosAlamacenamientoCache(this, clave, valor)
        //Toast.makeText(this, contenido, Toast.LENGTH_LONG).show()

    }


    /*===========================================================================================
                FUNCIONES PARA EJERCICIO DE CAMARA
       ========================================================================================*/
    private fun requesCameraPermision(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            //PERMISO OTORGADO
        }else{
            //PERMISO NO OTORGADO, SOLICITARLO
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
        }
    }

    override fun onRequestPermissionsResult( requestCode: Int,permissions: Array<out String>,
                                             grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //PERMISO OTORGADO
                }
                else{
                    //permiso denegado, volver a solcitar
                }
            }
        }
    }


    /*===========================================================================================
            FUNCIONES PARA EJERCICIO DE ALMACENAMIENTO INTERNO
   ========================================================================================*/
    fun escribirDatosAlmacenamientoInterno(nombreArchivo: String, datos: String){
        val archivo = File(this.filesDir, nombreArchivo)
        archivo.writeText(datos)
    }

    fun leerDatosAlmacenamientoInterno(nombreArchivo: String): String{
        val archivo = File (this.filesDir, nombreArchivo)
        return archivo.readText()
    }



    /*===========================================================================================
            FUNCIONES PARA EJERCICIO DE ALMACENAMIENTO EXTERNO
   ========================================================================================*/
    private fun escribirDatosAlmacenamientoExterno(){
        val estado = isExternalStorageWritable()
        if (estado){
            val directorio = getExternalFilesDir(null)
            var archivo = File(directorio, nombreArchivoAE)
            try{
                FileOutputStream(archivo).use {
                    it.write(datosAE.toByteArray())

                }
            }catch (e: IOException){
                e.printStackTrace()
            }

        }
    }

    private fun isExternalStorageWritable(): Boolean {
        val estado = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED ==  estado

    }

    private fun isExternalStorageReadable(): Boolean {
        val estado = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED ==  estado || Environment.MEDIA_MOUNTED_READ_ONLY == estado

    }

    private fun leerDatosAlamacenamientoExterno():String{
        val estado = isExternalStorageWritable()
        if (estado){
            val directorio = getExternalFilesDir(null)
            val archivo = File(directorio, nombreArchivoAE)
            val fileInputStream = FileInputStream(archivo)
            val inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({text =  bufferedReader.readLine(); text}() != null){
                stringBuilder.append(text)
            }
            fileInputStream.close()

            return stringBuilder.toString()
        }
        return ""
    }


    /*===========================================================================================
            FUNCIONES PARA EJERCICIO DE ALMACENAMIENTO CACHE
   ========================================================================================*/

    fun escribirDatosAlmacenamientoCache(context: Context, clave: String, valor: String){
        val sharedPreferences =  context.getSharedPreferences("cache", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(clave, valor)
        editor.apply()
    }

    fun leerDatosAlamacenamientoCache(context: Context, clave: String, valor: String): String? {
        val sharedPreferences =  context.getSharedPreferences("cache", Context.MODE_PRIVATE)
        return sharedPreferences.getString(clave, null)
    }

}
