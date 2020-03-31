package uoc.quizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //Recibo los parámetros acierto y numpregunta
        val acierto = intent.getIntExtra("Acierto",0)
        val numpregunta = intent.getIntExtra("Pregunta",0)

        //Los elementos se muestran diferentes en función de si se acierta o no
        if (acierto == 0){
            findViewById<TextView>(R.id.respuesta).text = getString(R.string.fallo)
            findViewById<Button>(R.id.next).text = getString(R.string.btn_fallo)
        }else {
            if (numpregunta==3){
                findViewById<TextView>(R.id.respuesta).text = getString(R.string.terminado)
                findViewById<Button>(R.id.next).text = getString(R.string.btn_terminado)
            } else {
                findViewById<TextView>(R.id.respuesta).text = getString(R.string.acierto)
                findViewById<Button>(R.id.next).text = getString(R.string.btn_acierto)
            }
        }

        // Listener del botón
        findViewById<Button>(R.id.next).setOnClickListener {
            //Vuelvo a la actividad 1 pasando los mismos parámetros
            val intento1 = Intent(this, MainActivity::class.java).apply {
                //Le paso el parámetro de si es un acierto o no
                putExtra("Acierto", acierto)
                putExtra("Pregunta", numpregunta)
            }
            startActivity(intento1)
        }

    }
}
