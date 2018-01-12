package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;

public class PedidoBebidasActivity extends AppCompatActivity {
    String nom, ap, telf, email;
    ArrayList<ArrayList<Integer>> kebab;

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

    public void lanzarResumentPedido(View view){
        Spinner cola = findViewById(R.id.cantidad_cola);
        Spinner limon = findViewById(R.id.cantidad_limon);
        Spinner naranja = findViewById(R.id.cantidad_naranja);
        Spinner vino = findViewById(R.id.cantidad_vino);
        Spinner cerveza = findViewById(R.id.cantidad_cerveza);
        Spinner agua = findViewById(R.id.cantidad_agua);

        int[] bebidas = {cola.getSelectedItemPosition(), limon.getSelectedItemPosition(), naranja.getSelectedItemPosition(), vino.getSelectedItemPosition(), cerveza.getSelectedItemPosition(), agua.getSelectedItemPosition()};

        Intent intent = new Intent(this, ResumenPedido.class);

        intent.putExtra("nombre", nom);
        intent.putExtra("apellido", ap);
        intent.putExtra("telefono", telf);
        intent.putExtra("email", email);
        intent.putExtra("kebab", kebab);
        intent.putExtra("bebidas", bebidas);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_bebidas);

        Intent intent = getIntent();

        nom = intent.getStringExtra("nombre");
        ap = intent.getStringExtra("apellido");
        telf = intent.getStringExtra("telefono");
        email = intent.getStringExtra("email");
        kebab = (ArrayList<ArrayList<Integer>>) intent.getSerializableExtra("kebab");
    }
}
