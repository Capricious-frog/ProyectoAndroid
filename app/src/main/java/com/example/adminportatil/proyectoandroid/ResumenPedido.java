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
    int cod_pedido, cod_cliente;
    float precio = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_pedido);

        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        TextView texto = findViewById(R.id.editText);
        texto.setEnabled(false);

        double precio_pedido = 1;

        String nombre = null, direccion = null;

        Intent intent = getIntent();

        cod_pedido = intent.getIntExtra("codigo_pedido", 1);
        cod_cliente = intent.getIntExtra("codigo_cliente", 1);

        String[] campos_datos = {"nombre", "direccion"};
        String[] campos_kebab = {"cod_tipo_kebab", "cod_tipo_carne", "cod_tamano", "cantidad"};
        String[] campos_bebida = {"cod_info_bebida", "cantidad"};
        String[] ccli = {String.valueOf(cod_cliente)};
        String[] cped = {String.valueOf(cod_pedido)};



        Cursor datos_cliente = db.query("cliente", campos_datos, "cod_cliente = ?", ccli, null, null, null);
        Cursor busqueda_kebabs = db.query("kebabs", campos_kebab, "cod_pedido = ?", cped, null, null, null);
        Cursor busqueda_bebidas = db.query("bebidas", campos_bebida, "cod_pedido = ?", cped, null, null, null);

        //Guarda los datos del cliente
        if (datos_cliente.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                nombre= datos_cliente.getString(0);
                direccion= datos_cliente.getString(1);
            } while(datos_cliente.moveToNext());
        }

        //Utilizando la información de la base de datos, rellenamos el edit text
        texto.append("Nombre: " + nombre + "\nDireccion: " + direccion);
        texto.append("\n\n--Kebab--");

        if (busqueda_kebabs.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
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
