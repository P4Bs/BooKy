package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuAdminActivity extends AppCompatActivity {

    ImageButton botonPerfil, botonCarta, botonAcciones;
    String emailAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        Intent intent = getIntent();

        botonPerfil = findViewById(R.id.perfilAdmin);
        botonCarta = findViewById(R.id.menu);
        botonAcciones = findViewById(R.id.accionesAdmin);
        emailAdmin = intent.getStringExtra("USUARIO_EMAIL");

        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchPerfilUsuario(emailAdmin);
            }
        });

        botonAcciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAccionesAdmin(emailAdmin);
            }
        });

        botonCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCartaAdmin(emailAdmin, true);
            }
        });
    }

    private void launchPerfilUsuario(String emailAdmin) {
        Intent intent = new Intent(this, PerfilUsuarioActivity.class);
        lanzaActividad(intent, emailAdmin);
    }

    private void launchAccionesAdmin(String emailAdmin) {
        Intent intent = new Intent(this, AdministracionAdminActivity.class);
        lanzaActividad(intent, emailAdmin);
    }

    private void launchCartaAdmin(String emailAdmin, Boolean esAdmin) {
        Intent intent = new Intent(this, CartaAdminActivity.class);
        lanzaActividad(intent, emailAdmin);
    }

    private void lanzaActividad(Intent intent, String emailAdmin){
        intent.putExtra("USUARIO_EMAIL", emailAdmin);
        startActivity(intent);
    }
}