package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

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

        Toast.makeText(getApplicationContext(), emailUsuario, Toast.LENGTH_SHORT).show();

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
        lanzaActividad(intent);
    }

    public void launchPerfilUsuarioActivity(String emailUsuario){
        Intent intent = new Intent(this, PerfilUsuarioActivity.class);
        lanzaActividad(intent);
    }

    public void launchCartaActivity(String emailUsuario){
        Intent intent = new Intent(this, CartaUsuarioActivity.class);
        lanzaActividad(intent);
    }

    private void lanzaActividad(Intent intent){
        intent.putExtra("USUARIO_EMAIL", emailUsuario);
        startActivity(intent);
    }
}