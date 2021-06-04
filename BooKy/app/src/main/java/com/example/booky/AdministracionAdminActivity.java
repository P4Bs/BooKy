package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class AdministracionAdminActivity extends AppCompatActivity {

    ListView usuarios, reservas;
    String correoUsario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion_admin);

        usuarios = findViewById(R.id.hola);
        reservas = findViewById(R.id.reservasView);

        DatabaseHelper baseDeDatos = new DatabaseHelper(AdministracionAdminActivity.this);
        Intent intent = getIntent();
        correoUsario = intent.getStringExtra("USUARIO_EMAIL");

        recarga(baseDeDatos);

        usuarios.setOnItemClickListener((parent, view, position, id) -> {
            Usuario usuariardo = (Usuario) parent.getItemAtPosition(position);
            if(usuariardo.getCorreo().compareTo(correoUsario) == 0){
                Toast.makeText(getApplicationContext(), "No puedes eliminarte a ti mismo", Toast.LENGTH_SHORT).show();
            } else{
                baseDeDatos.borraUsuario(usuariardo);
                Toast.makeText(getApplicationContext(), "Usuario Eliminado", Toast.LENGTH_SHORT).show();
                recarga(baseDeDatos);
            }
        });

        reservas.setOnItemClickListener((parent, view, position, id) -> {
            Reserva reservariarda = (Reserva) parent.getItemAtPosition(position);
            baseDeDatos.borrarReserva(reservariarda);;
            Toast.makeText(getApplicationContext(), "Reserva Eliminada", Toast.LENGTH_SHORT).show();
            recarga(baseDeDatos);
        });
    }

    private void recarga(DatabaseHelper ayuda){
        List<Usuario> todos_usuarios = ayuda.get_lista_usuarios();
        ArrayAdapter usuarioArray = new ArrayAdapter<>(AdministracionAdminActivity.this, android.R.layout.simple_list_item_1, todos_usuarios);
        usuarios.setAdapter(usuarioArray);

        List<Reserva> todasLasReservas = ayuda.getTodasLasReservas();
        ArrayAdapter reservasArray = new ArrayAdapter<>(AdministracionAdminActivity.this, android.R.layout.simple_list_item_1, todasLasReservas);
        reservas.setAdapter(reservasArray);
    }
}