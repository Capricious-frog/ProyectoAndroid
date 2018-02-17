package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PedidoBebidasActivity extends AppCompatActivity {
    int cod_pedido, cod_cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_bebidas);

        Intent intent = getIntent();
        cod_cliente = intent.getIntExtra("codigo_cliente", 0);
        cod_pedido = intent.getIntExtra("codigo_pedido", 0);
    }

    public void lanzarResumentPedido(View view){
        ArrayList<Spinner> spinners_cantidad = new ArrayList<>();
        ConstraintLayout constraint_layout = findViewById(R.id.cl);

        Intent intent = new Intent(this, ResumenPedido.class);
        intent.putExtra("codigo_cliente", cod_cliente);
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

        String[] strings_bebidas = this.getResources().getStringArray(R.array.bebidas);

        for (int i = 0; i < strings_bebidas.length; i++) {
            if (spinners_cantidad.get(i).getSelectedItemPosition() != 0){
                db.execSQL("INSERT INTO Pedido_bebida (nombre_bebida, cantidad) VALUES ('" + strings_bebidas[i] + "', " + spinners_cantidad.get(i).getSelectedItemPosition() + ")");
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();

        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();

        db.execSQL("DELETE FROM bebidas WHERE pedido_completado = 0");
        db.execSQL("DELETE FROM kebabs WHERE pedido_completado = 0");
    }

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

}
