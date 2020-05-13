package uoc.quizz


import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //DB creation
        var db = DataBaseHandler(this)
        //Check if db is Empty
        if (db.isEmpty()==1) {
            //Insert data of questions
            val preguntas = listOf<pregunta>(
                pregunta("¿Quién inventó el teléfono?", listOf("Thomas Edison","Graham Bell"), 2,"https://upload.wikimedia.org/wikipedia/commons/1/10/Alexander_Graham_Bell.jpg",1),
                pregunta("¿Quién desarrollo la teoría de la relatividad?", listOf("Albert Einstein","Isaac Newton",""), 1,"https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Albert_Einstein_Head.jpg/1200px-Albert_Einstein_Head.jpg",2 ),
                pregunta("¿Quién fue Confucio?", listOf("En la antigua china, fue un chino-japonés que inventó la confusión.","Pensador chino fundador de la escuela filosófica conocida como confucianismo",""), 2,"https://lamenteesmaravillosa.com/wp-content/uploads/2016/08/confucio-2.jpg",3 )
            )
            db.insertData(preguntas[0])
            db.insertData(preguntas[1])
            db.insertData(preguntas[2])
        }

        //Parameters receiving from Activity 2
        var acierto = intent.getIntExtra("Acierto",0)
        val numpregunta = intent.getIntExtra("Pregunta",1)
        var indice_pregunta: Int = numpregunta + acierto - 1
        //When last answer is correct, start from question 1
        if (indice_pregunta==3){
            indice_pregunta=0
        }

        val pregunta_actual = db.readData(indice_pregunta+1)

        //Question number Reference
        num_pregunta.text = (pregunta_actual.numpregunta.toString() + "/3")

        //Image Reference using Picasso
        Picasso.get().load(pregunta_actual.imagen).into(imageView)

        //Question title reference
        pregunta.text = pregunta_actual.titulo

        //answer reference
        radioButton.text = pregunta_actual.respuestas[0]
        radioButton2.text = pregunta_actual.respuestas[1]

        //Button listener
        send.setOnClickListener {
            acierto = 0
            //Ask if option is choosen
            var id: Int = radioGroup.checkedRadioButtonId
            if (id!=-1){ //A option is choosen
                //Check if it's correct
                if ((radioButton.isChecked && pregunta_actual.resultado == 1 ) || (radioButton2.isChecked && pregunta_actual.resultado == 2)) {
                    acierto = 1
                }
                //Start A2 with parameters
                val intento1 = Intent(this, Main2Activity::class.java).apply {
                    putExtra("Acierto", acierto)
                    putExtra("Pregunta",pregunta_actual.numpregunta)
                }
                startActivity(intento1)

            }else{
                //No option is choosen
                Toast.makeText(applicationContext,getString(R.string.mensaje),
                    Toast.LENGTH_SHORT).show()
            }

        }

    }



}
