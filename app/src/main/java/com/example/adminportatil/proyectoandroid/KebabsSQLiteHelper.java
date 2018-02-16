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

        crearBd(db);
    }

    private void crearBd(SQLiteDatabase db){
        db.execSQL("CREATE TABLE \"bebidas\" ( `cod_pedido` INTEGER NOT NULL, `cod_bebida` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `nombre_bebida` TEXT NOT NULL UNIQUE, `precio` INTEGER NOT NULL, `cantidad` INTEGER DEFAULT 0, FOREIGN KEY(`cod_pedido`) REFERENCES `pedido`(`cod_pedido`) on delete set null)");
        db.execSQL("CREATE TABLE \"cliente\" ( `cod_cliente` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `nombre` TEXT NOT NULL, `direccion` TEXT NOT NULL, `telefono` TEXT NOT NULL, `email` TEXT NOT NULL )");
        db.execSQL("CREATE TABLE \"kebabs\" ( `cod_pedido` INTEGER NOT NULL, `cod_tipo_kebab` INTEGER NOT NULL, `cod_tipo_carne` INTEGER NOT NULL, `cod_tamano` INTEGER NOT NULL, `cantidad` INTEGER DEFAULT 0, `cod_bebabs` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, FOREIGN KEY(`cod_pedido`) REFERENCES `pedido`(`cod_pedido`) on delete set null )");
        db.execSQL("CREATE TABLE \"pedido\" ( `cod_pedido` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `cod_cliente` INTEGER NOT NULL UNIQUE, FOREIGN KEY(`cod_pedido`) REFERENCES `cliente`(`cod_cliente`) on delete set null )");
        db.execSQL("CREATE TABLE `tamano` ( `cod_tamano` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `nombre_tamano` TEXT NOT NULL UNIQUE, `precio` INTEGER NOT NULL )");
        db.execSQL("CREATE TABLE `tipo_carne` ( `cod_tipo_carne` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `nombre_tipo_carne` INTEGER NOT NULL, `precio` INTEGER NOT NULL )");
        db.execSQL("CREATE TABLE `tipo_kebab` ( `cod_tipo_kebab` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `nombre_tipo_kebab` TEXT NOT NULL UNIQUE, `precio` INTEGER NOT NULL )");
    }
}
