package com.example.adminportatil.proyectoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PedidoKebab extends AppCompatActivity {

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_kebab);
    }
}
