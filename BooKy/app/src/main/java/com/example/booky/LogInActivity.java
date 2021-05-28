package com.example.booky;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
                    String emailUsuario = email.getText().toString();
                    String contrasenyaUsuario = getSHA1(contrasenya.getText().toString());
                    DatabaseHelper baseDeDatos = new DatabaseHelper(LogInActivity.this);
                    String esAdmin = baseDeDatos.esAdmin(emailUsuario, contrasenyaUsuario);

                    if(esAdmin == null) {
                        Toast.makeText(getApplicationContext(), "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                        if(esAdmin == "1"){ //
                            //TODO: ABRIR VISTA ADMIN
                        } else{
                            //TODO : ABRIR VISA USURIOs
                        }
                    }
                }
            }
        });
    }

    public void launchRegistrarseActivity(){
        Intent intent = new Intent(this, RegistrarseActivity.class);
        startActivity(intent);
    }

    private static String getSHA1(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
