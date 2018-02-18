package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Objects;

public class ResumenPedido extends AppCompatActivity {
    String cod_pedido, cod_cliente;
    float precio = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_pedido);

        String nombre = null, direccion = null;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        TextView texto = findViewById(R.id.editText);
        texto.setEnabled(false);

        Intent intent = getIntent();

        cod_pedido = intent.getStringExtra("codigo_pedido");
        cod_cliente = intent.getStringExtra("codigo_cliente");

        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();

        Cursor busqueda_datos = db.query("cliente", campos_datos, "cod_cliente = ?", cped, null, null, null);
        Cursor busqueda_kebabs = db.query("kebabs", campos_kebab, "cod_pedido = ?", cped, null, null, null);
        Cursor busqueda_bebidas = db.query("bebidas", campos_bebida, "cod_pedido = ?", cped, null, null, null);

        if (busqueda_datos.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                nombre= busqueda_datos.getString(0);
            } while(busqueda_datos.moveToNext());
        }


        //Utilizando la información sacada anteriormente de la base de datos la muestra en el edit text
        texto.append("Nombre: " + nombre + "\nDireccion: " + direccion);
        texto.append("\n\n--Kebab--");

        if (busqueda_kebabs.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                int precio_pedido = 1;

                texto.append(busqueda_kebabs.getString(0)); //Tipo kebab

                for(int i = 0; i < tipoKebab.length; i++){
                    if(Objects.equals(busqueda_kebabs.getString(0), tipoKebab[i])){
                        precio_pedido += precio_tipo_kebab[i];
                    }
                }

                texto.append("\n");
                texto.append(busqueda_kebabs.getString(1)); //Tamaño kebab

                for(int i = 0; i < strings_bebidas.length; i++){
                    if(Objects.equals(busqueda_kebabs.getString(0), strings_bebidas[i])){
                        precio_pedido += precio_tamaño[i];
                    }
                }

                texto.append("\n");
                texto.append(busqueda_kebabs.getString(2)); //Tipo carne

                for(int i = 0; i < strings_bebidas.length; i++){
                    if(Objects.equals(busqueda_kebabs.getString(0), strings_bebidas[i])){
                        precio_pedido += precio_tipo_carne[i];
                    }
                }

                texto.append("\n");
                texto.append(busqueda_kebabs.getString(3)); //Cantidad kebab

                precio += precio_pedido * Integer.parseInt(busqueda_kebabs.getString(3));

                texto.append("\n");
            } while(busqueda_kebabs.moveToNext());
        }

        texto.append("\n\n--Bebidas--");

        if (busqueda_bebidas.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                int precio_pedido2 = 1;
                texto.append(busqueda_bebidas.getString(0)); //Nombre bebida

                for(int i = 0; i < strings_bebidas.length; i++){
                    if(Objects.equals(busqueda_bebidas.getString(0), strings_bebidas[i])){
                        precio_pedido2 += precio_bebidas[i];
                    }
                }

                texto.append("\n");
                texto.append(busqueda_bebidas.getString(1)); //Cantidad bebida

                precio += precio_pedido2 * Integer.parseInt(busqueda_bebidas.getString(1));

                texto.append("\n");
            } while(busqueda_bebidas.moveToNext());
        }

        texto.append("\n\n--Regalo--");

        if(precio > 15) {
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
        texto.append("\n\nTotal: " +  decimalFormat.format(precio + precio * 0.21f) + "€");

    }

    public void enviar(View v){
        //Este es el codigo que permite compartir el contenido del editText
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

        TextView texto = findViewById(R.id.editText);

        sharingIntent.setType("text/plain");

        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Pedido kebab");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, String.valueOf(texto.getText()));

        startActivity(Intent.createChooser(sharingIntent, "Enviar por"));
    }

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

}
