package com.example.adminportatil.proyectoandroid;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Actualizar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
    }

    public void actualizarPedido(View view) {
        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();

        EditText cod_pedido = findViewById(R.id.codigo_pedido);
        EditText cod_cliente = findViewById(R.id.codigo_cliente);

        db.execSQL("INSERT INTO pedido(cod_pedido, cod_cliente) VALUES (" + Integer.parseInt(cod_pedido.getText().toString()) + ", " + Integer.parseInt(cod_cliente.getText().toString()) + ")");
    }

    public void actualizarTipoKebab(View view) {
        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();

        EditText nombre = findViewById(R.id.nombre);
        EditText precio = findViewById(R.id.precio);

        db.execSQL("INSERT INTO tipo_kebab(nombre_tipo_kebab, precio) VALUES (" + nombre.getText().toString() + ", " + Integer.parseInt(precio.getText().toString()) + ")");
    }

    public void cerrar(View v) {
        finish();
        System.exit(0);
    }
}
