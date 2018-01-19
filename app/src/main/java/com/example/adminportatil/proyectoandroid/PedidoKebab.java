package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import java.util.ArrayList;

public class PedidoKebab extends AppCompatActivity {

    int contador = 1;
    String nom, ap, telf, email;
    Spinner kebab, carne, tamano, cantidad;

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

    public void lanzarPedidoKebab(View view){

        TableLayout tabla = findViewById(R.id.tablaKebabs);
        Spinner s;
        TableRow t;
        ArrayList<ArrayList<Integer>> array_kebab = new ArrayList<>();

        //Guarda los datos de la fila en un arraylist y luego mete este array en otro arraylist
        //El arraylist fila representa las filas y el arraylist array_kebab representa la tabla
        for (int i = 0; i < tabla.getChildCount(); i++) {
            t = (TableRow) tabla.getChildAt(i);
            array_kebab.add(new ArrayList<Integer>());

            for (int x = 0; x < t.getChildCount(); x++){
                    s = (Spinner) t.getChildAt(x);
                    array_kebab.get(i).add(x, s.getSelectedItemPosition());
            }

        }

        if(!validaKebab(array_kebab)) {

            Intent intent = new Intent(this, PedidoBebidasActivity.class);
            intent.putExtra("nombre", nom);
            intent.putExtra("apellido", ap);
            intent.putExtra("telefono", telf);
            intent.putExtra("email", email);
            intent.putExtra("kebab", array_kebab);

            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "Tienes que pedir al menos un kebab", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public boolean validaKebab(ArrayList<ArrayList<Integer>> array_kebab){
        int contador = 0;

        //Comprueba si al menos hay un spinner de la cantidad de kebabs que sea mayor que 0
        for (int i = 0; i < array_kebab.size(); i++){
            if(array_kebab.get(0).get(3) != 0){
                contador++;
            }
        }

        return contador == 0 ;
    }

    public void anadir_fila(View v) {
        Toast toast = Toast.makeText(this, "No puedes añadir mas. Ya tienes todas las combinaciones posibles! :)", Toast.LENGTH_SHORT);

        if (contador < 30) {
            contador++;

            TableLayout tl = findViewById(R.id.tablaKebabs);

            //Crea un nuevo tableRow y luego lo rellena con la actividad fila_kebab
            LayoutInflater inflator = PedidoKebab.this.getLayoutInflater();
            TableRow rowView = new TableRow(v.getContext());
            inflator.inflate(R.layout.fila_kebab, rowView);
            tl.addView(rowView);

        } else {
            try{
                //Si ya hay un toast en pantalla no te deja añadir otro
                 toast.getView().isShown();
            } catch (Exception e) {
                System.out.println("Ya hay un toast en pantalla.");
            }
            toast.show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_kebab);

        kebab = findViewById(R.id.tipo_kebab0);
        carne = findViewById(R.id.tipo_carne0);
        tamano = findViewById(R.id.tamano0);
        cantidad = findViewById(R.id.cantidad0);

        Intent intent = getIntent();

        nom = intent.getStringExtra("nombre");
        ap = intent.getStringExtra("apellido");
        telf = intent.getStringExtra("telefono");
        email = intent.getStringExtra("email");

    }
}
