package com.example.adminportatil.proyectoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class PedidoKebab extends AppCompatActivity {

    int contador = 1;

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

    public void anadir_fila(View v) {
        if (contador >= 30) {
            contador++;

        /* Find Tablelayout defined in main.xml */
            TableLayout tl = findViewById(R.id.tablaKebabs);
        /* Create a new row to be added. */
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        /* Create a Button to be the row-content. */
            Button b = new Button(this);
            b.setText("Dynamic Button");
            b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        /* Add Button to row. */
            tr.addView(b);
        /* Add row to TableLayout. */
            //tr.setBackgroundResource(R.drawable.sf_gradient_03);
            tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        } else {
            Toast toast = Toast.makeText(this, "No puedes añadir mas. Ya tienes todas las combinaciones posibles! :)", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void anadir_fila2(View v) {
        contador++;

        TableLayout tl = findViewById(R.id.tablaKebabs);

        LayoutInflater inflator = PedidoKebab.this.getLayoutInflater();
        TableRow rowView = new TableRow(v.getContext());
        inflator.inflate(R.layout.fila_kebab, rowView);
        tl.addView(rowView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_kebab);
    }
}
