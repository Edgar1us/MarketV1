package edbeca.simarro.marketv1.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import java.util.ArrayList;

import edbeca.simarro.marketv1.BD.MiBD;
import edbeca.simarro.marketv1.pojo.Producto;
import edbeca.simarro.marketv1.pojo.Usuario;

public class ProductoDAO implements PojoDAO {

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Producto p = (Producto) obj;

        contentValues.put("nombre", p.getNombre());
        contentValues.put("precio", p.getPrecio());
        contentValues.put("descripcion", p.getDescripcion());
        contentValues.put("estado",p.getEstado());
        contentValues.put("tiempo",p.getTiempo());
        contentValues.put("idUsuario", p.getUsuario().getIdUsuario());
        return MiBD.getDB().insert("productos", null, contentValues);
    }

    @Override
    public int update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Producto p = (Producto) obj;
        contentValues.put("nombre", p.getNombre());
        contentValues.put("precio", p.getPrecio());
        contentValues.put("descripcion", p.getDescripcion());
        contentValues.put("estado",p.getEstado());
        contentValues.put("tiempo",p.getTiempo());
        contentValues.put("idUsuario", p.getUsuario().getIdUsuario());

        String condicion = "id=" + String.valueOf(p.getIdProducto());

        return MiBD.getDB().update("productos", contentValues, condicion, null);
    }

    @Override
    public void delete(Object obj) {
        Producto p = (Producto) obj;
        String condicion = "id=" + String.valueOf(p.getIdProducto());

        //Se borra el producto indicado en el campo de texto
        MiBD.getDB().delete("productos", condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Producto p = (Producto) obj;
        String condicion = "";
        if(TextUtils.isEmpty(p.getNombre())){
            condicion = "id=" + String.valueOf(p.getIdProducto());
        }else{
            condicion = "nombre=" + String.valueOf(p.getNombre())  + " AND estado = " + String.valueOf(p.getEstado());
        }

        String[] columnas = {
                "id","nombre","precio","descripcion","estado","tiempo", "idUsuario"
        };
        Cursor cursor = MiBD.getDB().query("productos", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            p.setIdProducto(cursor.getInt(0));
            p.setNombre(cursor.getString(1));
            p.setPrecio(cursor.getDouble(2));
            p.setDescripcion(cursor.getString(3));
            p.setEstado(cursor.getString(4));
            p.setTiempo(cursor.getInt(5));

            // Obtenemos el cliente y lo asignamos
            Usuario a = new Usuario();
            a.setIdUsuario(cursor.getInt(6));
            a = (Usuario) MiBD.getInstance(null).getUsuarioDAO().search(a);
            p.setUsuario(a);


            // Obtenemos la lista de movimientos y los asignamos
            //c.setListaMovimientos(MiBD.getInstance(null).getMovimientoDAO().getMovimientos(c));

            return p;
        }else{
            return null;
        }
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        String[] columnas = {
                "id","nombre","precio","descripcion","estado","tiempo", "idUsuario"
        };
        Cursor cursor = MiBD.getDB().query("productos", columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Producto p = new Producto();
                p.setIdProducto(cursor.getInt(0));
                p.setNombre(cursor.getString(1));
                p.setPrecio(cursor.getDouble(2));
                p.setDescripcion(cursor.getString(3));
                p.setEstado(cursor.getString(4));
                p.setTiempo(cursor.getInt(5));

                // Obtenemos el cliente y lo asignamos
                Usuario a = new Usuario();
                a.setIdUsuario(cursor.getInt(6));
                a = (Usuario) MiBD.getInstance(null).getUsuarioDAO().search(a);
                p.setUsuario(a);

                // Obtenemos la lista de movimientos y los asignamos
                //c.setListaMovimientos(MiBD.getInstance(null).getMovimientoDAO().getMovimientos(c));

                listaProductos.add(p);

            } while(cursor.moveToNext());
        }
        return listaProductos;
    }

    public ArrayList getProductos(Usuario usuario) {
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        String condicion = "idUsuario=" + String.valueOf(usuario.getIdUsuario());
        String[] columnas = {
                "id", "nombre", "precio", "descripcion", "estado","tiempo", "idUsuario"

        };
        Cursor cursor = MiBD.getDB().query("productos", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Producto p = new Producto();
                p.setIdProducto(cursor.getInt(0));
                p.setNombre(cursor.getString(1));
                p.setPrecio(cursor.getDouble(2));
                p.setDescripcion(cursor.getString(3));
                p.setEstado(cursor.getString(4));
                p.setTiempo(cursor.getInt(5));

                p.setUsuario(usuario);

                listaProductos.add(p);

            } while (cursor.moveToNext());
        }
        return listaProductos;
    }



}
