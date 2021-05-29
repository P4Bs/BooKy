package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class AdministracionAdminActivity extends AppCompatActivity {

    ListView hola;
    private void recarga(DatabaseHelper ayuda){
        List<Usuario> todos_usuarios = ayuda.get_lista_usuarios();
        ArrayAdapter usuarioArray = new ArrayAdapter<Usuario>(AdministracionAdminActivity.this, android.R.layout.simple_list_item_1, todos_usuarios);
        hola.setAdapter(usuarioArray);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion_admin);

        hola = findViewById(R.id.hola);

        DatabaseHelper baseDeDatos = new DatabaseHelper(AdministracionAdminActivity.this);

        recarga(baseDeDatos);

        hola.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario a = (Usuario) parent.getItemAtPosition(position);
                baseDeDatos.borraUsuario(a);
                recarga(baseDeDatos);
            }
        });

    }
}