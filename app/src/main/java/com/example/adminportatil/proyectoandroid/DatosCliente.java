package com.example.adminportatil.proyectoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DatosCliente extends AppCompatActivity {
    EditText nombre, direccion, telefono, email;

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

    public void lanzarPedidoKebab(View view){
        nombre = findViewById(R.id.nombre);
        direccion = findViewById(R.id.direccion);
        telefono = findViewById(R.id.telefono);
        email = findViewById(R.id.email);

        if(!nombre.getText().toString().isEmpty() || !direccion.getText().toString().isEmpty() || !telefono.getText().toString().isEmpty() || !email.getText().toString().isEmpty()){
            Intent i = new Intent(this, PedidoKebab.class);
            startActivity(i);
        } else{
            Toast toast = Toast.makeText(this, "Tienes que rellenar todos los campos.", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cliente);
    }
}
