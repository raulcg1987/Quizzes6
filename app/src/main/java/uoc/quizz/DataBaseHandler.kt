package uoc.quizz

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "Productos"
val COL_TITULO = "titulo"
val COL_RESPUESTA1 = "respuesta1"
val COL_RESPUESTA2 = "respuesta2"
val COL_RESULTADO = "resultado"
val COL_IMAGEN = "imagen"
val COL_NUMPREGUNTA = "numpregunta"

class DataBaseHandler(private var context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //DB creation
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_TITULO + " TEXT," +
                COL_RESPUESTA1 + " TEXT," +
                COL_RESPUESTA2 + " TEXT," +
                COL_RESULTADO + " INTEGER," +
                COL_IMAGEN + " TEXT," +
                COL_NUMPREGUNTA + " INTEGER PRIMARY KEY)";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(pregunta : MainActivity.pregunta){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_TITULO, pregunta.titulo)
        cv.put(COL_RESPUESTA1, pregunta.respuestas[0])
        cv.put(COL_RESPUESTA2, pregunta.respuestas[1])
        cv.put(COL_RESULTADO, pregunta.resultado)
        cv.put(COL_IMAGEN, pregunta.imagen)
        cv.put(COL_NUMPREGUNTA, pregunta.numpregunta)
        var result = db?.insert (TABLE_NAME, null, cv)
        if(result==-1.toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
    }

}