package edbeca.simarro.marketv1.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.Serializable;

import edbeca.simarro.marketv1.DAO.ProductoDAO;
import edbeca.simarro.marketv1.DAO.TransaccionDAO;
import edbeca.simarro.marketv1.DAO.UsuarioDAO;
import edbeca.simarro.marketv1.pojo.Transaccion;
import edbeca.simarro.marketv1.pojo.Usuario;

public class MiBD extends SQLiteOpenHelper implements Serializable {

    private static SQLiteDatabase db;

    private static final String database = "Tienda";

    //versión de la base de datos
    private static final int version = 6;

    private String sqlCreacionUsuarios = "CREATE TABLE usuarios ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre STRING, " +
            "claveSeguridad STRING, email STRING, dinero DOUBLE);";

    //Creacion de la tabla productos
    private String sqlCreacionProductos = "CREATE TABLE productos ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre STRING, precio DOUBLE, descripcion STRING, estado STRING, tiempo INTEGER, idUsuario INTEGER);";

    private String sqlCreacionTransacciones = "CREATE TABLE transacciones ( id INTEGER PRIMARY KEY AUTOINCREMENT, tipo INTEGER, fecha LONG," +
            " descripcion STRING, importe DOUBLE, idusuarioorigen INTEGER, idusuariodestino INTEGER);";


    private static MiBD instance = null;

    private static UsuarioDAO usuarioDAO;
    private static ProductoDAO productoDAO;
    private static TransaccionDAO transaccionDAO;

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public TransaccionDAO getTransaccionDAO(){return transaccionDAO;}

    public static MiBD getInstance(Context context) {
        if(instance == null) {
            instance = new MiBD(context);
            db = instance.getWritableDatabase();
            usuarioDAO = new UsuarioDAO();
            productoDAO = new ProductoDAO();
            transaccionDAO = new TransaccionDAO();
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
        db.execSQL(sqlCreacionTransacciones);
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
            db.execSQL( "DROP TABLE IF EXISTS transacciones" );
            //y luego creamos la nueva tabla
            db.execSQL(sqlCreacionUsuarios);
            db.execSQL(sqlCreacionProductos);
            db.execSQL(sqlCreacionTransacciones);

            insercionDatos(db);
            Log.i("SQLite", "Se actualiza versión de la base de datos, New version= " + newVersion  );
        }
    }

    private void insercionDatos(SQLiteDatabase db){

        //INSERCIÓN USUARIOS
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (1, 'edgar', '1234', 'edgar.gmail.com', 1000.50);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (2, 'oscar', '1234', 'oscar.pitia.es', 2000.50);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (3, 'pepe', '1234', 'pepe.pitia.es', 3000.50);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (4, 'mortadelo', '1234', 'mortadelo.pitia.es', 4000.50);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (5, 'sancho', '1234', 'sancho.pitia.es', 1564.1235);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (6, 'quijote', '1234', 'quijote.pitia.es', 2542.125);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (7, 'jorge', '1234', 'jorge.pitia.es', 810.123);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (8, 'jordi', '1234', 'jordi.pitia.es', 155.00);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (9, 'borja', '1234', 'borja.pitia.es', 5213654.1552);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (10, 'miguel', '1234', 'miguel.pitia.es', 410.60);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (11, 'andreu', '1234', 'andreu.pitia.es', 9871.23);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (12, 'laura', '1234', 'laura.pitia.es', 59.3289);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (13, 'dani', '1234', 'dani.pitia.es', 456.123);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (14, 'manu', '1234', 'manu.pitia.es', 12.58);");
        db.execSQL("INSERT INTO usuarios(id, nombre, claveSeguridad, email, dinero) VALUES (15, 'herme', '1234', 'herme.pitia.es', 47.63954);");

        //INSERCION PRODUCTOS
        db.execSQL("INSERT INTO productos(id, nombre, precio, descripcion, estado, tiempo, idUsuario) VALUES (1, 'Camiseta', 15.5, 'Se vende camiseta de los Rolling stones', 'Bueno', '4', 1);");
        db.execSQL("INSERT INTO productos(id, nombre, precio, descripcion, estado, tiempo, idUsuario) VALUES (2, 'SmartTV', 300.50, 'Se vende SmartTV curved de la marca LG, con 59 pulgagas, resolución 4k', 'Bueno', '5', 1);");
        db.execSQL("INSERT INTO productos(id, nombre, precio, descripcion, estado, tiempo, idUsuario) VALUES (3, 'Tienda de acampada', 20.0, 'Con capacidad para 4 personas, fresh black', 'Semi-nuevo', '2', 1);");
        db.execSQL("INSERT INTO productos(id, nombre, precio, descripcion, estado, tiempo, idUsuario) VALUES (4, 'Xiaomi A2', 70.85, 'Se vende smartphone de segunda mano', 'Bueno', '6', 1);");
        db.execSQL("INSERT INTO productos(id, nombre, precio, descripcion, estado, tiempo, idUsuario) VALUES (5, 'Globo terrestre', 5, 'Se vende globo terrestre con leds', 'Bueno', '15', 1);");
        db.execSQL("INSERT INTO productos(id, nombre, precio, descripcion, estado, tiempo, idUsuario) VALUES (6, 'Altavoz Sony', 5, 'Se vende altavoz', 'Regular', '16', 2);");


    }

    public void actualizarDinero(Usuario u){
        db.execSQL("UPDATE usuarios SET dinero= "+u.getDinero()+" WHERE nombre='"+u.getNombre()+"' AND claveSeguridad='"+u.getClaveSeguridad()+"' AND email='"+u.getEmail()+"';");
    }

}
