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

public class ResumenPedido extends AppCompatActivity {
    String nom, ap, telf, email, cod_pedido;
    ArrayList<ArrayList<Integer>> kebab;
    int[] bebidas;
    int contador = 0;
    float precio = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_pedido);

        String nombre = null, direccion = null, telefono = null;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        Double[] precio_tipo_kebab = {4.00, 4.50, 5.00, 4.50, 5.00}, precio_tipo_carne = {0.50, 1.0, 0.50}, precio_tamaño = {0.00, 1.00}, precio_bebidas = {1.00, 1.00, 1.00, 8.00, 1.50, 0.50};

        TextView texto = findViewById(R.id.editText);
        texto.setEnabled(false);

        Intent intent = getIntent();

        cod_pedido = intent.getStringExtra("codigo_pedido");
        String[] cped = {cod_pedido}, campos_datos = {"Nombre", "Direccion", "Telefono", "Email"}, campos_kebab = {"tipo_kebab", "tamaño_kebab", "tipo_carne", "cantidad"}, campos_bebida = {"nombre_bebida", "cantidad"};

        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();

        Cursor busqueda_datos = db.query("Datos_cliente", campos_datos, "cod_pedido = ?", cped, null, null, null);
        Cursor busqueda_kebabs = db.query("Pedido_kebab", campos_kebab, "cod_pedido = ?", cped, null, null, null);
        Cursor busqueda_bebidas = db.query("Pedido_bebida", campos_bebida, "cod_pedido = ?", cped, null, null, null);

        if (busqueda_datos.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                nombre= busqueda_datos.getString(0);
                direccion = busqueda_datos.getString(1);
                telefono = busqueda_datos.getString(2);
                email = busqueda_datos.getString(3);
            } while(busqueda_datos.moveToNext());
        }

        texto.append("Nombre: " + nombre + "\nDireccion: " + direccion);
        texto.append("\n\n--Kebab--");

        if (busqueda_kebabs.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                texto.append(busqueda_kebabs.getString(0)); //Tipo kebab
                texto.append(busqueda_kebabs.getString(1)); //Tamaño kebab
                texto.append(busqueda_kebabs.getString(2)); //Tipo carne
                texto.append(busqueda_kebabs.getString(3)); //Cantidad kebab
                texto.append("\n");
            } while(busqueda_kebabs.moveToNext());
        }

        texto.append("\n\n--Bebidas--");
        if (busqueda_bebidas.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                texto.append(busqueda_bebidas.getString(0)); //Nombre bebida
                texto.append(busqueda_bebidas.getString(1)); //Cantidad bebida
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




        //Navega por los arrays para conseguir la informacion de las filas y luego las muestra en el edit text
        //Hace algo similar con las bebidas
        //Por alguna razon el array sale de pedido kebab con valores pero al llegar aqui todos los valores son 0
        //Esa es la razon por la que no se muestra correctamente
        /*
        for(int i = 0; i < kebab.size(); i++){
            for(int x = 0; x < kebab.get(i).size(); x++) {
                ArrayList<Integer> fila = new ArrayList<> (kebab.get(i));


                if (x == 0 && fila.get(x) == 0 && fila.get(3) != 0) {
                    texto.append("\nDönner ");

                    precio += 4f * (float) fila.get(3);
                } else if (x == 0 && fila.get(x) == 1 && fila.get(3) != 0) {
                    texto.append("\nDürüm ");
                    precio += 4.50f * (float) fila.get(3);
                } else if (x == 0 && fila.get(x) == 2 && fila.get(3) != 0) {
                    texto.append("\nLahmacum ");
                    precio += 5f * (float)fila.get(3);
                } else if (x == 0 && fila.get(x) == 3 && fila.get(3) != 0) {
                    texto.append("\nShawarms ");
                    precio += 4.50f * (float)fila.get(3);
                } else if (x == 0 && fila.get(x) == 4 && fila.get(3) != 0) {
                    texto.append("\nGyros ");
                    precio += 5f * (float) fila.get(3);
                }

                if(x == 1 && fila.get(x) == 0 && fila.get(3) != 0) {
                    texto.append("Pollo  ");
                    precio += 0.50f;
                } else if(x == 1 && fila.get(x) == 1 && fila.get(3) != 0){
                    texto.append("Ternera  ");
                    precio += 1f;
                } else if(x == 1 && fila.get(x) == 2 && fila.get(3) != 0){
                    texto.append("Cordero  ");
                    precio += 0.50f;
                }

                if(x == 2 &&fila.get(x) == 0 && fila.get(3) != 0 && fila.get(3) != 0){
                    texto.append("Normal  *" + String.valueOf(fila.get(3)));
                } else if(x == 2 && fila.get(x) == 1 && fila.get(3) != 0 && fila.get(3) != 0){
                    texto.append("Completo  *" + String.valueOf(fila.get(3)));
                    precio += 1f;
                }
            }
        }

        texto.append("\n\n--Bebidas--");
        for (int i = 0; i < bebidas.length; i++){
            if(bebidas[i] != 0 && i == 0){
                texto.append("\nCola *" + bebidas[i]);
                precio += (float) bebidas[i];
                contador++;
            } else if (bebidas[i] != 0 && i == 1){
                texto.append("\nLimon *" + bebidas[i]);
                precio +=(float)  bebidas[i];
                contador++;
            } else if (bebidas[i] != 0 && i == 2){
                texto.append("\nNaranja *" + bebidas[i]);
                precio += (float) bebidas[i];
                contador++;
            } else if (bebidas[i] != 0 && i == 3){
                texto.append("\nVino *" + bebidas[i]);
                precio += 8f * (float) bebidas[i];
                contador++;
            } else if (bebidas[i] != 0 && i == 4){
                texto.append("\nCerveza *" + bebidas[i]);
                precio += 1.50f * (float) bebidas[i];
                contador++;
            } else if (bebidas[i] != 0 && i == 5){
                texto.append("\nAgua *" + bebidas[i]);
                precio += 0.50f * (float) bebidas[i];
                contador++;
            }
        }

        if (contador == 0){
            texto.append("\nNada");
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
        */
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
