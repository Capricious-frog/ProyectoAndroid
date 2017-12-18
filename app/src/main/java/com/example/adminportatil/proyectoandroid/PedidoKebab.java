package com.example.adminportatil.proyectoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class PedidoKebab extends AppCompatActivity {

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

    public void anadir_fila(View v) {
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
    }

    public void anadir_fila2(View v) {
        TableLayout tl = findViewById(R.id.tablaKebabs);

        LayoutInflater inflator = PedidoKebab.this.getLayoutInflater();
        TableRow rowView = new TableRow(v.getContext());
        inflator.inflate(R.layout.activity_pedido_kebab, rowView);
        tl.addView(rowView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_kebab);
    }
}
