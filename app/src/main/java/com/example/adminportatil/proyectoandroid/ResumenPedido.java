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
        texto.append("\n--Kebab--");

        for(int i = 0; i < kebab.size(); i++){
            for(int x = 0; x < kebab.get(i).size(); x++) {
                if (x == 0 && kebab.get(i).get(x) == 0) {
                    texto.append("\nDönner ");
                } else if (x == 0 && kebab.get(i).get(x) == 1) {
                    texto.append("\nDürüm ");
                } else if (x == 0 && kebab.get(i).get(x) == 2) {
                    texto.append("\nLahmacum ");
                } else if (x == 0 && kebab.get(i).get(x) == 3) {
                    texto.append("\nShawarms ");
                } else if (x == 0 && kebab.get(i).get(x) == 4) {
                    texto.append("\nGyros ");
                }

                if(x == 1 && kebab.get(i).get(x) == 0) {
                    texto.append("Pollo  ");
                } else if(x == 1 && kebab.get(i).get(x) == 1){
                    texto.append("Ternera  ");
                } else if(x == 1 && kebab.get(i).get(x) == 2){
                    texto.append("Cordero  ");
                }

                if(x == 2 && kebab.get(i).get(x) == 0){
                    texto.append("Normal  *" + kebab.get(i).get(x));
                } else if(x == 2 && kebab.get(i).get(x) == 1){
                    texto.append("Completo  *" + kebab.get(i).get(x));
                }
            }
        }

        texto.append("\n\n--Bebidas--");
        for (int i = 0; i < bebidas.length; i++){
            if(bebidas[i] != 0 && i == 0){
                texto.append("\nCola *" + bebidas[i]);
            } else if (bebidas[i] != 0 && i == 1){
                texto.append("\nLimon *" + bebidas[i]);
            } else if (bebidas[i] != 0 && i == 2){
                texto.append("\nNaranja *" + bebidas[i]);
            } else if (bebidas[i] != 0 && i == 3){
                texto.append("\nVino *" + bebidas[i]);
            } else if (bebidas[i] != 0 && i == 4){
                texto.append("\nCerveza *" + bebidas[i]);
            } else if (bebidas[i] != 0 && i == 5){
                texto.append("\nAgua *" + bebidas[i]);
            }
        }

        texto.append("\n--Regalo--");

        System.out.println(nom);
        System.out.println(ap);
        System.out.println(telf);
        System.out.println(email);
    }
}
