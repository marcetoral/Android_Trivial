package com.example.trivial.modal;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trivial.R;

public class PreguntasDB extends SQLiteOpenHelper {

    private static final String SELECT_ULTIMA_ID = "SELECT _id FROM preguntas order by ROWID DESC limit 1";

    //atributos
    private static final String DATABASE_NAME = "trivial.db";
    private static final int DATABASE_VERSION = 3;
    private static final String PREGUNTAS_TABLE_CREATE = "CREATE TABLE preguntas " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, pregunta TEXT, respuesta INT, explicacion TEXT)";
    private static final String SCORES_TABLE_CREATE = "CREATE TABLE scores " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, score INT)";
    private Resources res;

    public PreguntasDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.res = context.getResources();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PREGUNTAS_TABLE_CREATE);
        db.execSQL(SCORES_TABLE_CREATE);
        //0 false, 1 true
        db.execSQL("INSERT INTO PREGUNTAS ( pregunta, respuesta, explicacion) " +
                "VALUES ('" + res.getString(R.string.pregunta1) + "', '" + 0 + "' ,'" + res.getString(R.string.explicacion1) + "')" +
                ",('" + res.getString(R.string.pregunta2) + "', '" + 0 + "' ,'" + res.getString(R.string.explicacion2) + "')" +
                ",('" + res.getString(R.string.pregunta3) + "', '" + 1 + "' ,'" + res.getString(R.string.explicacion3) + "')" +
                ",('" + res.getString(R.string.pregunta4) + "', '" + 1 + "' ,'" + res.getString(R.string.explicacion4) + "')" +
                ",('" + res.getString(R.string.pregunta5) + "', '" + 0 + "' ,'" + res.getString(R.string.explicacion5) + "')" +
                ",('" + res.getString(R.string.pregunta6) + "', '" + 1 + "' ,'" + res.getString(R.string.explicacion6) + "')" +
                ",('" + res.getString(R.string.pregunta7) + "', '" + 1 + "' ,'" + res.getString(R.string.explicacion7) + "')" +
                ",('" + res.getString(R.string.pregunta8) + "', '" + 0 + "' ,'" + res.getString(R.string.explicacion8) + "')" +
                ",('" + res.getString(R.string.pregunta9) + "', '" + 1 + "' ,'" + res.getString(R.string.explicacion9) + "')" +
                ",('" + res.getString(R.string.pregunta10) + "', '" + 0 + "' ,'" + res.getString(R.string.explicacion10) + "')" +
                ",('" + res.getString(R.string.pregunta11) + "', '" + 1 + "' ,'" + res.getString(R.string.explicacion11) + "')" +
                "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PREGUNTAS");
        onCreate(db);
    }

    //funciones SCORES

    //Lista tops
    public Cursor getListaTop() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT _id, score, username FROM scores ORDER BY SCORE DESC";

        return db.rawQuery(query, null);
    }


    public void updateScore(Score score) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("score", score.getScore());
        int rows = db.update("SCORES", values, "username = ?", new String[]{String.valueOf(score.getUsername())});
        if (rows == 0) {
            values.put("username", score.getUsername());
            db.insert("SCORES", null, values);
            Log.d("midebug", "hago insert" + values);
        }
    }


    //Funciones PREGUNTAS

    //Lista preguntas
    public Cursor getListaPreguntas() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT _id, pregunta, respuesta, explicacion FROM preguntas";
        Log.d("midebug", "hago getpreg");
        return db.rawQuery(query, null);
    }


    //Add pregunta
    public void addPregunta(Pregunta p) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("pregunta", p.getPregunta());
        int respuesta = p.isRespuesta() ? 1 : 0;
        values.put("respuesta", respuesta);
        values.put("explicacion", p.getExplicacion());
        db.insert("PREGUNTAS", null, values);
        //nada mas insert, recogemos el id que le ha peusto y se lo ponemos a la clase
        Cursor c = db.rawQuery(SELECT_ULTIMA_ID, null);
        c.moveToFirst();
        p.setIdPregunta(c.getInt(0));

    }

    //edit preg
    public int modificarRegistro(Pregunta pregunta) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pregunta", pregunta.getPregunta());
        values.put("explicacion", pregunta.getExplicacion());
        values.put("respuesta", pregunta.isRespuesta() ? 1 : 0);
        return db.update("PREGUNTAS", values, "_id = ?", new String[]{String.valueOf(pregunta.getIdPregunta())});
    }

    //Remove pregunta
    public long removePregunta(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Log.d("midebug", "borrar con id :" + id);
        return db.delete("PREGUNTAS", "_id = ?", new String[]{String.valueOf(id)});
    }

}
