package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class ResumenPedido extends AppCompatActivity {
    int cod_pedido, cod_cliente;
    double precio = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_pedido);

        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        TextView texto = findViewById(R.id.editText);
        texto.setEnabled(false);

        String nombre = null, direccion = null;

        Intent intent = getIntent();

        cod_pedido = intent.getIntExtra("codigo_pedido", 1);
        cod_cliente = intent.getIntExtra("codigo_cliente", 1);

        String[] campos_datos = {"nombre", "direccion"};
        String[] campos_kebab = {"cod_tipo_kebab", "cod_tipo_carne", "cod_tamano", "cantidad"};
        String[] campos_bebida = {"cod_info_bebida", "cantidad"};
        String[] ccli = {String.valueOf(cod_cliente)};
        String[] cped = {String.valueOf(cod_pedido)};

        ArrayList<String> tipo_kebab = new ArrayList<>();
        ArrayList<String> tipo_carne = new ArrayList<>();
        ArrayList<String> tamano = new ArrayList<>();

        ArrayList<Integer> precio_tipo_kebab = new ArrayList<>();
        ArrayList<Integer> precio_tipo_carne = new ArrayList<>();
        ArrayList<Integer> precio_tamano = new ArrayList<>();

        ArrayList<String> info_bebida = new ArrayList<>();
        ArrayList<Integer> precio_info_bebida = new ArrayList<>();


        //Se recoge la informacion del pedido hecho por el cliente
        Cursor datos_cliente = db.query("cliente", campos_datos, "pedido_completado = 0 AND cod_cliente = ?", ccli, null, null, null);
        Cursor busqueda_kebabs = db.query("kebabs", campos_kebab, "pedido_completado = 0 AND cod_pedido = ?", cped, null, null, null);
        Cursor busqueda_bebidas = db.query("bebidas", campos_bebida, "pedido_completado = 0 AND cod_pedido = ?", cped, null, null, null);

        //Se recoge los diferentes tipos de kebab, carne, tamaño y nombre de bebidas. Cada uno se mete en su ArrayList correspondiente.
        Cursor busqueda_tipo_kebab = db.query("tipo_kebab", campos_datos, "pedido_completado = 0 AND cod_cliente = ?", cped, null, null, null);
        Cursor busqueda_tipo_carne = db.query("tipo_carne", campos_kebab, "pedido_completado = 0 AND cod_pedido = ?", cped, null, null, null);
        Cursor busqueda_tamano = db.query("tamano", campos_bebida, "pedido_completado = 0 AND cod_pedido = ?", cped, null, null, null);
        Cursor busqueda_info_bebida = db.query("info_bebida", campos_bebida, "pedido_completado = 0 AND cod_pedido = ?", cped, null, null, null);

        //Guarda los datos del cliente
        if (datos_cliente.moveToFirst()) {
            do {
                nombre = datos_cliente.getString(0);
                direccion = datos_cliente.getString(1);
            } while (datos_cliente.moveToNext());
        }

        //Guarda todos los tipos de kebab
        if (busqueda_tipo_kebab.moveToFirst()) {
            do {
                tipo_kebab.add(busqueda_tipo_kebab.getString(0));
                precio_tipo_kebab.add(busqueda_tipo_kebab.getInt(1));
            } while (busqueda_tipo_kebab.moveToNext());
        }

        //Guarda todos los tipos de carne
        if (busqueda_tipo_carne.moveToFirst()) {
            do {
                tipo_carne.add(busqueda_tipo_carne.getString(0));
                precio_tipo_carne.add(busqueda_tipo_carne.getInt(1));
            } while (busqueda_tipo_carne.moveToNext());
        }

        //Guarda todos los tipos de tamaños
        if (busqueda_tamano.moveToFirst()) {
            do {
                tamano.add(busqueda_tamano.getString(0));
                precio_tamano.add(busqueda_tamano.getInt(1));
            } while (busqueda_tamano.moveToNext());
        }

        //Guarda todos los nombres de bebidas
        if (busqueda_info_bebida.moveToFirst()) {
            do {
                info_bebida.add(busqueda_info_bebida.getString(0));
                precio_info_bebida.add(busqueda_info_bebida.getInt(1));
            } while (busqueda_info_bebida.moveToNext());
        }

        //Utilizando la información de la base de datos, rellenamos el edit text
        texto.append("Nombre: " + nombre + "\nDireccion: " + direccion);
        texto.append("\n\n--Kebab--");

        if (busqueda_kebabs.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                texto.append(tipo_kebab.get(busqueda_kebabs.getInt(0)) + " "); //Tipo kebab
                texto.append(tipo_carne.get(busqueda_kebabs.getInt(1)) + " "); //Tipo carne
                texto.append(tamano.get(busqueda_kebabs.getInt(2)) + " "); //Tamaño
                texto.append(String.valueOf(busqueda_kebabs.getInt(3))); //Cantidad
                texto.append("\n");

                precio += (precio_tipo_kebab.get(busqueda_kebabs.getInt(0)) + precio_tipo_carne.get(busqueda_kebabs.getInt(1)) + precio_tamano.get(busqueda_kebabs.getInt(2))) * busqueda_kebabs.getInt(3);

            } while (busqueda_kebabs.moveToNext());
        }

        texto.append("\n\n--Bebidas--");

        if (busqueda_bebidas.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                texto.append(info_bebida.get(busqueda_bebidas.getInt(0)) + " "); //Nombre bebida
                texto.append(String.valueOf(busqueda_bebidas.getInt(1))); //Cantidad bebida

                precio += precio_info_bebida.get(busqueda_bebidas.getInt(0)) * busqueda_bebidas.getInt(1);
            } while (busqueda_bebidas.moveToNext());
        }

        texto.append("\n\n--Regalo--");

        if (precio > 15) {
            texto.append("\nPeluche de android");
        } else {
            texto.append("\nNada");

        }

        if (precio > 20) {
            texto.append("\nVale para el comedor de Cebanc");
        }


        texto.append("\n\n--Precio--");
        texto.append("\nPrecio neto: " + decimalFormat.format(precio) + "€");
        texto.append("\nIVA: " + decimalFormat.format(precio * 0.21f) + "€");
        texto.append("\n\nTotal: " + decimalFormat.format(precio + precio * 0.21f) + "€");

        //Cierre de los cursores
        datos_cliente.close();
        busqueda_kebabs.close();
        busqueda_bebidas.close();
        busqueda_tipo_kebab.close();
        busqueda_tipo_carne.close();
        busqueda_tamano.close();
        busqueda_info_bebida.close();

        db.execSQL("UPDATE pedido SET pedido_completado = 1 WHERE pedido_completado = 0");
        db.execSQL("UPDATE kebabs SET pedido_completado = 1 WHERE pedido_completado = 0");
        db.execSQL("UPDATE bebidas SET pedido_completado = 1 WHERE pedido_completado = 0");

    }

    public void enviar(View v) {
        //Este es el codigo que permite compartir el contenido del editText
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

        TextView texto = findViewById(R.id.editText);

        sharingIntent.setType("text/plain");

        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Pedido kebab");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, String.valueOf(texto.getText()));

        startActivity(Intent.createChooser(sharingIntent, "Enviar por"));
    }

    protected void onDestroy() {
        super.onDestroy();

        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();

        db.execSQL("DELETE FROM pedido WHERE pedido_completado = 0");
        db.execSQL("DELETE FROM bebidas WHERE pedido_completado = 0");
        db.execSQL("DELETE FROM kebabs WHERE pedido_completado = 0");
    }

    public void cerrar(View v) {
        finish();
        System.exit(0);
    }

}
