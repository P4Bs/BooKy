package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class PlatoUsuarioActivity extends AppCompatActivity {

    String nombrePlato;
    TextView cuadroNombrePlato, cuadroDescripcionPlato, alergenosPlato, cuadroPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato_usuario);

        DatabaseHelper baseDeDatos = new DatabaseHelper(PlatoUsuarioActivity.this);
        cuadroNombrePlato = findViewById(R.id.nombrePlato);
        cuadroDescripcionPlato = findViewById(R.id.descripcionPlato);
        cuadroPrecio = findViewById(R.id.precioPlato);
        alergenosPlato = findViewById(R.id.alergenosPlato);

        Intent intent = getIntent();

        nombrePlato = intent.getStringExtra("NOMBRE_PLATO");
        Cursor plato = baseDeDatos.getPlato(nombrePlato);

        if(plato.moveToFirst()){
            cuadroNombrePlato.setText();
        }

    }
}