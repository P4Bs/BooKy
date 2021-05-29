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
        botonUsuario = findViewById(R.id.perfilUsuario);
        botonReserva = findViewById(R.id.reservas);
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
                launchUsuarioActivity(emailUsuario);
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
        iniciarActivity(intent);
    }

    public void launchUsuarioActivity(String emailUsuario){
        Intent intent = new Intent(this, PerfilUsuarioActivity.class):
        iniciarActivity(intent);
    }

    public void launchCartaActivity(String emailUsuario){
        Intent intent = new Intent(this, CartaActivity.class);
        iniciarActivity(intent);
    }

    private void iniciarActivity(Intent intent){
        intent.putExtra("USUARIO_CORREO", emailUsuario);
        intent.putExtra("ES_ADMIN", false);
        startActivity(intent);
    }

}