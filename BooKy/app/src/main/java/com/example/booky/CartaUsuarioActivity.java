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

    String plato;
    ListView lv_platoslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta_usuario);
        lv_platoslist = findViewById(R.id.lv_platoslist);

        DatabaseHelper baseDeDatos = new DatabaseHelper(CartaUsuarioActivity.this);
        List<Plato> todos_platos = baseDeDatos.get_lista_platos();

        ArrayAdapter platosArray = new ArrayAdapter<Plato>(CartaUsuarioActivity.this, android.R.layout.simple_list_item_1, todos_platos);
        lv_platoslist.setAdapter(platosArray);


        lv_platoslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Plato a = (Plato) parent.getItemAtPosition(position);
                plato = a.getNombre();
                launchPlatoUsuarioActivity(plato);
            }
        });


    }

    public void launchPlatoUsuarioActivity(String plato){
        Intent intent = new Intent(this, PlatoUsuarioActivity.class);
        lanzaActividad(intent);
    }

    private void lanzaActividad(Intent intent){
        intent.putExtra("NOMBRE_PLATO", plato);
        startActivity(intent);
    }

}