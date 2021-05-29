package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class PlatoUsuarioActivity extends AppCompatActivity {

    String nombrePlato, descripcionPlato, alergenosPlato, emailUsuario;
    int precioPlato, IDplato;
    TextView cuadroNombrePlato, cuadroDescripcionPlato, cuadroAlergenosPlato, cuadroPrecio;
    ListView lv_comentarios;
    Button añadeComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato_usuario);

        DatabaseHelper baseDeDatos = new DatabaseHelper(PlatoUsuarioActivity.this);
        cuadroNombrePlato = findViewById(R.id.nombrePlato);
        cuadroDescripcionPlato = findViewById(R.id.descripcionPlato);
        cuadroPrecio = findViewById(R.id.precioPlato);
        cuadroAlergenosPlato = findViewById(R.id.alergenosPlato);
        lv_comentarios = findViewById(R.id.listaComentarios);
        añadeComentario = findViewById(R.id.añadeComentario);

        Intent intent = getIntent();
        nombrePlato = intent.getStringExtra("ID_PLATO");
        emailUsuario = intent.getStringExtra("USUARIO_EMAIL");

        Cursor plato = baseDeDatos.getPlato(nombrePlato);

        if(plato.moveToFirst()){
            IDplato = plato.getInt(0);
            descripcionPlato = plato.getString(2);
            alergenosPlato = plato.getString(3);
            precioPlato = plato.getInt(4);
            String precioFormateado = formateaElPrecio(precioPlato);

            cuadroNombrePlato.setText(nombrePlato);
            cuadroDescripcionPlato.setText("Descripcion:  " + descripcionPlato);
            cuadroAlergenosPlato.setText("Alergenos: " + alergenosPlato);
            cuadroPrecio.setText("Precio: " + precioFormateado+ "€");

            List<Calificacion> calficacionesPlato = baseDeDatos.getListaComentarios(IDplato);

            ArrayAdapter calificacionesArray = new ArrayAdapter<Calificacion>(PlatoUsuarioActivity.this, android.R.layout.simple_list_item_1, calficacionesPlato);
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

        añadeComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCreaCalificacionActivity();
            }
        });
    }

    public void launchCreaCalificacionActivity(){
        Intent intent = new Intent(this, CrearCalificacionActivity.class);
        intent.putExtra("ID_PLATO", IDplato);
        intent.putExtra("USUARIO_EMAIL", emailUsuario);
        iniciaActividad(intent);
    }

    public void launchCalificacionActivity(int IDCalif) {
        Intent intent = new Intent(this, CalificacionActivity.class);
        intent.putExtra("ID_CALIFICACION", IDCalif);
        iniciaActividad(intent);
    }

    private void iniciaActividad(Intent intent){
        startActivity(intent);
    }

    private String formateaElPrecio(int precio){
        String precioOriginal = Integer.toString(precio);
        String precioFinal = precioOriginal.substring(0, precioOriginal.length() - 2);
        precioFinal += ",";
        precioFinal += precioOriginal.substring(precioOriginal.length()-2, precioOriginal.length());
        return precioFinal;
    }
}