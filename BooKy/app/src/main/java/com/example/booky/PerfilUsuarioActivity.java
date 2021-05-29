package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class PerfilUsuarioActivity extends AppCompatActivity {

    String emailUsuario, nombre, ID, contraseña, numTelf;
    TextView cuadroID;
    EditText cuadroNombre, cuadroContraseña, cuadroTelefono, cuadroEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        cuadroID = findViewById(R.id.IDUsuario);
        cuadroNombre = findViewById(R.id.nombreUsuariardo);
        cuadroContraseña = findViewById(R.id.contraseñaUsuario);
        cuadroTelefono = findViewById(R.id.telfUsuario);
        cuadroEmail = findViewById(R.id.emailUsuario);


        Intent intent = getIntent();
        emailUsuario = intent.getStringExtra("USUARIO_EMAIL");

        DatabaseHelper baseDeDatos = new DatabaseHelper(PerfilUsuarioActivity.this);

        Cursor datosUsuario = baseDeDatos.getDatosUsuario(emailUsuario);

        ID = datosUsuario.getString(0);
        nombre = datosUsuario.getString(1);
        numTelf = datosUsuario.getString(3);

        cuadroID.setText(ID);
        cuadroNombre.setText(nombre);
        cuadroEmail.setText(emailUsuario);
        cuadroTelefono.setText(numTelf);
    }
}