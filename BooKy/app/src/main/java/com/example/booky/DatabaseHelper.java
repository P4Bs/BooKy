package com.example.booky;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String USUARIO_TABLA = "USUARIO";
    private static final String RESERVA_TABLA = "RESERVA";
    private static final String CALIFICACION_TABLA = "CALIFICACION";
    private static final String CARTA_TABLA = "CARTA";

    private static final String ID = "ID";
    private static final String NOMBRE = "NOMBRE";
    private static final String CONTRASENYA = "CONTRASEÑA";
    private static final String TELF_USUARIO = "NUM_TELEFONO";
    private static final String EMAIL = "EMAIL";
    private static final String USUARIO_ADMIN = "ES_ADMINISTRADOR";

    private static final String DESC_PLATO = "DESCRIPCION";
    private static final String ALERG_PLATO = "ALERGENOS";
    private static final String PRECIO = "PRECIO";

    private static final String USUARIO = "USUARIO";
    private static final String MESA = "MESA";
    private static final String DIA_MES = "DIA";
    private static final String MES = "MES";
    // private static final String FECHA_RES = "FECHA";
    private static final String OCUPANTES = "OCUPANTES";
    private static final String RES_CANCEL = "CANCELADA";
    private static final String INT_TIEMP = "INTERVALO_TIEMPO";

    private static final String ID_PLATO = "PLATO";
    private static final String NOTA = "NOTA";
    private static final String COMENTARIO = "COMENTARIO";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "BooKy.db", null, 1);
    }

    // Method called first time database is accessed. All table creations must be coded here.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String crearTablaUsuario = "CREATE TABLE " + USUARIO_TABLA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, CONTRASEÑA TEXT, NUM_TELEFONO TEXT, EMAIL TEXT, ES_ADMINISTRADOR BOOL);"; //FALTA IMAGEN
        String crearTablaCarta = "CREATE TABLE " + CARTA_TABLA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT UNIQUE, DESCRIPCION TEXT, ALERGENOS TEXT, PRECIO TEXT);";
        String crearTablaReserva = "CREATE TABLE " + RESERVA_TABLA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USUARIO INTEGER, MESA TEXT, DIA INT, MES INT, OCUPANTES INT, INTERVALO_TIEMPO TEXT, FOREIGN KEY(USUARIO) REFERENCES USUARIO(ID));";
        String crearTablaCalificacion = "CREATE TABLE " + CALIFICACION_TABLA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USUARIO INTEGER, PLATO INTEGER, NOTA TEXT, COMENTARIO TEXT, FOREIGN KEY(USUARIO) REFERENCES USUARIO(ID), FOREIGN KEY(PLATO) REFERENCES CARTA(ID));";

        db.execSQL(crearTablaUsuario);
        db.execSQL(crearTablaCarta);
        db.execSQL(crearTablaReserva);
        db.execSQL(crearTablaCalificacion);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean anyadeUsuario(Usuario user){ //Añade un usuario a la base de datos
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NOMBRE, user.getNombre());
        cv.put(CONTRASENYA, user.getContrasenya());
        cv.put(TELF_USUARIO, user.getNumTelefono());
        cv.put(EMAIL, user.getCorreo());
        cv.put(USUARIO_ADMIN, user.isEsAdmin());

        long insert = db.insert(USUARIO_TABLA, null, cv);
        return insert != -1;
    }

    public boolean anyadePlato(Plato plato){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NOMBRE, plato.getNombre());
        cv.put(DESC_PLATO, plato.getDescripcion());
        cv.put(ALERG_PLATO, plato.getAlergenos());
        cv.put(PRECIO, plato.getPrecio());

        long insert = db.insert(CARTA_TABLA, null, cv);
        return insert != -1;
    }

    public boolean anyadeReserva(Reserva reserva, int IDUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USUARIO, IDUsuario);
        cv.put(MESA, reserva.getMesa());
        cv.put(DIA_MES, reserva.getDia());
        cv.put(MES, reserva.getMes());
        cv.put(OCUPANTES, reserva.getOcupantes());
        cv.put(INT_TIEMP, reserva.getIntervaloTiempo());

        long insert = db.insert(RESERVA_TABLA, null, cv);
        return insert != -1;
    }

    public boolean anyadeCalificacion(Calificacion calificacion){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USUARIO, calificacion.getIDUsuario());
        cv.put(ID_PLATO, calificacion.getIDPlato());
        cv.put(NOTA, calificacion.getNota());
        cv.put(COMENTARIO, calificacion.getComentario());

        long insert = db.insert(CALIFICACION_TABLA, null, cv);
        return insert != -1;
    }

    /*
        TODO DELETE:
            USUARIO
            COMENTARIO
            PLATO DE CARTA
            RESERVA
     */

    public boolean borraUsuario(Usuario usuario){
        //TODO: BORRAR RESERVAS Y COMENTARIOS DEL USUARIO Y PERFIL USUARIO

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + USUARIO_TABLA + " WHERE " + ID + " = " + usuario.getID();
        borraTodasLasReservas(db, usuario);
        borraTodosLasCalificacionesDelUsuario(db, usuario);
        Cursor cursor = db.rawQuery(queryString, null);

        return cursor.moveToFirst();
    }

    private boolean borraTodasLasReservas(SQLiteDatabase db, Usuario usuario){
        String queryString = "DELETE FROM " + RESERVA_TABLA + " WHERE " + USUARIO + " = " + usuario.getID();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }

    private boolean borraTodosLasCalificacionesDelUsuario(SQLiteDatabase db, Usuario usuario){
        String queryString = "DELETE FROM " + CALIFICACION_TABLA + " WHERE " + USUARIO + " = " + usuario.getID();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }

    public boolean borrarPlato(int IDplato){
        //TODO: BORRAR TODAS LAS CALIFICACIONES ASOCIADAS AL PLATO
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CARTA_TABLA + " WHERE " + ID + " = " +IDplato;
        borraTodasLasCalificacionesDelPlato(db, IDplato);
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }

    private boolean borraTodasLasCalificacionesDelPlato(SQLiteDatabase db, int IDPlato){
        String queryString = "DELETE FROM " + CALIFICACION_TABLA + " WHERE " + ID_PLATO + " = " + IDPlato;
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }

    public boolean borrarReserva(Reserva reserva){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + RESERVA_TABLA + " WHERE " + ID + " = " + reserva.getID();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }


    /*
        TODO MODIFICACIONES:
            USUARIO
                CAMBIAR CONTRASEÑA / DONE
                CAMBIAR TELEFONO / DONE
                CAMBIAR CORREO / DONE
     */


    public boolean cambiarContrasenyaUsuario(Usuario newUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString =    "UPDATE " + USUARIO_TABLA +
                                " SET " + CONTRASENYA + " = '" + newUsuario.getContrasenya() +
                                "' WHERE " + ID + " = " + newUsuario.getID();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }

    public boolean cambiarTelefonoUsuario(Usuario newUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString =    "UPDATE " + USUARIO_TABLA +
                                " SET " + TELF_USUARIO + " = '" + newUsuario.getNumTelefono() +
                                "' WHERE " + ID + " = " + newUsuario.getID();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }

    public boolean cambiarNombreUsuario(Usuario newUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString =    "UPDATE " + USUARIO_TABLA +
                " SET " + NOMBRE + " = '" + newUsuario.getNombre() +
                "' WHERE " + ID + " = " + newUsuario.getID();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }

    // ESTE METODO GENERALIZA EL CAMBIO DE LOS DATOS DEL PLATO
    // CUANDO EL ADMIN QUIERE CAMBIAR DATOS DEL PLATO SE ENCUENTRA CON UNA VENTANA CON LOS DATOS INTRODUCIDOS
    // EL ADMIN HACE CAMBIOS EN LOS CAMPOS QUE DESEA CAMBIAR Y SE SOBREESCRIBEN TODAS LAS COLUMNAS DE ESA TUPLA
    public boolean cambiarDatosPlato(Plato newPlato){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString =    "UPDATE " + CARTA_TABLA +
                                " SET " +   NOMBRE + " = " + newPlato.getNombre() + ", " +
                                            DESC_PLATO + " = " + newPlato.getDescripcion() + ", " +
                                            ALERG_PLATO + " = " + newPlato.getAlergenos() + ", " +
                                            PRECIO + " = " + newPlato.getPrecio() +
                                " WHERE " + ID + " = " + newPlato.getID();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }

    //EL METODO DE CAMBIO DE DATOS DE UNA CALIFICACION TIENE LA MISMA ESTRUCTURA QUE EL DE CAMBIO DE DATOS DE PLATO
    public boolean cambiarDatosCalificacion(Calificacion newCalificacion){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString =    "UPDATE " + CALIFICACION_TABLA +
                                " SET " +   NOTA + " = " + newCalificacion.getNota() + ", " +
                                            COMENTARIO + " = " + newCalificacion.getComentario() +
                                " WHERE " + ID + " = " + newCalificacion.getID();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }


    /*
        TODO: BUSCAR USUARIOS
     */

    public Cursor getDatosUsuario(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + USUARIO_TABLA + " WHERE " + EMAIL + " = '" + email + "'";
        return db.rawQuery(queryString, null);
    }

    public Cursor getDatosUsuario(int IDUsuario){
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + USUARIO_TABLA + " WHERE " + ID + " = " + IDUsuario;
        return db.rawQuery(queryString, null);
    }

    public boolean estaElUsuario(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + USUARIO_TABLA + " WHERE " + EMAIL + " = '" + email + "'";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }

    public boolean estaELplato(String nombrePlato){
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + CARTA_TABLA + " WHERE " + NOMBRE + " = '" + nombrePlato + "'";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
    }

    public String esAdmin(String email, String contraseña){
        Cursor cursor = devuelveSiAdmin(email, contraseña);

        if(cursor.moveToFirst()){
            return cursor.getString(0);
        } else {
            return null;
        }
    }

    private Cursor devuelveSiAdmin(String email, String contraseña){
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT " + USUARIO_ADMIN + " FROM " + USUARIO_TABLA + " WHERE " + EMAIL + " = '" + email + "' AND " + CONTRASENYA + " = '" + contraseña + "'";
        Cursor cursor = db.rawQuery(queryString, null);

        return cursor;
    }

    public Cursor getPlato(int IDPlato){ //OBTIENE UN PLATO DE LA BASE DE DATOS SEGUN SU NOMBRE
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + CARTA_TABLA + " WHERE " + ID + " = '" + IDPlato + "'";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //MOSTRAR LOS PLATOS EN LISTA
    public List<Plato> get_lista_platos(){

        List<Plato> returnlist = new ArrayList<>();

        String querystring = "SELECT * FROM " + CARTA_TABLA;

        SQLiteDatabase db  = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(querystring,null);

        if(cursor.moveToFirst()){
            do{
                int ID = cursor.getInt(0);
                String Nombre = cursor.getString(1);
                String Descripcion = cursor.getString(2);
                String AlergenosConcatenados = cursor.getString(3);
                String Precio = cursor.getString(4);

                Plato platoactual = new Plato(ID,Nombre,Descripcion,AlergenosConcatenados, Precio);
                returnlist.add(platoactual);


            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnlist;

    }

    public List<Usuario> get_lista_usuarios(){

        List<Usuario> returnlist = new ArrayList<>();

        String querystring = "SELECT * FROM " + USUARIO_TABLA;

        SQLiteDatabase db  = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(querystring,null);

        if(cursor.moveToFirst()){
            do{
                int ID = cursor.getInt(0);
                String Nombre = cursor.getString(1);
                String contraseña = cursor.getString(2);
                String telefono = cursor.getString(3);
                String email = cursor.getString(4);
                boolean admin = cursor.getInt(5) > 0;

                Usuario usuarioactual = new Usuario(ID,Nombre,contraseña,telefono,email,admin);
                returnlist.add(usuarioactual);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnlist;

    }

    public List<Calificacion> getListaComentarios(int IDPlato){
        List<Calificacion> calificaciones = new ArrayList<>();
        String queryString = "SELECT * FROM " + CALIFICACION_TABLA + " WHERE " + ID_PLATO + " = " + IDPlato;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int ID = cursor.getInt(0);
                int usuarioID = cursor.getInt(1);
                int platoID = cursor.getInt(2);
                String notaPlato = cursor.getString(3);
                String comentarioPlato = cursor.getString(4);

                Calificacion nuevoComentario = new Calificacion(ID, usuarioID, platoID, notaPlato, comentarioPlato);
                calificaciones.add(nuevoComentario);
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return calificaciones;
    }

    public List<Reserva> getListaReservas(int IDUsuario){
        List<Reserva> reservas = new ArrayList<>();
        String queryString = "SELECT * FROM " + RESERVA_TABLA + " WHERE " + USUARIO + " = " + IDUsuario;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int ID = cursor.getInt(0);
                int usuarioID = cursor.getInt(1);
                String mesa = cursor.getString(2);
                int dia = cursor.getInt(3);
                int mes = cursor.getInt(4);
                int ocupantes = cursor.getInt(5);
                String intervalo = cursor.getString(6);

                Reserva reserva = new Reserva(ID, usuarioID, Integer.parseInt(mesa), dia, mes, ocupantes, intervalo);
                reservas.add(reserva);
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return reservas;
    }

    public List<Reserva> getTodasLasReservas(){
        List<Reserva> reservas = new ArrayList<>();
        String queryString = "SELECT * FROM " + RESERVA_TABLA;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int ID = cursor.getInt(0);
                int usuarioID = cursor.getInt(1);
                String mesa = cursor.getString(2);
                int dia = cursor.getInt(3);
                int mes = cursor.getInt(4);
                int ocupantes = cursor.getInt(5);
                String intervalo = cursor.getString(6);

                Reserva reserva = new Reserva(ID, usuarioID, Integer.parseInt(mesa), dia, mes, ocupantes, intervalo);
                reservas.add(reserva);
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return reservas;
    }

    public Calificacion getCalificacion(int idCalficacion){
        String queryString = "SELECT * FROM " + CALIFICACION_TABLA + " WHERE " + ID + " = " + idCalficacion;
        SQLiteDatabase db = this.getReadableDatabase();
        Calificacion calificacion = null;
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            int ID = cursor.getInt(0);
            int usuarioID =  cursor.getInt(1);
            int platoID = cursor.getInt(2);
            String notaPlato = cursor.getString(3);
            String comentarioPlato = cursor.getString(4);
            calificacion = new Calificacion(ID, usuarioID, platoID, notaPlato, comentarioPlato);
        }
        return calificacion;
    }

    public boolean estaLaReserva(Reserva reserva){
        SQLiteDatabase db = this.getReadableDatabase();
        String quaryString = "SELECT * FROM " + RESERVA_TABLA + " WHERE "   + DIA_MES + " = " + reserva.getDia() + " AND "
                                                                            + MES + " = " + reserva.getMes() + " AND "
                                                                            + MESA + " = '" + reserva.getMesa() + "' AND "
                                                                            + INT_TIEMP + " = '" + reserva.getIntervaloTiempo() + "'";
        Cursor cursor = db.rawQuery(quaryString, null);
        return cursor.moveToFirst();
    }
}
