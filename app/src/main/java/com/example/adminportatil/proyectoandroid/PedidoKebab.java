package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import java.util.ArrayList;

public class PedidoKebab extends AppCompatActivity {

    int contador = 1;
    String nom, ap, telf, email;
    Spinner kebab, carne, tamano, cantidad;

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

    public void lanzarPedidoKebab(View view){
        Spinner sp = findViewById(R.id.cantidad0);

        if (sp.getSelectedItemPosition() != 0){
            ArrayList<int[]> array_kebab = new ArrayList<>();

            //ArrayList compuesto de arrays. Cada array contiene la informacion de una fila. (Por momento solo añade la primera fila)
            array_kebab.add(new int[]{kebab.getSelectedItemPosition(), carne.getSelectedItemPosition() , tamano.getSelectedItemPosition(), cantidad.getSelectedItemPosition()});

            Intent intent = new Intent(this, PedidoBebidasActivity.class);
            intent.putExtra("nombre", nom);
            intent.putExtra("apellido", ap);
            intent.putExtra("telefono", telf);
            intent.putExtra("email", email);
            intent.putExtra("kebab", array_kebab);

            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "Tienes que seleccionar al menos un producto.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void anadir_fila(View v) {
        Toast toast = Toast.makeText(this, "No puedes añadir mas. Ya tienes todas las combinaciones posibles! :)", Toast.LENGTH_SHORT);

        if (contador < 30) {
            contador++;

            TableLayout tl = findViewById(R.id.tablaKebabs);

            LayoutInflater inflator = PedidoKebab.this.getLayoutInflater();
            TableRow rowView = new TableRow(v.getContext());
            inflator.inflate(R.layout.fila_kebab, rowView);
            tl.addView(rowView);

        } else {
            try{
                 toast.getView().isShown();
            } catch (Exception e) {
                System.out.println("Ya hay un toast en pantalla.");
            }
            toast.show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_kebab);

        kebab = findViewById(R.id.tipo_kebab0);
        carne = findViewById(R.id.tipo_carne0);
        tamano = findViewById(R.id.tamano0);
        cantidad = findViewById(R.id.cantidad0);

        Intent intent = getIntent();

        nom = intent.getStringExtra("nombre");
        ap = intent.getStringExtra("apellido");
        telf = intent.getStringExtra("telefono");
        email = intent.getStringExtra("email");
    }
}
