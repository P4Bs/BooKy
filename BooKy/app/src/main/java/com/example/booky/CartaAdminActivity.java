package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class CartaAdminActivity extends AppCompatActivity {

    ListView lv_platoslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta_admin);

        lv_platoslist = findViewById(R.id.lv_platoslist);

        DatabaseHelper baseDeDatos = new DatabaseHelper(CartaAdminActivity.this);
        List<Plato> todos_platos = baseDeDatos.get_lista_platos();

        ArrayAdapter platosArray = new ArrayAdapter<Plato>(CartaAdminActivity.this, android.R.layout.simple_list_item_1, todos_platos);
        //lv_Plat

        Toast.makeText(CartaAdminActivity.this,todos_platos.toString(),Toast.LENGTH_SHORT).show();
    }
}