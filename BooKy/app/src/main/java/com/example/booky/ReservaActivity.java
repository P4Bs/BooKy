package com.example.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ReservaActivity extends AppCompatActivity {

    EditText num_ocupantes, num_mesa, dia_Mes, mes;
    RadioButton turnoMañana ,turnoTarde;
    Button añadir;
    String emailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        num_ocupantes = findViewById(R.id.numeroComensales);
        num_mesa = findViewById(R.id.numeroMesa);
        dia_Mes = findViewById(R.id.diaMes);
        mes = findViewById(R.id.numeroMes);
        turnoMañana = findViewById(R.id.turnoNoche);
        turnoTarde = findViewById(R.id.turnoMañana);
        añadir = findViewById(R.id.botonReserva);

        DatabaseHelper db = new DatabaseHelper(ReservaActivity.this);
        Intent intent = getIntent();
        emailUsuario = intent.getStringExtra("USUARIO_EMAIL");

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nOcupantes, diaMes, Mes, mesa, IDUsuario;
                String turno;

                try{
                    nOcupantes = Integer.parseInt(num_ocupantes.getText().toString());
                    diaMes = Integer.parseInt(dia_Mes.getText().toString());
                    Mes = Integer.parseInt(mes.getText().toString());
                    mesa = Integer.parseInt(num_mesa.getText().toString());

                    if(turnoMañana.isChecked()){
                        turno = "Turno Mañana";
                    } else{
                        turno = "Turno Tarde";
                    }

                    if(mesa < 1 || mesa > 5){
                        Toast.makeText(getApplicationContext(), "Pon bien el numero de mesa, bobo", Toast.LENGTH_SHORT).show();
                    } else{
                        if(!estaBienLaFecha(diaMes, Mes)){
                            Toast.makeText(getApplicationContext(), "Pon la fecha bien, bobo", Toast.LENGTH_SHORT).show();
                        } else{
                            if(nOcupantes < 1 || nOcupantes > 8){
                                Toast.makeText(getApplicationContext(), "El numero maximo de comensales es 8, bobo", Toast.LENGTH_SHORT).show();
                            } else{
                                Reserva reserva = new Reserva(-1, mesa, diaMes, Mes, nOcupantes, turno);
                                IDUsuario = getIDUsuario(db);
                                if(db.estaLaReserva(reserva)){
                                    Toast.makeText(getApplicationContext(), "No se puede realizar la reserva con esos datos. Seleccione otros", Toast.LENGTH_SHORT).show();
                                } else{
                                    boolean seMetio = db.anyadeReserva(reserva, IDUsuario);
                                    if(seMetio){
                                        Toast.makeText(getApplicationContext(), "La reserva fue realizada satisfactoriamente :D", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                } catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Pon bien los datos, bobo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean estaBienLaFecha(int dia, int mes){
        if(dia > 31 || dia < 1 || mes < 1 || mes > 12){
            return false;
        } else{
            if(mes == 2 && dia > 28){
                return false;
            } else if (dia > 30 && (mes % 2) != 0){
                return false;
            } else{
                return true;
            }
        }
    }

    private int getIDUsuario(DatabaseHelper db){
        Cursor cursor = db.getDatosUsuario(emailUsuario);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
}