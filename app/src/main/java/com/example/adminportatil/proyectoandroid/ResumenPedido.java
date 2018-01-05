package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ResumenPedido extends AppCompatActivity {
    String nom, ap, telf, email;
    int[] bebidas;

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_pedido);

        Intent intent = getIntent();

        nom = intent.getStringExtra("nombre");
        ap = intent.getStringExtra("apellido");
        telf = intent.getStringExtra("telefono");
        email = intent.getStringExtra("email");
        //kebab = intent.getStringArrayExtra(); //Array con la informacion de pedido kebab
        bebidas = intent.getIntArrayExtra("bebidas");

        System.out.println(nom);
        System.out.println(ap);
        System.out.println(telf);
        System.out.println(email);
    }
}
