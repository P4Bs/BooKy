package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;


public class ComentarioVerActivity extends AppCompatActivity {

    TextView notaComentario, Comentario, usuario, plato;
    int idComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario_ver);

        DatabaseHelper db = new DatabaseHelper(ComentarioVerActivity.this);
        Intent intent = getIntent();
        idComentario = intent.getIntExtra("ID_CALIFICACION", 0);

        notaComentario = findViewById(R.id.notaComentario);
        Comentario = findViewById(R.id.comentarioVerComentario);
        usuario = findViewById(R.id.autorComentario);
        plato = findViewById(R.id.platoComentario);

        Calificacion comentario = db.getCalificacion(idComentario);
        Cursor usuarioDatos = db.getDatosUsuario(comentario.getIDUsuario());
        Cursor platoDatos = db.getPlato(comentario.getIDPlato());
        usuarioDatos.moveToFirst();
        platoDatos.moveToFirst();
        String nombreUsuario = usuarioDatos.getString(1);
        String nombrePlato = platoDatos.getString(1);

        notaComentario.setText(comentario.getNota());
        Comentario.setText(comentario.getComentario());
        usuario.setText(nombreUsuario);
        plato.setText(nombrePlato);
    }
}