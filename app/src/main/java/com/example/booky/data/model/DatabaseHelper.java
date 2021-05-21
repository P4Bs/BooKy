package com.example.booky.data.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String USUARIO_TABLA = "USUARIO";
    private static final String RESERVA_TABLA = "RESERVA";
    private static final String CALIFICACION_TABLA = "CALIFICACION";
    private static final String PLATO_TABLA = "PLATO";
    private static final String CARTA_TABLA = "CARTA";

    private static final String ID = "ID";
    private static final String NOMBRE = "NOMBRE";
    private static final String CONTRASENYA = "CONTRASEÑA";
    private static final String IMAGEN = "IMAGEN";
    private static final String TELF_USUARIO = "NUMERO_TELEFONO";
    private static final String USUARIO_ADMIN = "ES_ADMINISTRADOR";

    private static final String DESC_PLATO = "DESCRIPCION";
    private static final String ALERG_PLATO = "ALERGENOS";
    private static final String PRECIO = "PRECIO";

    private static final String MESA = "MESA";
    private static final String FECHA_RES = "FECHA";
    private static final String OCUPANTES = "OCUPANTES";
    private static final String RES_CANCEL = "CANCELADA";
    private static final String INT_TIEMP = "INTERVALO_TIEMPO";

    private static final String ID_PLATO = "PLATO";

    private static final String USUARIO_ID = "USUARIO";
    private static final String NOTA = "NOTA";
    private static final String COMENTARIO = "COMENTARIO";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "BooKy.db", null, 1);
    }

    // Method called first time database is accessed. All table creations must be coded here.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String crearTablaUsuario = "CREATE TABLE " + USUARIO_TABLA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, CONTRASEÑA TEXT, IMAGEN BLOB, NUM_TELEFONO INT, ES_ADMINISTRADOR BOOL);"; //FALTA IMAGEN
        String crearTablaPlato = "CREATE TABLE " + PLATO_TABLA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, DESCRIPCION TEXT, IMAGEN BLOB, ALERGENOS TEXT, PRECIO INTEGER);";
        String crearTablaReserva = "CREATE TABLE " + RESERVA_TABLA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USUARIO INTEGER, MESA TEXT, FECHA DATE, OCUPANTES INT, CANCELADA BOOLEAN, INTERVALO_TIEMPO TEXT, FOREIGN KEY(USUARIO) REFERENCES USUARIO(ID));";
        String crearTablaCarta = "CREATE TABLE " + CARTA_TABLA + " (PLATO INTEGER, FOREIGN KEY(PLATO) REFERENCES PLATO(ID));";
        String crearTablaCalificacion = "CREATE TABLE " + CALIFICACION_TABLA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USUARIO INTEGER, PLATO INTEGER, NOTA INTEGER, COMENTARIO TEXT, FOREIGN KEY(USUARIO) REFERENCES USUARIO(ID), FOREIGN KEY(PLATO) REFERENCES PLATO(ID));";

        db.execSQL(crearTablaUsuario);
        db.execSQL(crearTablaPlato);
        db.execSQL(crearTablaReserva);
        db.execSQL(crearTablaCarta);
        db.execSQL(crearTablaCalificacion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean anyadeUsuario(Usuario user){ //Añade un usuario a la base de datos
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID, user.getID());
        cv.put(NOMBRE, user.getNombre());
        cv.put(IMAGEN, user.getImagenByteArray());
        cv.put(CONTRASENYA, user.getContrasenya());
        cv.put(TELF_USUARIO, user.getNumTelefono());
        cv.put(USUARIO_ADMIN, user.isEsAdmin());

        long insert = db.insert(USUARIO_TABLA, null, cv);
        return insert != -1;
    }

    public boolean anyadePlato(Plato plato){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID, plato.getID());
        cv.put(NOMBRE, plato.getNombre());
        cv.put(DESC_PLATO, plato.getDescripcion());
        cv.put(IMAGEN, plato.getImagenAsByteArray());
        cv.put(ALERG_PLATO, plato.getAlergenos());
        cv.put(PRECIO, plato.getPrecio());

        long insert = db.insert(PLATO_TABLA, null, cv);
        return insert != -1;
    }

    public boolean anyadeReserva(Reserva reserva){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID, reserva.getID());
        cv.put(MESA, reserva.getMesa());
        cv.put(FECHA_RES, reserva.getFecha());
        cv.put(OCUPANTES, reserva.getOcupantes());
        cv.put(RES_CANCEL, reserva.isCancelada());
        cv.put(INT_TIEMP, reserva.getIntervaloTiempo());

        long insert = db.insert(RESERVA_TABLA, null, cv);
        return insert != -1;
    }
}
