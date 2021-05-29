package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class AdministracionAdminActivity extends AppCompatActivity {

    ListView hola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion_admin);

        hola = findViewById(R.id.hola);

        DatabaseHelper baseDeDatos = new DatabaseHelper(AdministracionAdminActivity.this);
        List<Usuario> todos_usuarios = baseDeDatos.get_lista_usuarios();

        ArrayAdapter usuarioArray = new ArrayAdapter<Usuario>(AdministracionAdminActivity.this, android.R.layout.simple_list_item_1, todos_usuarios);
        hola.setAdapter(usuarioArray);

    }
}