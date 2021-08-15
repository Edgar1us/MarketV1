package edbeca.simarro.marketv1.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.Serializable;

import edbeca.simarro.marketv1.DAO.ProductoDAO;
import edbeca.simarro.marketv1.DAO.UsuarioDAO;

public class MiBD extends SQLiteOpenHelper implements Serializable {

    private static SQLiteDatabase db;

    private static final String database = "Tienda";

    //versión de la base de datos
    private static final int version = 1;

    private String sqlCreacionUsuarios = "CREATE TABLE usuarios ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre STRING, " +
            "claveSeguridad STRING, email STRING);";

    //Creacion de la tabla productos
    private String sqlCreacionProductos = "CREATE TABLE productos ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre STRING, precio DOUBLE, descripcion STRING, estado STRING, tiempo INTEGER, idUsuario INTEGER);";


    private static MiBD instance = null;

    private static UsuarioDAO usuarioDAO;
    private static ProductoDAO productoDAO;

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public static MiBD getInstance(Context context) {
        if(instance == null) {
            instance = new MiBD(context);
            db = instance.getWritableDatabase();
            usuarioDAO = new UsuarioDAO();
            productoDAO = new ProductoDAO();
        }
        return instance;
    }

    public static SQLiteDatabase getDB(){
        return db;
    }
    public static void closeDB(){db.close();};

    public MiBD(Context context) {
        super( context, database, null, version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacionUsuarios);
        db.execSQL(sqlCreacionProductos);
        insercionDatos(db);
        Log.i("SQLite", "Se crea la base de datos " + database + " version " + version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion , int newVersion) {
        Log.i("SQLite", "Control de versiones: Old Version=" + oldVersion + " New Version= " + newVersion  );
        if ( newVersion > oldVersion )
        {
            //elimina tabla
            db.execSQL( "DROP TABLE IF EXISTS usuarios" );
            db.execSQL( "DROP TABLE IF EXISTS productos" );
            //y luego creamos la nueva tabla
            db.execSQL(sqlCreacionUsuarios);
            db.execSQL(sqlCreacionProductos);

            insercionDatos(db);
            Log.i("SQLite", "Se actualiza versión de la base de datos, New version= " + newVersion  );
        }
    }

    private void insercionDatos(SQLiteDatabase db){

        //INSERCIÓN USUARIOS
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (1, 'edgar', '1234', 'edgar.gmail.com');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (2, 'oscar', '1234', 'oscar.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (3, 'pepe', '1234', 'pepe.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (4, 'mortadelo', '1234', 'mortadelo.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (5, 'sancho', '1234', 'sancho.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (6, 'quijote', '1234', 'quijote.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (7, 'jorge', '1234', 'jorge.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (8, 'jordi', '1234', 'jordi.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (9, 'borja', '1234', 'borja.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (10, 'miguel', '1234', 'miguel.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (11, 'andreu', '1234', 'andreu.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (12, 'laura', '1234', 'laura.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (13, 'dani', '1234', 'dani.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (14, 'manu', '1234', 'manu.pitia.es');");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email) VALUES (15, 'herme', '1234', 'herme.pitia.es');");

        //INSERCION PRODUCTOS
        db.execSQL("INSERT INTO productos(id, nombre, precio, descripcion, estado, tiempo, idUsuario) VALUES (1, 'Camiseta', 15.5, 'Se vende camiseta de los Rolling stones', 'Bueno', '4', 1);");

    }

}
