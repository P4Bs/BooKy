package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ayadirplatoActivity extends AppCompatActivity {

    EditText nombre, descripcion, alergenos , precio;
    Button añadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayadirplato);

        nombre = findViewById(R.id.nombre18);
        descripcion = findViewById(R.id.nombre19);
        alergenos = findViewById(R.id.nombre20);
        precio = findViewById(R.id.nombre21);
        añadir = findViewById(R.id.button4);

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.getText().toString().equals("") || descripcion.getText().toString().equals("")
                        || alergenos.getText().toString().equals("") || precio.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                } else{
                    DatabaseHelper databaseHelper = new DatabaseHelper(ayadirplatoActivity.this);
                    String nombrePlato, descripcionPlato, alergenosPlato, precioPlato;
                    nombrePlato = nombre.getText().toString();
                    descripcionPlato = descripcion.getText().toString();
                    alergenosPlato = alergenos.getText().toString();
                    precioPlato = precio.getText().toString();

                    if(precioPlato.contains(".")){
                        Toast.makeText(getApplicationContext(), "El precio debe expresarse con coma", Toast.LENGTH_SHORT).show();
                    } else if(!precioPlato.equals("")){
                        if (databaseHelper.estaELplato(nombrePlato)) {
                            Toast.makeText(getApplicationContext(), "El Plato indicado ya está en la base de datos", Toast.LENGTH_SHORT).show();
                        } else {
                            Plato nuevoPlato = new Plato(-1, nombrePlato, descripcionPlato, alergenosPlato, precioPlato);
                            databaseHelper.anyadePlato(nuevoPlato);
                            Toast.makeText(getApplicationContext(), "Plato añadido Exitososamente", Toast.LENGTH_SHORT).show();
                            finish(); //CIERRA LA ACTIVIDAD PORQUE EL PLATO FUE AÑADIDO EXITOSAMENTE
                        }
                    }
                }
            }
        });
    }

}