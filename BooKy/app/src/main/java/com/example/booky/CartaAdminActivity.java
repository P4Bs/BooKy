package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class CartaAdminActivity extends AppCompatActivity {

    ListView lv_platoslist;
    Button boton_añadir;
    String plato;
    int IDPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta_admin);

        lv_platoslist = findViewById(R.id.lv_platoslist);
        boton_añadir = findViewById(R.id.Añadir_plato);

        DatabaseHelper baseDeDatos = new DatabaseHelper(CartaAdminActivity.this);
        actualizaPlatos(baseDeDatos);


        lv_platoslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Plato a = (Plato) parent.getItemAtPosition(position);
                IDPlato = a.getID();
                launchPlatoAdminActivity(plato);
            }
        });

        boton_añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchayadirplatoActivity(plato);
                actualizaPlatos(baseDeDatos);
            }
        });
    }

    public void launchayadirplatoActivity(String plato){
        Intent intent = new Intent(this, ayadirplatoActivity.class);
        startActivity(intent);
    }

    public void launchPlatoAdminActivity(String plato){
        Intent intent = new Intent(this, PlatoAdminActivity.class);
        intent.putExtra("ID_PLATO", IDPlato);
        startActivity(intent);
    }

    public void actualizaPlatos(DatabaseHelper db){
        List<Plato> todos_platos = db.get_lista_platos();
        ArrayAdapter platosArray = new ArrayAdapter<Plato>(CartaAdminActivity.this, android.R.layout.simple_list_item_1, todos_platos);
        lv_platoslist.setAdapter(platosArray);
    }
}