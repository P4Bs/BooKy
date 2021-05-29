package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class ReservaActivity extends AppCompatActivity {

    EditText num_ocupantes, num_mesa;
    RadioButton turno1 ,turno2;
    Button añadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        num_ocupantes = findViewById(R.id.numeroComensales);
        num_mesa = findViewById(R.id.numeroMesa);
        turno1 = findViewById(R.id.turnoNoche);
        turno2 = findViewById(R.id.turnoMañana);
        añadir = findViewById(R.id.botonReserva);

       /* añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num_ocupantes.getText().toString().equals("") || num_mesa.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                }else if(Integer.valueOf(num_mesa.getText().toString()) > 5 || Integer.valueOf(num_mesa.getText().toString()) < 0){
                    Toast.makeText(getApplicationContext(), "Solo tenemos 5 mesas", Toast.LENGTH_SHORT).show();
                }else{
                    DatabaseHelper databaseHelper = new DatabaseHelper(ReservaActivity.this);
                    String num_ocupantes_reserva, num_mesa_reserva,turno_reserva;
                    num_ocupantes_reserva = num_ocupantes.getText().toString();
                    num_mesa_reserva = num_mesa.getText().toString();
                    if(turno1.isChecked()){
                        turno_reserva = "Turno Mañana";
                    }else if(turno2.isChecked()){
                        turno_reserva = "Turno Noche";
                    }

                    if(databaseHelper.estaELplato(nombreplato)){
                        Toast.makeText(getApplicationContext(), "El Plato indicado ya está en la base de datos", Toast.LENGTH_SHORT).show();
                    } else{
                        Plato nuevoPlato = new Plato(-1,nombreplato,descripcionplato,alergenosplatos,Integer.parseInt(precioplato));
                        databaseHelper.anyadePlato(nuevoPlato);
                        Toast.makeText(getApplicationContext(), "Plato añadido Exitososamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
*/

    }
}