package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Portada extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), DatosCliente.class));
            }
        }, 3000);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);
    }

}
