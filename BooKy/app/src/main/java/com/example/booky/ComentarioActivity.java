package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ComentarioActivity extends AppCompatActivity {

    EditText nota, comentario;
    Button añadirComentario;
    String emailUsuario;
    int IDPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        nota = findViewById(R.id.calificacionComentario);
        comentario = findViewById(R.id.comentarioComentario);
        añadirComentario = findViewById(R.id.introduceElComentario);

        Intent intent = getIntent();

        emailUsuario = intent.getStringExtra("USUARIO_EMAIL");
        IDPlato = intent.getIntExtra("ID_PLATO", 0);

        añadirComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c
            }
        });
    }
}