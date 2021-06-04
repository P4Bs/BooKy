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

        anyadeDatosDeInicio(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void anyadeDatosDeInicio(SQLiteDatabase db){
        Plato plato = new Plato(-1,"lasaña","La lasaña se prepara con láminas de pastas cuadradas crudas o precocidas, que se colocan en capas una sobre otra y entre las que se agrega carne molida de vacuno sazonada y cocida.\n" +
                                "Sobre las camas de carne entre capas se añade queso mozzarella rallado y salsa bechamel, para dar sabor y compactar mejor.","Queso,Carne","16,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"Risotto alla milanese","Sus ingredientes principales son el arroz y el toque de queso parmesano. Su textura cremosa combinada con el sabor del queso hace de este plato una experiencia culinaria inigualable.Otros ingredientes clave para su preparación son las espinacas, los mariscos, setas y otros quesos. También el ajo y la cebolla fina entre aceite de oliva, el azafrán para dar color y como toque especial, un chorrito de vino blanco.","azafrán","19,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"Carpaccio","El carpaccio es una de las comidas típicas al norte de Italia. Consiste en carne o pescado crudo cortado en láminas finas que se maceran con aceite de oliva y zumo de limón o vinagre y se acompaña con sal y queso parmesano.","Salmón,vinagre,queso","24,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"Minestrone","La de minestrone no es más que una suculenta sopa de verduras con un poco de pasta o sémola de arroz, que se come caliente y espesa como plato principal. Está considerada una comida con alto valor nutricional por la variedad de verduras y vegetales.","apio,pollo,tocino","21,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"Pizza Margarita","Se cree que la pizza proviene del pan, pues en la Roma antigua se hacía redondo y cortado en porciones cónicas, al que le agregaban una salsa a base de tomates con virutas de queso mozzarella esparcido por encima.","queso","13,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"Ensalada capresse","plato de vegetales frescos que se sirve como entrada, aperitivo o pasaboca, ideal para días de verano,una de las ensaladas más típicas la cúal proviene de la region de Crapi","queso","12,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"Ossobuco","También conocido como jarrete de ternera o como ossobuco a la milanesa, se cocina guisando la carne con tomate, cebolla y zanahoria; se adereza con ajo, romero, laurel, pimienta y sal. El toque final es un poco de vino blanco. Lo que le diferencia de otras carnes es que su cantidad de grasa es la necesaria para lograr una carne jugosa y suculenta.","cebolla,ajo,romero,laurel","29,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"Espaguetis a la carbonara","Una de las pastas más representativas de la gastronomía italiana. La pasta carbonara con la salsa carbonara original la cual tiene queso pecorino,huevos,guancile,pimienta y sal","queso,huevo","19,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"Fainá con carne y berenjena","una clase de pizza elaborada con harina a base de garbanzos que se cree es originaria de Génova. Se pronuncia “farinata”, aunque para el genovés es fainá.","pimienta","22,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"Agnolotti","los “primos” de los raviolis ,  se distinguen por su forma cuadrada y pequeñez. Están rellenos con salsas de carnes de res o de cerdo o de una mezcla de salvia, mantequilla y queso parmesano.","salvia","14,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"espagueti all’amatriciana","Pasta fina con un orificio cubierto de la famosa salsa amatriciana, preparada con una base de guanciale (trozos de carrillo de cerdo) acompañados de tomate, aceite de oliva y queso pecorino rallado.","queso","13,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"La polenta","Plato que bien se puede comer solo o acompañado de pescados, hongos, estofado, mariscos, salames, verduras, tomate o queso. Es una comida muy versátil, al punto de que se puede considerar una clase porcion de pizzas.","mariscos","24,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"Vincisgrassi","Se prepara con láminas cuadradas o rectangulares de pasta al huevo. Las capas estan rellenadas  con una salsa elaborada de varios tipos de carne picada de cerdo, vacuno, salchichas, hígados de pollo, cebolla, apio y zanahoria. Todo con sal y pimienta.","pimienta,apio,cebolla","19,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"Arancini","Son bolas fritas en aceite de oliva elaboradas de arroz, cebolla, carne de cerdo, queso pecorino o parmesano y huevo, que quedan como croquetas.","huevo,queso","19,99");
        anyadePlato(plato, db);
        plato = new Plato(-1,"Guiso a la casteddaia","El guiso a la casteddaia consiste en un plato de pintarroja, un tipo de tiburón gato también llamado gato marino o alitán. Este guiso de tiburón gato se prepara entre vinagre de vino blanco y nueces y se cocina con hojas de laurel, ingrediente que le da un sabor particular. ","nueces","39,99");
        anyadePlato(plato, db);
    }


    private boolean anyadePlato(Plato plato, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(NOMBRE, plato.getNombre());
        cv.put(DESC_PLATO, plato.getDescripcion());
        cv.put(ALERG_PLATO, plato.getAlergenos());
        cv.put(PRECIO, plato.getPrecio());

        long insert = db.insert(CARTA_TABLA, null, cv);
        return insert != -1;
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
