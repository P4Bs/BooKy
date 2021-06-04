package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ComentarioActivity extends AppCompatActivity {

    EditText cuadroNota, cuadroComentario;
    Button añadirComentario;
    String emailUsuario;
    int IDPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        cuadroNota = findViewById(R.id.calificacionComentario);
        cuadroComentario = findViewById(R.id.comentarioComentario);
        añadirComentario = findViewById(R.id.introduceElComentario);

        cuadroComentario.setMovementMethod(new ScrollingMovementMethod());

        DatabaseHelper db = new DatabaseHelper(ComentarioActivity.this);
        Intent intent = getIntent();

        emailUsuario = intent.getStringExtra("USUARIO_EMAIL");
        IDPlato = intent.getIntExtra("ID_PLATO", 0);

        añadirComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nota = cuadroNota.getText().toString();
                String comentario = cuadroComentario.getText().toString();
                int IDUsuario;

                Cursor datosUsuario = db.getDatosUsuario(emailUsuario);
                if(datosUsuario.moveToFirst()){
                    IDUsuario = datosUsuario.getInt(0);
                    if(nota.contains(".")) {
                        Toast.makeText(getApplicationContext(), "La calificacion debe ser con coma", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Calificacion cali = new Calificacion(-1, IDUsuario, IDPlato, nota, comentario);
                        if(db.anyadeCalificacion(cali)){
                            Toast.makeText(getApplicationContext(), "Calificacion añadida", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(getApplicationContext(), "Hubo Un Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}