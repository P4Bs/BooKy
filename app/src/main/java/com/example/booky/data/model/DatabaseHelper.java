package com.example.booky.data.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String USUARIO_TABLA = "USUARIO";
    public static final String RESERVA_TABLA = "RESERVA";
    public static final String CALIFICACION_TABLA = "CALIFICACION";
    public static final String PLATO_TABLA = "PLATO";
    public static final String CARTA_TABLA = "CARTA";

    public static final String ID = "ID";
    public static final String NOMBRE = "NOMBRE";
    public static final String CONTRASENYA = "CONTRASEÑA";
    //FALTA PARA INSTANCIAR LA IMAGEN DEL USUARIO
    public static final String TELF_USUARIO = "NUMERO_TELEFONO";
    public static final String USUARIO_ADMIN = "ES_ADMINISTRADOR";

    public static final String DESC_PLATO = "DESCRIPCION";
    public static final String ALERG_PLATO = "ALERGENOS";
    public static final String PRECIO = "PRECIO";

    public static final String MESA = "MESA";
    public static final String FECHA_RES = "FECHA";
    public static final String OCUPANTES = "OCUPANTES";
    public static final String RES_CANCEL = "CANCELADA";
    public static final String INT_TIEMP = "INTERVALO_TIEMPO";

    public static final String ID_PLATO = "PLATO";

    public static final String USUARIO_ID = "USUARIO";
    public static final String NOTA = "NOTA";
    public static final String COMENTARIO = "COMENTARIO";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "BooKy.db", null, 1);
    }

    // Method called first time database is accessed. All table creations must be coded here.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String crearTablaUsuario = "CREATE TABLE " + USUARIO_TABLA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, CONTRASEÑA TEXT, NUM_TELEFONO INT, ES_ADMINISTRADOR BOOL);"; //FALTA IMAGEN
        String crearTablaPlato = "CREATE TABLE " + PLATO_TABLA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, DESCRIPCION TEXT, ALERGENOS TEXT, PRECIO DOUBLE);";
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
}
