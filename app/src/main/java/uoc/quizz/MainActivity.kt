package uoc.quizz


import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.service.autofill.Validators.and
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        //Creacion de la array de preguntas
         val preguntas = listOf<pregunta>(
             pregunta("¿Quién inventó el teléfono?", listOf("Thomas Edison","Graham Bell"), 2,"https://upload.wikimedia.org/wikipedia/commons/1/10/Alexander_Graham_Bell.jpg",1),
             pregunta("¿Quién desarrollo la teoría de la relatividad?", listOf("Albert Einstein","Isaac Newton",""), 1,"https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Albert_Einstein_Head.jpg/1200px-Albert_Einstein_Head.jpg",2 ),
             pregunta("¿Quién fue Confucio?", listOf("En la antigua china, fue un chino-japonés que inventó la confusión.","Pensador chino fundador de la escuela filosófica conocida como confucianismo",""), 2,"https://lamenteesmaravillosa.com/wp-content/uploads/2016/08/confucio-2.jpg",3 )
         )

        //DB creation
        var db = DataBaseHandler(this)
        db.insertData(preguntas[0])



        //Recibo valores de la 2ª actividad; acierto 0 o 1 y numpregunta
        var acierto = intent.getIntExtra("Acierto",0)
        val numpregunta = intent.getIntExtra("Pregunta",1)
        //val message = intent.getStringExtra(numpregunta)
        var indice_pregunta: Int = numpregunta + acierto - 1
        //Cuando se acierta la última pregunta, se vuelve a empezar
        if (indice_pregunta==3){
            indice_pregunta=0
        }
        val pregunta_actual = preguntas[indice_pregunta]

        //Referencia de num pregunta
        findViewById<TextView>(R.id.num_pregunta).text = (pregunta_actual.numpregunta.toString() + "/3")

        //Referencia de la imagen
        //findViewById<ImageView>(R.id.imageView).setImageResource(pregunta_actual.imagen)
        //var url: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Albert_Einstein_Head.jpg/1200px-Albert_Einstein_Head.jpg"
        Picasso.get().load(pregunta_actual.imagen).into(imageView);

        //Referencia de la pregunta
        findViewById<TextView>(R.id.pregunta).text = pregunta_actual.titulo

        //Referencia de las respuestas
        findViewById<TextView>(R.id.radioButton).text = pregunta_actual.respuestas[0]
        findViewById<TextView>(R.id.radioButton2).text = pregunta_actual.respuestas[1]

        // Llamada a la función al clicar el botón
        findViewById<Button>(R.id.send).setOnClickListener {
            acierto = 0
            // Pregunto si se ha marcado alguna opción
            var id: Int = radioGroup.checkedRadioButtonId
            if (id!=-1){ // Si se ha marcado alguna opción
                // Consulto que opción se ha marcado para ver si es correcto o no y llamo a la actividad 2
                //val radio: RadioButton = findViewById(id)
//                Toast.makeText(applicationContext,"On button click : ${radio.text}",
//                Toast.LENGTH_SHORT).show()
                if ((radioButton.isChecked && pregunta_actual.resultado == 1 ) || (radioButton2.isChecked && pregunta_actual.resultado == 2)) {
                    acierto = 1
                }
                //Lanzo la 2ª actividad enviando parámetros acierto y num de pregunta
                val intento1 = Intent(this, Main2Activity::class.java).apply {
                    //Le paso el parámetro de si es un acierto o no
                    putExtra("Acierto", acierto)
                    putExtra("Pregunta",preguntas[indice_pregunta].numpregunta)
                }
                startActivity(intento1)

            }else{
                // No se ha seleccionado ninguna opción
                Toast.makeText(applicationContext,getString(R.string.mensaje),
                    Toast.LENGTH_SHORT).show()
            }



            // your code to perform when the user clicks on the button
            //Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }

    }

    //Constructor de una pregunta
    class pregunta(titulo: String, respuestas: List<String>, resultado: Int, imagen: String, numpregunta: Int) {
        var titulo: String = titulo
        var respuestas: List<String> = respuestas
        var resultado: Int = resultado
        var imagen: String = imagen
        var numpregunta: Int = numpregunta
    }

}
