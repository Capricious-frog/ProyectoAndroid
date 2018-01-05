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

public class PedidoKebab extends AppCompatActivity {

    int contador = 1;

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

    public void lanzarPedidoKebab(View view){
        Spinner sp = findViewById(R.id.cantidad0);

        if (sp.getSelectedItemPosition() != 0){
            Intent i = new Intent(this, PedidoBebidasActivity.class);
            startActivity(i);
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
    }
}
