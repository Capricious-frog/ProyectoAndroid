package com.example.adminportatil.proyectoandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KebabsSQLiteHelper extends SQLiteOpenHelper {

    KebabsSQLiteHelper(Context context) {
        super(context, "Kebabs.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        crearBd(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS \"Datos_cliente\"");
        db.execSQL("DROP TABLE IF EXISTS 'Pedido_kebab'");
        db.execSQL("DROP TABLE IF EXISTS 'Pedido_bebida'");

        crearBd(db);
    }

    private void crearBd(SQLiteDatabase db){
        db.execSQL("CREATE TABLE \"Datos_cliente\" ( 'cod_cliente' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'Nombre' TEXT NOT NULL, 'Direccion' TEXT NOT NULL, 'Telefono' TEXT NOT NULL, 'Email' TEXT NOT NULL )");
        db.execSQL("CREATE TABLE 'Pedido_kebab' ( 'cod_pedido' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'tipo_kebab' TEXT NOT NULL, 'tamaño_kebab' TEXT NOT NULL, 'tipo_carne' TEXT NOT NULL, 'cantidad' INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE 'Pedido_bebida' ( 'cod_pedido' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'nombre_bebida' TEXT NOT NULL UNIQUE, 'cantidad' INTEGER NOT NULL )");
    }
}
