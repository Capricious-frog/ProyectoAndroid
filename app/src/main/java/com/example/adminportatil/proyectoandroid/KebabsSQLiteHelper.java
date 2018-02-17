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
        db.execSQL("DROP TABLE IF EXISTS \"bebidas\"");
        db.execSQL("DROP TABLE IF EXISTS 'cliente'");
        db.execSQL("DROP TABLE IF EXISTS 'kebabs'");
        db.execSQL("DROP TABLE IF EXISTS \"pedido\"");
        db.execSQL("DROP TABLE IF EXISTS 'tamano'");
        db.execSQL("DROP TABLE IF EXISTS 'tipo_carne'");
        db.execSQL("DROP TABLE IF EXISTS 'tipo_kebab'");
        db.execSQL("DROP TABLE IF EXISTS 'info_bebida'");

        crearBd(db);
    }

    private void crearBd(SQLiteDatabase db){
        //Creacion de las tablas
        db.execSQL("CREATE TABLE \"bebidas\" ( 'cod_pedido' INTEGER NOT NULL UNIQUE, 'cod_bebida' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, FOREIGN KEY('cod_pedido') REFERENCES 'pedido'('cod_pedido') on delete set null, FOREIGN KEY('cod_bebida') REFERENCES 'bebidas'('cod_bebida') on delete set null )");
        db.execSQL("CREATE TABLE \"cliente\" ( 'cod_cliente' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'nombre' TEXT NOT NULL, 'direccion' TEXT NOT NULL, 'telefono' TEXT NOT NULL, 'email' TEXT NOT NULL )");
        db.execSQL("CREATE TABLE \"kebabs\" ( 'cod_pedido' INTEGER NOT NULL, 'cod_tipo_kebab' INTEGER NOT NULL, 'cod_tipo_carne' INTEGER NOT NULL, 'cod_tamano' INTEGER NOT NULL, 'cantidad' INTEGER DEFAULT 0, 'cod_bebabs' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'pedido_completado' INTEGER NOT NULL DEFAULT 0,  FOREIGN KEY('cod_pedido') REFERENCES 'pedido'('cod_pedido') on delete set null )");
        db.execSQL("CREATE TABLE \"pedido\" ( 'cod_pedido' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'cod_cliente' INTEGER NOT NULL UNIQUE, FOREIGN KEY(`cod_pedido`) REFERENCES 'cliente'('cod_cliente') on delete set null )");
        db.execSQL("CREATE TABLE 'tamano' ( 'cod_tamano' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'nombre_tamano' TEXT NOT NULL UNIQUE, 'precio' INTEGER NOT NULL )");
        db.execSQL("CREATE TABLE 'tipo_carne' ( 'cod_tipo_carne' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'nombre_tipo_carne' INTEGER NOT NULL, 'precio' INTEGER NOT NULL )");
        db.execSQL("CREATE TABLE 'tipo_kebab' ( 'cod_tipo_kebab' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'nombre_tipo_kebab' TEXT NOT NULL UNIQUE, 'precio' INTEGER NOT NULL )");
        db.execSQL("CREATE TABLE 'info_bebida' ( 'cod_bebida' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 'nombre_bebida' TEXT NOT NULL UNIQUE, 'precio' INTEGER NOT NULL )");

        /*
        * Rellenado de las tablas
        */

        //Kebabs
        db.execSQL("INSERT INTO tipo_carne (nombre_tipo_carne, precio) VALUES ('Döner', 4.00)");
        db.execSQL("INSERT INTO tipo_carne (nombre_tipo_carne, precio) VALUES ('Dürüm', 4.50)");
        db.execSQL("INSERT INTO tipo_carne (nombre_tipo_carne, precio) VALUES ('Lahmacum', 5.00)");
        db.execSQL("INSERT INTO tipo_carne (nombre_tipo_carne, precio) VALUES ('Shawarma', 4.50)");
        db.execSQL("INSERT INTO tipo_carne (nombre_tipo_carne, precio) VALUES ('Gyros', 5.00)");

        db.execSQL("INSERT INTO tipo_kebab (nombre_tipo_kebab, precio) VALUES ('Pollo', 0.50)");
        db.execSQL("INSERT INTO tipo_kebab (nombre_tipo_kebab, precio) VALUES ('Ternera', 1.0)");
        db.execSQL("INSERT INTO tipo_kebab (nombre_tipo_kebab, precio) VALUES ('Cordero', 0.50)");

        db.execSQL("INSERT INTO tamano (nombre_tamano, precio) VALUES ('Normal', 0.00)");
        db.execSQL("INSERT INTO tamano (nombre_tamano, precio) VALUES ('Completo', 1.00)");

        //Bebidas
        db.execSQL("INSERT INTO info_bebida (nombre_bebida, precio) VALUES ('Refesco cola', 1,00)");
        db.execSQL("INSERT INTO info_bebida (nombre_bebida, precio) VALUES ('Refresco limon', 1.00)");
        db.execSQL("INSERT INTO info_bebida (nombre_bebida, precio) VALUES ('Refresco naranja', 1.00)");
        db.execSQL("INSERT INTO info_bebida (nombre_bebida, precio) VALUES ('Vino', 8.00)");
        db.execSQL("INSERT INTO info_bebida (nombre_bebida, precio) VALUES ('Cerveza', 1.50)");
        db.execSQL("INSERT INTO info_bebida (nombre_bebida, precio) VALUES ('Agua', 0.50)");
    }
}
