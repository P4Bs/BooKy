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
                launchPerfilAdmin(emailAdmin);
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

    private void launchPerfilAdmin(String emailAdmin) {
        Intent intent = new Intent(this, PerfilAdminActivity.class);
        lanzaPerfilAdmin_Acciones(intent, emailAdmin);
    }

    private void launchAccionesAdmin(String emailAdmin) {
        Intent intent = new Intent(this, AdministracionAdminActivity.class);
        lanzaPerfilAdmin_Acciones(intent, emailAdmin);
    }

    private void launchCartaAdmin(String emailAdmin, Boolean esAdmin) {
        Intent intent = new Intent(this, CartaActivity.class);
        lanzaCarta(intent, emailAdmin);
    }

    private void lanzaPerfilAdmin_Acciones(Intent intent, String emailAdmin){
        intent.putExtra("EMAIL_USUARIO", emailAdmin);
        startActivity(intent);
    }

    private void lanzaCarta(Intent intent, String emailAdmin) {
        intent.putExtra("EMAIL_USUARIO", emailAdmin);
        intent.putExtra("ES_ADMIN", true);
        startActivity(intent);
    }
}