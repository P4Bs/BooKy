package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PerfilUsuarioActivity extends AppCompatActivity {

    String emailUsuario, nombre, contraseña, numTelf;
    int ID;
    TextView cuadroID, cuadroEmail;
    EditText editableNombre, editableContraseña, editableTelefono;
    Button actualizarCambios;
    ListView listaReservas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        cuadroID = findViewById(R.id.IDUsuarioText);
        editableNombre = findViewById(R.id.nombreUsuariardo);
        editableContraseña = findViewById(R.id.contraseñaUsuario);
        editableTelefono = findViewById(R.id.telfUsuario);
        cuadroEmail = findViewById(R.id.emailUsuario);
        actualizarCambios = findViewById(R.id.actualizarDatos);
        listaReservas = findViewById(R.id.listaReservas);

        DatabaseHelper baseDeDatos = new DatabaseHelper(PerfilUsuarioActivity.this);
        Intent intent = getIntent();
        emailUsuario = intent.getStringExtra("USUARIO_EMAIL");
        Cursor datosUsuario = baseDeDatos.getDatosUsuario(emailUsuario);

        if(datosUsuario.moveToFirst()) {
            ID = datosUsuario.getInt(0);
            nombre = datosUsuario.getString(1);
            numTelf = datosUsuario.getString(3);

            cuadroID.setText(Integer.toString(ID));
            editableNombre.setText(nombre);
            cuadroEmail.setText(emailUsuario);
            editableTelefono.setText(numTelf);
        }

        cargaReservas(baseDeDatos, ID);

        actualizarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(PerfilUsuarioActivity.this);
                if(!editableContraseña.getText().toString().equals("")){
                    Usuario userNuevaContraseña = new Usuario(ID, null, editableContraseña.getText().toString());
                    db.cambiarContrasenyaUsuario(userNuevaContraseña);
                    editableContraseña.setText("");
                }

                if(!editableNombre.getText().toString().equals(nombre)){
                    Usuario userNuevoNombre = new Usuario(ID, null, "1");
                    userNuevoNombre.setNombre(editableNombre.getText().toString());
                    db.cambiarNombreUsuario(userNuevoNombre);
                }

                if(!editableTelefono.getText().toString().equals(numTelf)){
                    Usuario userNuevoTelefono = new Usuario(ID, null, "1");
                    userNuevoTelefono.setNumTelefono(editableTelefono.getText().toString());
                    db.cambiarTelefonoUsuario(userNuevoTelefono);
                }

                Toast.makeText(getApplicationContext(), "Datos actualizados", Toast.LENGTH_LONG).show();
            }
        });

        listaReservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reserva reservariarda = (Reserva) parent.getItemAtPosition(position);
                baseDeDatos.borrarReserva(reservariarda);;
                Toast.makeText(getApplicationContext(), "Reserva Eliminada", Toast.LENGTH_SHORT).show();
                cargaReservas(baseDeDatos, ID);
            }
        });
    }

    public void cargaReservas(DatabaseHelper db, int IDUsuario){
        List<Reserva> todasReservas = db.getListaReservas(IDUsuario);
        ArrayAdapter reservasArray = new ArrayAdapter<>(PerfilUsuarioActivity.this, android.R.layout.simple_list_item_1, todasReservas);
        listaReservas.setAdapter(reservasArray);
    }
}