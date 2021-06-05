package com.example.booky;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    DatabaseHelper db;

    @Before
    public void init(){
        db = new DatabaseHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.booky", appContext.getPackageName());
    }

    @Test
    public void siElPlatoYaEstaNoSeAnyade() {
        List<Plato> platardos = db.get_lista_platos();
        Plato primerPlato = platardos.get(0);
        assertFalse(db.anyadePlato(primerPlato));
    }

    @Test
    public void siElUsuarioYaEstaNoSeAnyade() {
        List<Usuario> usuariardos = db.get_lista_usuarios();
        Usuario user = usuariardos.get(0);
        assertFalse(db.anyadeUsuario(user));
    }

    @Test
    public void siLaReservaYaEstaNoSeAnyade() {
        List<Reserva> reservas = db.getTodasLasReservas();
        Reserva reserva = reservas.get(0);
        assertFalse(db.anyadeReserva(reserva, 1));
    }

    @Test
    public void elComentarioSeAnyade {
        List<Usuario> usuarios = db.get_lista_usuarios();

    }
}