package uoc.quizz

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import android.widget.Toast
import uoc.quizz.FeedReaderContract.FeedEntry.COL_IMAGEN
import uoc.quizz.FeedReaderContract.FeedEntry.COL_RESPUESTA1
import uoc.quizz.FeedReaderContract.FeedEntry.COL_RESPUESTA2
import uoc.quizz.FeedReaderContract.FeedEntry.COL_RESULTADO
import uoc.quizz.FeedReaderContract.FeedEntry.COL_TITULO
import uoc.quizz.FeedReaderContract.FeedEntry.TABLE_NAME

object FeedReaderContract {
    // Table contents are grouped together in an anonymous object.
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "Preguntas"
        const val COL_TITULO = "titulo"
        const val COL_RESPUESTA1 = "respuesta1"
        const val COL_RESPUESTA2 = "respuesta2"
        const val COL_RESULTADO = "resultado"
        const val COL_IMAGEN = "imagen"
    }
}

class DataBaseHandler(private var context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
         val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_TITULO + " TEXT," +
                COL_RESPUESTA1 + " TEXT," +
                COL_RESPUESTA2 + " TEXT," +
                COL_RESULTADO + " INTEGER," +
                COL_IMAGEN + " TEXT)"

        //DB creation
        db?.execSQL(createTable)
        Log.d("PEC2", "createTABLE ")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MyDB"
    }

    //Insert a question in  DB
    fun insertData(pregunta : pregunta){

        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_TITULO, pregunta.titulo)
        cv.put(COL_RESPUESTA1, pregunta.respuestas[0])
        cv.put(COL_RESPUESTA2, pregunta.respuestas[1])
        cv.put(COL_RESULTADO, pregunta.resultado)
        cv.put(COL_IMAGEN, pregunta.imagen)
        var result = db?.insert (TABLE_NAME, null, cv)
        if(result==-1.toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
    }

    //Check if DB is empty or not
    fun isEmpty(): Int {
        val db = this.readableDatabase
        val projection = arrayOf(BaseColumns._ID, COL_TITULO, COL_RESPUESTA1, COL_RESPUESTA2,COL_RESULTADO, COL_IMAGEN)
        val selection = "1 = 1"
        val cursor = db.query(
            TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            null, //selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null //sortOrder               // The sort order
        )
        val itemIds = mutableListOf<Long>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                Log.d("PEC2", "itemId:"+itemId)
                return 0
                itemIds.add(itemId)
                var titulo = getString (1)
                Log.d("PEC2", "itemId:"+titulo)
            }
        }
        return 1
    }

    //Read a question from DB
    fun readData (numpregunta: Int): pregunta {
        val db = this.readableDatabase
        val projection = arrayOf(BaseColumns._ID, COL_TITULO, COL_RESPUESTA1, COL_RESPUESTA2, COL_RESULTADO, COL_IMAGEN )
        val selection = BaseColumns._ID + " = ?"
        val selectionArgs = arrayOf(""+numpregunta)
        val cursor = db.query(
            TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val index = cursor.getColumnIndexOrThrow(COL_TITULO)
        with (cursor) {
            moveToNext()
            var titulo = getString(getColumnIndexOrThrow(COL_TITULO))
            var respuesta1 = getString(getColumnIndexOrThrow(COL_RESPUESTA1))
            var respuesta2 = getString(getColumnIndexOrThrow(COL_RESPUESTA2))
            var resultado = getInt(getColumnIndexOrThrow(COL_RESULTADO))
            var imagen = getString(getColumnIndexOrThrow(COL_IMAGEN))
            var numpregunta = getInt(getColumnIndexOrThrow(BaseColumns._ID))
            return pregunta(titulo, listOf(respuesta1,respuesta2), resultado, imagen, numpregunta)
        }
        Log.d("PEC2", "Leida pregunta " + numpregunta)
    }
}