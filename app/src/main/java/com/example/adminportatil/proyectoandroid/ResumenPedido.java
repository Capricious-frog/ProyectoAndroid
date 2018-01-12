package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ResumenPedido extends AppCompatActivity {
    String nom, ap, telf, email;
    ArrayList<ArrayList<Integer>> kebab;
    int[] bebidas;
    float precio = 0;

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_pedido);

        TextView texto = findViewById(R.id.editText);
        texto.setEnabled(false);

        Intent intent = getIntent();

        nom = intent.getStringExtra("nombre");
        ap = intent.getStringExtra("apellido");
        telf = intent.getStringExtra("telefono");
        email = intent.getStringExtra("email");
        kebab = (ArrayList<ArrayList<Integer>>) intent.getSerializableExtra("kebab");
        bebidas = intent.getIntArrayExtra("bebidas");



        texto.append("Nombre: " + nom + "\nApellido: " + ap);
        texto.append("\n\n--Kebab--");

        for(int i = 0; i < kebab.size(); i++){
            for(int x = 0; x < kebab.get(i).size(); x++) {
                ArrayList<Integer> fila = new ArrayList<> (kebab.get(i));

                System.out.println(fila.get(0));
                System.out.println(fila.get(1));
                System.out.println(fila.get(2));
                System.out.println(fila.get(3));

                if (x == 0 && fila.get(x) == 0) {
                    texto.append("\nDönner ");

                    precio += 4f * (float) fila.get(3);
                } else if (x == 0 && fila.get(x) == 1) {
                    texto.append("\nDürüm ");
                    precio += 4.50f * (float) fila.get(3);
                } else if (x == 0 && fila.get(x) == 2) {
                    texto.append("\nLahmacum ");
                    precio += 5f * (float)fila.get(3);
                } else if (x == 0 && fila.get(x) == 3) {
                    texto.append("\nShawarms ");
                    precio += 4.50f * (float)fila.get(3);
                } else if (x == 0 && fila.get(x) == 4) {
                    texto.append("\nGyros ");
                    precio += 5f * (float) fila.get(3);
                }

                if(x == 1 && fila.get(x) == 0) {
                    texto.append("Pollo  ");
                    precio += 0.50f;
                } else if(x == 1 && fila.get(x) == 1){
                    texto.append("Ternera  ");
                    precio += 1f;
                } else if(x == 1 && fila.get(x) == 2){
                    texto.append("Cordero  ");
                    precio += 0.50f;
                }

                if(x == 2 &&fila.get(x) == 0 && fila.get(3) != 0){
                    texto.append("Normal  *" + String.valueOf(fila.get(3)));
                } else if(x == 2 && fila.get(x) == 1 && fila.get(3) != 0){
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
            } else if (bebidas[i] != 0 && i == 1){
                texto.append("\nLimon *" + bebidas[i]);
                precio +=(float)  bebidas[i];
            } else if (bebidas[i] != 0 && i == 2){
                texto.append("\nNaranja *" + bebidas[i]);
                precio += (float) bebidas[i];
            } else if (bebidas[i] != 0 && i == 3){
                texto.append("\nVino *" + bebidas[i]);
                precio += 8f * (float) bebidas[i];
            } else if (bebidas[i] != 0 && i == 4){
                texto.append("\nCerveza *" + bebidas[i]);
                precio += 1.50f * (float) bebidas[i];
            } else if (bebidas[i] != 0 && i == 5){
                texto.append("\nAgua *" + bebidas[i]);
                precio += 0.50f * (float) bebidas[i];
            }
        }

        texto.append("\n\n--Regalo--");

        if(precio > 15) {
            texto.append("\nPeluche de android");
        }

        if (precio > 20) {
            texto.append("\nVale para el comedor de Cebanc");
        }

        texto.append("\n\n--Precio--");
        texto.append("\nPrecio: " + precio + "€");
        texto.append("\nIVA: " + (precio * 0.21f) + "€");
        texto.append("\nTotal: " +( precio + precio * 0.21f) + "€");
    }

    public void enviar(View v){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

        TextView texto = findViewById(R.id.editText);

        sharingIntent.setType("text/plain");

        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Pedido kebab");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, String.valueOf(texto.getText()));

        startActivity(Intent.createChooser(sharingIntent, "Enviar por"));
    }
}
