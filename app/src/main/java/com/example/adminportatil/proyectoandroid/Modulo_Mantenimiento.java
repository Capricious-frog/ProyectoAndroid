package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Modulo_Mantenimiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo__mantenimiento);
    }

    public void lanzarBuscar (View view){
        Intent intent = new Intent(this, Buscar.class);
        startActivity(intent);
    }

    public void lanzarActualizar (View view){
        Intent intent = new Intent(this, Actualizar.class);
        startActivity(intent);
    }

    public void lanzarBorrar (View view){
        Intent intent = new Intent(this, Borrar.class);
        startActivity(intent);
    }

    public void lanzarInsertar (View view){
        Intent intent = new Intent(this, Insertar.class);
        startActivity(intent);
    }

    public void cerrar(View v) {
        finish();
        System.exit(0);
    }
}
