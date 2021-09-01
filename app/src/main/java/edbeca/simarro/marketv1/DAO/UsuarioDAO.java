package edbeca.simarro.marketv1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;

import edbeca.simarro.marketv1.BD.MiBD;
import edbeca.simarro.marketv1.pojo.Usuario;

public class UsuarioDAO implements PojoDAO {

    public static final String C_TABLA = "usuarios" ;

    public static final String FIELD_USUARIO_ID = "id";
    public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_CLAVE = "claveSeguridad";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_DINERO = "dinero";

    private Context contexto;
    private MiBD miBD;
    private SQLiteDatabase db;

    private String[] colums = new String[]{ FIELD_USUARIO_ID, FIELD_NOMBRE, FIELD_CLAVE, FIELD_EMAIL, FIELD_DINERO} ;

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Usuario u = (Usuario) obj;
        contentValues.put("nombre", u.getNombre());
        contentValues.put("claveSeguridad", u.getClaveSeguridad());
        contentValues.put("email", u.getEmail());
        contentValues.put("dinero", u.getDinero());
        return MiBD.getDB().insert("usuarios", null, contentValues);
    }

    @Override
    public int update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Usuario u = (Usuario) obj;
        contentValues.put("nombre", u.getNombre());
        contentValues.put("claveSeguridad", u.getClaveSeguridad());
        contentValues.put("email", u.getEmail());
        contentValues.put("dinero", u.getDinero());
        String condicion = "id=" + String.valueOf(u.getIdUsuario());

        int resultado = MiBD.getDB().update("usuarios", contentValues, condicion, null);

        return resultado;
    }

    @Override
    public void delete(Object obj) {

    }

    @Override
    public Object search(Object obj) {
        Usuario u = (Usuario) obj;
        String condicion = "";
        if(TextUtils.isEmpty(u.getEmail())){
            condicion = "id=" + String.valueOf(u.getIdUsuario());
        }else{
            condicion = "email=" + "'" + u.getEmail() + "'";
        }

        String[] columnas = {
                "id","nombre","claveseguridad","email","dinero"
        };
        Cursor cursor = MiBD.getDB().query("usuarios", columnas, condicion, null, null, null, null);
        Usuario nuevoUsuario = null;
        if (cursor.moveToFirst()) {
            nuevoUsuario = new Usuario();
            nuevoUsuario.setIdUsuario(cursor.getInt(0));
            nuevoUsuario.setNombre(cursor.getString(1));
            nuevoUsuario.setClaveSeguridad(cursor.getString(2));
            nuevoUsuario.setEmail(cursor.getString(3));
            nuevoUsuario.setDinero(cursor.getDouble(4));


        }
        return nuevoUsuario;
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        String[] columnas = {
                "id","nombre","claveseguridad","email","dinero"
        };
        Cursor cursor = MiBD.getDB().query("usuarios", columnas, null, null, null, null, null);
        //PeliculaDAO peliculaDAO = new PeliculaDAO();
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Usuario u = new Usuario();
                u.setIdUsuario(cursor.getInt(0));
                u.setNombre(cursor.getString(1));
                u.setClaveSeguridad(cursor.getString(2));
                u.setEmail(cursor.getString(3));
                u.setDinero(cursor.getDouble(4));
                //c.setListaCuentas(MiBD.getInstance(null).getCuentaDAO().getCuentas(c));
                listaUsuarios.add(u);

            } while(cursor.moveToNext());
        }
        return listaUsuarios;
    }

    public UsuarioDAO(){

    }

    public UsuarioDAO(Context context) {
        this.contexto = context;
    }

    public Cursor getRegistro(long id){
        String condicion = FIELD_USUARIO_ID + "=" + id;
        Cursor c = db.query(true, C_TABLA, colums, condicion, null, null, null, null,null);
        if (c!=null){
            c.moveToFirst();
        }
        return c;
    }

    public UsuarioDAO abrir(){
        miBD = new MiBD(contexto);
        db = miBD.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        miBD.close();
    }

    public Cursor getCursor() {
        Cursor c = db.query( true, C_TABLA, colums, null, null, null, null, null, null);
        return c;
    }

}
