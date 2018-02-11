package com.example.adminportatil.proyectoandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DatosCliente extends AppCompatActivity {
    EditText nombre, direccion, telefono, email;
    KebabsSQLiteHelper base_de_datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cliente);

        base_de_datos = new KebabsSQLiteHelper(this);

        //Recibe los intent para pasarlos
        nombre = findViewById(R.id.nombre);
        direccion = findViewById(R.id.direccion);
        telefono = findViewById(R.id.telefono);
        email = findViewById(R.id.email);
    }

    public void lanzarPedidoKebab(View view){
        Toast toast;

        //Validacion de datos
        if(!nombre.getText().toString().isEmpty() && !direccion.getText().toString().isEmpty() && !telefono.getText().toString().isEmpty() && !email.getText().toString().isEmpty()){

            if(telefono.getText().toString().length() > 9) {
                toast = Toast.makeText(this, "El numero de telefono tiene mas que 9 digitos.", Toast.LENGTH_SHORT);
                toast.show();
            }else if (telefono.getText().toString().length() < 9) {
                toast = Toast.makeText(this, "El numero de telefono tiene menos que 9 digitos.", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Intent intent = new Intent(this, PedidoKebab.class);
                intent.putExtra("nombre", nombre.getText().toString());
                intent.putExtra("apellido", direccion.getText().toString());
                intent.putExtra("telefono", telefono.getText().toString());
                intent.putExtra("email", email.getText().toString());

                añadirDatos();

                startActivity(intent);
            }

        } else {
            toast = Toast.makeText(this, "Tienes que llenar todos los campos.", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void añadirDatos(){
        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Nombre", nombre.getText().toString());
        contentValues.put("Direccion", direccion.getText().toString());
        contentValues.put("Telefono", telefono.getText().toString());
        contentValues.put("Email", email.getText().toString());

        db.insert("Datos_cliente", null, contentValues);
    }

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

}

