package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;

public class PedidoBebidasActivity extends AppCompatActivity {
    String cod_pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_bebidas);

        Intent intent = getIntent();
        cod_pedido = intent.getStringExtra("codigo_pedido");
    }

    public void lanzarResumentPedido(View view){
        ArrayList<Spinner> spinners_cantidad = new ArrayList<>();
        ConstraintLayout constraint_layout = findViewById(R.id.cl);

        Intent intent = new Intent(this, ResumenPedido.class);
        intent.putExtra("codigo_pedido", cod_pedido);

        for (int i = 0; i < constraint_layout.getChildCount(); i++) {
            final View child = constraint_layout.getChildAt(i);
            if(child instanceof Spinner){
                spinners_cantidad.add((Spinner) constraint_layout.getChildAt(i));
            }
        }

        añadirDatos(spinners_cantidad);

        startActivity(intent);
    }

    public void añadirDatos(ArrayList<Spinner> spinners_cantidad){
        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();

        String[] strings_bebidas;

        strings_bebidas = this.getResources().getStringArray(R.array.bebidas);

        for (int i = 0; i < strings_bebidas.length; i++) {
            if (spinners_cantidad.get(i).getSelectedItemPosition() != 0){
                db.execSQL("INSERT INTO Pedido_bebida (nombre_bebida, cantidad) VALUES ('" + strings_bebidas[i] + "', " + spinners_cantidad.get(i).getSelectedItemPosition() + ")");
            }
        }
    }

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

}
