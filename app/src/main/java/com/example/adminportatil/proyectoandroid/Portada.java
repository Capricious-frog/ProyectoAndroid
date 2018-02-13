package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

public class Portada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        //añadirDatos();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), DatosCliente.class));
            }
        }, 3000);
    }

    public void añadirDatos(){
        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();

        String bebidas, precio_bebidas;

        bebidas = getResources().getStringArray(R.array.bebidas)[0];
        precio_bebidas = getResources().getStringArray(R.array.precio_bebidas)[0];

        db.execSQL("INSERT INTO Bebidas (nombre_bebida, precio) VALUES('" + bebidas + "', " + Float.parseFloat(precio_bebidas) + ")");
    }
}
