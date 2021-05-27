package com.example.booky;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    EditText email, contrasenya;
    Button registrarse, iniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.nombre);
        contrasenya = findViewById(R.id.contraseña);
        registrarse = findViewById(R.id.registrarse);
        iniciarSesion = findViewById(R.id.iniciarSesion);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRegistrarseActivity();
            }
        });

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("") || contrasenya.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Email o contraseña vacios", Toast.LENGTH_SHORT).show();
                } else{
                    Usuario usuarioIntroducido = new Usuario(-1, email.getText().toString());
                    usuarioIntroducido.setContrasenya(contrasenya.getText().toString());
                    DatabaseHelper databaseHelper = new DatabaseHelper(LogInActivity.this);
                    if(databaseHelper.estaElUsuario(usuarioIntroducido)){
                        Usuario datosUsuario = databaseHelper.getElUsuario(usuarioIntroducido);
                        if(datosUsuario.getContrasenya().equals(usuarioIntroducido.getContrasenya())){
                            Toast.makeText(getApplicationContext(), "Bienvenido " + datosUsuario.getNombre(), Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(getApplicationContext(), "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                        }

                    } else{
                        Toast.makeText(getApplicationContext(), "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*
        @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("") || contrasenya.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Email o contraseña vacios", Toast.LENGTH_SHORT).show();
                } else{
                    Usuario user = new Usuario(-1, email.getText().toString(), contrasenya.getText().toString());
                    DatabaseHelper databaseHelper = new DatabaseHelper(LogInActivity.this);
                    if(databaseHelper.estaElUsuario(user)){
                        Toast.makeText(getApplicationContext(), "El usuario indicado ya se encuentra en la base de datos. Use un correo diferente", Toast.LENGTH_SHORT).show();
                    } else{
                        boolean correcto = databaseHelper.anyadeUsuario(user);
                        if(correcto){
                            Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
         */
    }

    public void launchRegistrarseActivity(){
        Intent intent = new Intent(this, RegistrarseActivity.class);
        startActivity(intent);
    }
}
