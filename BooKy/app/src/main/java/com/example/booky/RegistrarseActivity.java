package com.example.booky;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarseActivity extends AppCompatActivity {

    EditText nombre, email, contraseña, telefono;
    Button registrarse;
    Switch soyAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);


        nombre = findViewById(R.id.nombre);
        email = findViewById(R.id.email);
        contraseña = findViewById(R.id.contraseña);
        telefono = findViewById(R.id.telefono);
        registrarse = findViewById(R.id.registrarse);
        soyAdmin = findViewById(R.id.soyAdmin);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.getText().toString().equals("") || email.getText().toString().equals("")
                    || contraseña.getText().toString().equals("") || telefono.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                } else{
                    DatabaseHelper databaseHelper = new DatabaseHelper(RegistrarseActivity.this);
                    Usuario usuario = new Usuario(-1, email.getText().toString());
                    usuario.setContrasenya(contraseña.getText().toString());
                    usuario.setNombre(nombre.getText().toString());
                    usuario.setNumTelefono(telefono.getText().toString());
                    if(soyAdmin.isChecked()){
                        usuario.setEsAdmin(true);
                    } else{
                        usuario.setEsAdmin(false);
                    }

                    if(databaseHelper.estaElUsuario(usuario)){
                        Toast.makeText(getApplicationContext(), "El usuario indicado ya está en la base de datos", Toast.LENGTH_SHORT).show();
                    } else{
                        databaseHelper.anyadeUsuario(usuario);
                        Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}