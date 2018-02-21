package com.example.adminportatil.proyectoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Actualizar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
    }

    public void actualizarPedido(View view) {

    }

    public void actualizarTipoKebab(View view) {

    }

    public void cerrar(View v) {
        finish();
        System.exit(0);
    }
}
