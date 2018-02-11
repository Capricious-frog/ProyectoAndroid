package com.example.adminportatil.proyectoandroid;

import android.content.ContentValues;
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

        añadirDatos();

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
        ContentValues contentValues = new ContentValues();

        String[] bebidas, precio_bebidas;

        bebidas = this.getResources().getStringArray(R.array.bebidas);
        precio_bebidas = this.getResources().getStringArray(R.array.precio_bebidas);

        for (String bebida : bebidas) {
            contentValues.put("nombre_bebida", bebida);
        }

        for (String precio_bebida : precio_bebidas) {
            contentValues.put("precio", precio_bebida);
        }

        db.insert("Bebidas", null, contentValues);
    }

}
