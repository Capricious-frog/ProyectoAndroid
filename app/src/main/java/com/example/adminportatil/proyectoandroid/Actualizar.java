package com.example.adminportatil.proyectoandroid;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Actualizar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
    }

    public void actualizarPedido(View view) {
        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();

        EditText cod_pedido_original = findViewById(R.id.codigo_pedido_original2);
        EditText cod_pedido = findViewById(R.id.codigo_pedido);
        EditText cod_cliente = findViewById(R.id.codigo_cliente);

        db.execSQL("UPDATE pedido SET cod_pedido = " + Integer.parseInt(cod_pedido.getText().toString()) + ", cod_cliente = " + Integer.parseInt(cod_cliente.getText().toString()) + " WHERE cod_pedido = " + Integer.parseInt(cod_pedido_original.getText().toString()) + "");

        Toast toast = Toast.makeText(this, "El pedido ha sido actualizado correctamente.", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void actualizarTipoKebab(View view) {
        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();

        EditText cod_tipo_kebab = findViewById(R.id.codigo_tipo_kebab_original);
        EditText nombre = findViewById(R.id.nombre);
        EditText precio = findViewById(R.id.precio);

        db.execSQL("UPDATE tipo_kebab SET nombre_tipo_kebab = '" + nombre.getText().toString() + "', precio = " + Integer.parseInt(precio.getText().toString()) + " WHERE cod_tipo_kebab = " + Integer.parseInt(cod_tipo_kebab.getText().toString()) + " ");

        Toast toast = Toast.makeText(this, "El tipo de kebab ha sido actualziado correctamente.", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void cerrar(View v) {
        finish();
        System.exit(0);
    }
}
