package com.example.booky;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

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
                    try {
                        IDUsuario = datosUsuario.getInt(0);
                        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);

                        double notaUsuario = format.parse(nota).doubleValue();

                        if(notaUsuario > 10){
                           Toast.makeText(getApplicationContext(), "La nota debe ser entre 0 y 10", Toast.LENGTH_SHORT).show();
                        } else {
                            if(nota.charAt(0) == ','){
                                nota = "0" + nota;
                            }
                            Calificacion cali = new Calificacion(-1, IDUsuario, IDPlato, nota, comentario);
                            if (db.anyadeCalificacion(cali)) {
                                Toast.makeText(getApplicationContext(), "Calificacion añadida", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Hubo Un Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (ParseException e) {
                        Toast.makeText(getApplicationContext(), "Por favor, rellene el campo de nota", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}