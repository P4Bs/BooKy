package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class CartaUsuarioActivity extends AppCompatActivity {

    int IDPlato;
    ListView lv_platoslist;
    String emailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta_usuario);
        lv_platoslist = findViewById(R.id.lv_platoslist);

        Intent intent = getIntent();
        emailUsuario = intent.getStringExtra("USUARIO_EMAIL");

        DatabaseHelper baseDeDatos = new DatabaseHelper(CartaUsuarioActivity.this);
        List<Plato> todos_platos = baseDeDatos.get_lista_platos();

        ArrayAdapter platosArray = new ArrayAdapter<Plato>(CartaUsuarioActivity.this, android.R.layout.simple_list_item_1, todos_platos);
        lv_platoslist.setAdapter(platosArray);


        lv_platoslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Plato a = (Plato) parent.getItemAtPosition(position);
                IDPlato = a.getID();
                launchPlatoUsuarioActivity(IDPlato);
            }
        });
    }

    public void launchPlatoUsuarioActivity(int plato){
        Intent intent = new Intent(this, PlatoUsuarioActivity.class);
        intent.putExtra("USUARIO_EMAIL", emailUsuario);
        intent.putExtra("ID_PLATO", plato);
        lanzaActividad(intent);
    }

    private void lanzaActividad(Intent intent){
        startActivity(intent);
    }

}