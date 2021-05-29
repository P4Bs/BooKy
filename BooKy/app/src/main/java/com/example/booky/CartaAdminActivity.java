package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class CartaAdminActivity extends AppCompatActivity {

    ListView lv_platoslist;
    Button boton_añadir;
    String plato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta_admin);

        lv_platoslist = findViewById(R.id.lv_platoslist);

        DatabaseHelper baseDeDatos = new DatabaseHelper(CartaAdminActivity.this);
        List<Plato> todos_platos = baseDeDatos.get_lista_platos();

        ArrayAdapter platosArray = new ArrayAdapter<Plato>(CartaAdminActivity.this, android.R.layout.simple_list_item_1, todos_platos);
        lv_platoslist.setAdapter(platosArray);

        Toast.makeText(CartaAdminActivity.this,todos_platos.toString(),Toast.LENGTH_SHORT).show();

        boton_añadir = findViewById(R.id.Añadir_plato);
        boton_añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchayadirplatoActivity(plato);
            }
        });
    }

    public void launchayadirplatoActivity(String plato){
        Intent intent = new Intent(this, ayadirplatoActivity.class);
        lanzaActividad(intent);
    }

    private void lanzaActividad(Intent intent){
        intent.putExtra("USUARIO_CORREO", plato);
        startActivity(intent);
    }

}