package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilUsuarioActivity extends AppCompatActivity {

    String emailUsuario, nombre, contraseña, numTelf;
    int ID;
    TextView cuadroID, cuadroEmail;
    EditText editableNombre, editableContraseña, editableTelefono;
    Button actualizarCambios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        cuadroID = findViewById(R.id.IDUsuarioText);
        editableNombre = findViewById(R.id.nombreUsuariardo);
        editableContraseña = findViewById(R.id.contraseñaUsuario);
        editableTelefono = findViewById(R.id.telfUsuario);
        cuadroEmail = findViewById(R.id.emailUsuario);
        actualizarCambios = findViewById(R.id.actualizarDatos);

        Intent intent = getIntent();
        emailUsuario = intent.getStringExtra("USUARIO_EMAIL");

        Toast.makeText(getApplicationContext(), emailUsuario, Toast.LENGTH_SHORT).show();
        DatabaseHelper baseDeDatos = new DatabaseHelper(PerfilUsuarioActivity.this);

        Cursor datosUsuario = baseDeDatos.getDatosUsuario(emailUsuario);

        if(datosUsuario.moveToFirst()) {
            ID = datosUsuario.getInt(0);
            nombre = datosUsuario.getString(1);
            numTelf = datosUsuario.getString(3);

            cuadroID.setText(Integer.toString(ID));
            editableNombre.setText(nombre);
            cuadroEmail.setText(emailUsuario);
            editableTelefono.setText(numTelf);
        }

        actualizarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(PerfilUsuarioActivity.this);
                if(!editableContraseña.getText().toString().equals("")){
                    Usuario userNuevaContraseña = new Usuario(ID, null, editableContraseña.getText().toString());
                    db.cambiarContrasenyaUsuario(userNuevaContraseña);
                }

                if(!editableNombre.getText().toString().equals(nombre)){
                    Usuario userNuevoNombre = new Usuario(ID, null, "1");
                    userNuevoNombre.setNombre(editableNombre.getText().toString());
                    db.cambiarNombreUsuario(userNuevoNombre);
                }

                if(!editableTelefono.getText().toString().equals(numTelf)){
                    Usuario userNuevoTelefono = new Usuario(ID, null, "1");
                    userNuevoTelefono.setNumTelefono(editableTelefono.getText().toString());
                    db.cambiarTelefonoUsuario(userNuevoTelefono);
                }

                Toast.makeText(getApplicationContext(), "LOS DATOS FUERON ACTUALIZADOS CORRECTAMENTE", Toast.LENGTH_LONG).show();
            }
        });
    }
}