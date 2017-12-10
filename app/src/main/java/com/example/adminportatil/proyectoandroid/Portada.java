package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);
    }

}
