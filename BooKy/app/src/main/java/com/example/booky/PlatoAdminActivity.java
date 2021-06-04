package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PlatoAdminActivity extends AppCompatActivity {

    String nombrePlato, descripcionPlato, alergenosPlato, precioPlato;
    int IDPlato;
    TextView cuadroNombrePlato, cuadroDescripcionPlato, cuadroAlergenosPlato, cuadroPrecio;
    ListView lv_comentarios;
    Button borraPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato_admin);

        DatabaseHelper baseDeDatos = new DatabaseHelper(PlatoAdminActivity.this);
        cuadroNombrePlato = findViewById(R.id.nombrePlatoAdmin);
        cuadroDescripcionPlato = findViewById(R.id.descripcionPlatoAdmin);
        cuadroPrecio = findViewById(R.id.precioPlatoAdmin);
        cuadroAlergenosPlato = findViewById(R.id.alergenosPlatoAdmin);
        borraPlato = findViewById(R.id.borrarPlato);
        lv_comentarios = findViewById(R.id.listaComentariosAdmin);

        cuadroDescripcionPlato.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();

        IDPlato = intent.getIntExtra("ID_PLATO", 0);
        Cursor plato = baseDeDatos.getPlato(IDPlato);

        if(plato.moveToFirst()){
            IDPlato = plato.getInt(0);
            nombrePlato = plato.getString(1);
            descripcionPlato = plato.getString(2);
            alergenosPlato = plato.getString(3);
            precioPlato = plato.getString(4);

            cuadroNombrePlato.setText(nombrePlato);
            cuadroDescripcionPlato.setText(descripcionPlato);
            cuadroAlergenosPlato.setText("Alergenos: " + alergenosPlato);
            cuadroPrecio.setText("Precio: " + precioPlato + "€");

            List<Calificacion> calficacionesPlato = baseDeDatos.getListaComentarios(IDPlato);

            ArrayAdapter calificacionesArray = new ArrayAdapter<>(PlatoAdminActivity.this, android.R.layout.simple_list_item_1, calficacionesPlato);
            lv_comentarios.setAdapter(calificacionesArray);
        }

        lv_comentarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Calificacion calificacion = (Calificacion) parent.getItemAtPosition(position);
                int IDCalificacion = calificacion.getID();
                launchCalificacionActivity(IDCalificacion);
            }
        });

        borraPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDeDatos.borrarPlato(IDPlato);
                Toast.makeText(getApplicationContext(), "Plato Eliminado", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void launchCalificacionActivity(int IDCalif) {
        Intent intent = new Intent(this, ComentarioVerActivity.class);
        intent.putExtra("ID_CALIFICACION", IDCalif);
        startActivity(intent);
    }
}