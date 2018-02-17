package com.example.adminportatil.proyectoandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();
        String[] campos = new String[] {"cod_cliente"};
        String[] args = new String[] {nombre.getText().toString()};

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

                añadirDatos();

                Cursor c = db.query("cliente", campos, "nombre = ?", args, null, null, null);

                //Nos aseguramos de que existe al menos un registro
                if (!c.moveToFirst()) {
                    intent.putExtra("cod_cliente", c.getString(0));
                }

                c.close();

                startActivity(intent);
            }

        } else {
            toast = Toast.makeText(this, "Tienes que llenar todos los campos.", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void buscar_cliente(View view) {
        if(!nombre.getText().toString().isEmpty()) {
            KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
            SQLiteDatabase db = kqlh.getWritableDatabase();

            String[] campos = new String[] {"direccion", "telefono", "email"};
            String[] args = new String[] {nombre.getText().toString()};

            String nom = null,  direcc = null, telf = null, em = null;

            Cursor c = db.query("cliente", campos, "nombre = ?", args, null, null, null);

            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    direcc= c.getString(0);
                    telf = c.getString(1);
                    em = c.getString(2);
                } while(c.moveToNext());
            }

            c.close();

            direccion.setText(direcc);
            telefono.setText(telf);
            email.setText(em);
        } else {
            Toast toast = Toast.makeText(this, "No hay nada en el campo del nombre.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void añadirDatos(){
        KebabsSQLiteHelper kqlh = new KebabsSQLiteHelper(this);
        SQLiteDatabase db = kqlh.getWritableDatabase();

        //db.execSQL("INSERT INTO Datos_cliente (Nombre, Direccion, Telefono, Email) VALUES (" + nombre.getText().toString() + ", " + direccion.getText().toString() + ", " + telefono.getText().toString() + ", " + email.getText().toString() + ")");

        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", nombre.getText().toString());
        contentValues.put("direccion", direccion.getText().toString());
        contentValues.put("telefono", telefono.getText().toString());
        contentValues.put("email", email.getText().toString());

        db.insert("cliente", null, contentValues);
    }

    public void cerrar(View v){
        finish();
        System.exit(0);
    }

}
