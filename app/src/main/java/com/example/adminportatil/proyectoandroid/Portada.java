package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class Portada extends AppCompatActivity {


    public void cerrar(View v){
        finish();
        System.exit(0);
    }

    public void lanzarDatosCliente(View view){
        Intent i = new Intent(this, DatosCliente.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), DatosCliente.class));
            }
        }, 5000);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);
    }

}
