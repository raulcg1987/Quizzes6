package uoc.quizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //Parameters receiving
        val acierto = intent.getIntExtra("Acierto",0)
        val numpregunta = intent.getIntExtra("Pregunta",0)

        //Change elements values depending if correct or fail
        if (acierto == 0){
            respuesta.text = getString(R.string.fallo)
            next.text = getString(R.string.btn_fallo)
        }else {
            if (numpregunta==3){
                respuesta.text = getString(R.string.terminado)
                next.text = getString(R.string.btn_terminado)
            } else {
                respuesta.text = getString(R.string.acierto)
                next.text = getString(R.string.btn_acierto)
            }
        }

        //Button Listener
        next.setOnClickListener {
            //Go back A1 using same parameters
            val intento1 = Intent(this, MainActivity::class.java).apply {
                //Le paso el parámetro de si es un acierto o no
                putExtra("Acierto", acierto)
                putExtra("Pregunta", numpregunta)
            }
            startActivity(intento1)
        }

    }
}
