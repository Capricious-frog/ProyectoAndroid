package com.example.adminportatil.proyectoandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KebabsSQLiteHelper extends SQLiteOpenHelper {

    KebabsSQLiteHelper(Context context) {
        super(context, "Kebabs.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE 'Bebidas' ( 'cod_bebida' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'nombre_bebida' TEXT NOT NULL UNIQUE, 'precio' INTEGER NOT NULL )");
        db.execSQL("CREATE TABLE \"Datos_cliente\" ( 'cod_cliente' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'Nombre' TEXT NOT NULL, 'Direccion' TEXT NOT NULL, 'Telefono' TEXT NOT NULL, 'Email' TEXT NOT NULL )");
        db.execSQL("CREATE TABLE 'Kebab' ( 'cod_kebab' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'tipo_kebab' TEXT NOT NULL UNIQUE, 'carne_kebab' TEXT NOT NULL UNIQUE, 'tamaño_kebab' TEXT NOT NULL UNIQUE, 'precio_tipo' INTEGER NOT NULL, 'precio_carne' INTEGER NOT NULL, 'precio_tamaño' INTEGER NOT NULL )");
        db.execSQL("CREATE TABLE 'Pedido_bebida' ( 'cod_pedido' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'nombre_bebida' TEXT NOT NULL UNIQUE, 'cantidad' INTEGER NOT NULL )");
        db.execSQL("CREATE TABLE 'Pedido_kebab' ( 'cod_pedido' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'tipo_kebab' TEXT NOT NULL UNIQUE, 'tamaño_kebab' TEXT NOT NULL UNIQUE, 'tipo_carne' TEXT NOT NULL UNIQUE, 'cantidad' INTEGER NOT NULL UNIQUE )");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS 'Bebidas'");
        db.execSQL("DROP TABLE IF EXISTS \"Datos_cliente\"");
        db.execSQL("DROP TABLE IF EXISTS 'Kebab'");
        db.execSQL("DROP TABLE IF EXISTS 'Pedido_bebida'");
        db.execSQL("DROP TABLE IF EXISTS 'Pedido_kebab'");

        db.execSQL("CREATE TABLE 'Bebidas' ( 'cod_bebida' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'nombre_bebida' TEXT NOT NULL UNIQUE, 'precio' INTEGER NOT NULL )");
        db.execSQL("CREATE TABLE \"Datos_cliente\" ( 'cod_cliente' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'Nombre' TEXT NOT NULL, 'Direccion' TEXT NOT NULL, 'Telefono' TEXT NOT NULL, 'Email' TEXT NOT NULL )");
        db.execSQL("CREATE TABLE 'Kebab' ( 'cod_kebab' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'tipo_kebab' TEXT NOT NULL UNIQUE, 'carne_kebab' TEXT NOT NULL UNIQUE, 'tamaño_kebab' TEXT NOT NULL UNIQUE, 'precio_tipo' INTEGER NOT NULL, 'precio_carne' INTEGER NOT NULL, 'precio_tamaño' INTEGER NOT NULL )");
        db.execSQL("CREATE TABLE 'Pedido_bebida' ( 'cod_pedido' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'nombre_bebida' TEXT NOT NULL UNIQUE, 'cantidad' INTEGER NOT NULL )");
        db.execSQL("CREATE TABLE 'Pedido_kebab' ( 'cod_pedido' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'tipo_kebab' TEXT NOT NULL UNIQUE, 'tamaño_kebab' TEXT NOT NULL UNIQUE, 'tipo_carne' TEXT NOT NULL UNIQUE, 'cantidad' INTEGER NOT NULL UNIQUE )");
    }
}
