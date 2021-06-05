package com.example.booky;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExampleInstrumentedTest {
    DatabaseHelper db;

    //Este es nuestro usuario de prueba, lo usaremos para asignarle la reserva y el comentario de prueba
    Usuario usuario_prueba = new Usuario(-1, "Usuario de Prueba", "pruebesita", "+34678234567", "usuarioPrueba@gmail.com", false);

    //Este es nuestro plato de prueba, lo usaremos para publicar nuestro comentario de prueba
    Plato platoPrueba = new Plato(-1,"Plato Prueba", "Esto es un plato de prueba :>", "ninguno", "9,99");

    //Esta es nuestra reserva de prueba, la cual asignaremos a nuestro Usuario de Prueba
    Reserva reservaPrueba = new Reserva(-1, 3, 3, 3, 3, "Turno Mañana");

    @Before
    public void init(){
        db = new DatabaseHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
    }

    @Test
    public void A_useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.booky", appContext.getPackageName());
    }

    @Test
    public void B_siSeAñadeUnUsuarioElNumeroDeUsuariosAumenta(){
        int nUsuariosAntes = db.get_lista_usuarios().size();
        db.anyadeUsuario(usuario_prueba); //Añadimos nuestro usuario de prueba a la base de datos
        int nUsuariosAhora = db.get_lista_usuarios().size();
        assertEquals(nUsuariosAntes + 1, nUsuariosAhora);
    }

    @Test
    public void C_siElUsuarioYaEstaNoSeAnyade() {
        List<Usuario> usuariardos = db.get_lista_usuarios();
        Usuario user = usuariardos.get(0); //Tomamos el primer usuario de la lista, no es nuestro usuario de prueba
        assertFalse(db.anyadeUsuario(user));
    }

    @Test
    public void D_siSeAñadeUnPlatoElNumeroDePlatosAumenta() {
        int nPlatosAntes = db.get_lista_platos().size();
        db.anyadePlato(platoPrueba); //Añadimos el plato de prueba
        int nPlatosDespues = db.get_lista_platos().size();
        assertEquals(nPlatosAntes + 1, nPlatosDespues);
    }

    @Test
    public void E_siElPlatoYaEstaNoSeAnyade() {
        List<Plato> platardos = db.get_lista_platos();
        Plato primerPlato = platardos.get(0); //No tiene porque ser nuestro plato de prueba
        assertFalse(db.anyadePlato(primerPlato));
    }

    @Test
    public void F_siSeAñadeUnComentarioAUnPlatoElNumeroDeComentariosAumenta(){
        List<Usuario> usuarios = db.get_lista_usuarios(); //En la lista se encuentran dos usuarios, el administrador (se introduce una vez se inicia la app por primera vez
                                                            // y nuestro usuario de prueba
        Usuario nuestroUsuario = usuarios.get(usuarios.size() - 1); //Nuestro usuario de prueba es el ultimo que añadimos
        int IDPlato = db.getIDPlato(platoPrueba.getNombre());
        int nComentariosAntes = db.getListaComentarios(IDPlato).size();
        Calificacion calificacionPrueba = new Calificacion(-1, nuestroUsuario.getID(), IDPlato, "9", "Comentario de Prueba :>");
        db.anyadeCalificacion(calificacionPrueba);
        int nComentariosDespues = db.getListaComentarios(db.getIDPlato(platoPrueba.getNombre())).size();
        assertEquals(nComentariosAntes + 1, nComentariosDespues);
    }

    @Test
    public void G_siSeAñdeUnaReservaElNumeroDeReservasAumenta(){
        List<Usuario> usuarios = db.get_lista_usuarios(); //En la lista se encuentran dos usuarios, el administrador (se introduce una vez se inicia la app por primera vez
                                                            // y nuestro usuario de prueba
        Usuario nuestroUsuario = usuarios.get(usuarios.size() - 1); //Nuestro usuario de prueba es el ultimo que añadimos
        int nReservasAntes = db.getTodasLasReservas().size();
        db.anyadeReserva(reservaPrueba, nuestroUsuario.getID()); //Le asignamos a nuestro usuario de prueba la reserva de prueba
        int nReservasDespues = db.getTodasLasReservas().size();
        assertEquals(nReservasAntes + 1, nReservasDespues);
    }

    @Test
    public void H_siLaReservaYaEstaNoSeAnyade() {
        List<Reserva> reservas = db.getTodasLasReservas();
        Reserva reserva = reservas.get(0); //No tiene porque ser nuestra reserva de prueba
        assertFalse(db.anyadeReserva(reserva, 1));
    }

    @Test
    public void I_siSeBorraUnPlatoSeBorranSusCalificaciones() {
        int idPlato = db.getIDPlato(platoPrueba.getNombre());
        db.borrarPlato(idPlato); //Borramos nuestro plato de prueba
        List<Calificacion> calificaciones = db.getListaComentarios(idPlato); // La calificacion de prueba es borrada de la base de datos
        assertEquals(0, calificaciones.size());
    }

    @Test
    public void J_siSeBorraUnUsuarioSeBorranSusReservas(){
        List<Usuario> usuarios = db.get_lista_usuarios();
        Usuario nuestroUsuario = usuarios.get(usuarios.size() - 1);
        db.borraUsuario(nuestroUsuario); //Borramos nuestro usuario de prueba
        List<Reserva> reservasUsuario = db.getListaReservas(nuestroUsuario.getID()); //La reserva de prueba del usuario de prueba es borrada de la base de datos
        assertEquals(0, reservasUsuario.size());
    }
}