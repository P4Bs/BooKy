package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuUsuarioActivity extends AppCompatActivity {

    ImageButton botonCarta, botonUsuario, botonReserva;
    String emailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);

        Intent intent = getIntent();
        botonCarta = findViewById(R.id.menu);
        botonUsuario = findViewById(R.id.perfilAdmin);
        botonReserva = findViewById(R.id.accionesAdmin);
        emailUsuario = intent.getStringExtra("USUARIO_EMAIL");

        botonReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchReservaActivity(emailUsuario);
            }
        });

        botonUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchPerfilUsuarioActivity(emailUsuario);
            }
        });

        botonCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCartaActivity(emailUsuario);
            }
        });
    }

    public void launchReservaActivity(String emailUsuario){
        Intent intent = new Intent(this, ReservaActivity.class);
        iniciarReserva_Perfil(intent);
    }

    public void launchPerfilUsuarioActivity(String emailUsuario){
        Intent intent = new Intent(this, PerfilUsuarioActivity.class);
        iniciarReserva_Perfil(intent);
    }

    public void launchCartaActivity(String emailUsuario){
        Intent intent = new Intent(this, CartaActivity.class);
        iniciarCarta(intent);
    }

    private void iniciarReserva_Perfil(Intent intent){
        intent.putExtra("USUARIO_CORREO", emailUsuario);
        startActivity(intent);
    }

    private void iniciarCarta(Intent intent){
        intent.putExtra("USUARIO_CORREO", emailUsuario);
        intent.putExtra("ES_ADMIN", false);
        startActivity(intent);
    }
}