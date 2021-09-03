package edbeca.simarro.marketv1.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;

import edbeca.simarro.marketv1.BD.MiBD;
import edbeca.simarro.marketv1.pojo.Transaccion;
import edbeca.simarro.marketv1.pojo.Usuario;

public class TransaccionDAO implements PojoDAO {

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Transaccion t = (Transaccion) obj;
        contentValues.put("tipo", t.getTipo());
        contentValues.put("fecha", t.getFecha().getTime());
        contentValues.put("descripcion", t.getDescripcion());
        contentValues.put("importe", t.getImporte());
        contentValues.put("idusuarioorigen", t.getUsuarioOrigen().getIdUsuario());
        contentValues.put("idusuariodestino", t.getUsuarioDestino().getIdUsuario());

        return MiBD.getDB().insert("transacciones", null, contentValues);
    }

    @Override
    public int update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Transaccion t = (Transaccion) obj;
        contentValues.put("tipo", t.getTipo());
        contentValues.put("fecha", t.getFecha().getTime());
        contentValues.put("descripcion", t.getDescripcion());
        contentValues.put("importe", t.getImporte());
        contentValues.put("idusuarioorigen", t.getUsuarioOrigen().getIdUsuario());
        contentValues.put("idusuariodestino", t.getUsuarioDestino().getIdUsuario());

        String condicion = "id=" + String.valueOf(t.getId());

        return MiBD.getDB().update("transacciones", contentValues, condicion, null);
    }

    @Override
    public void delete(Object obj) {
        Transaccion t = (Transaccion) obj;
        String condicion = "id=" + String.valueOf(t.getId());

        //Se borra el producto indicado en el campo de texto
        MiBD.getDB().delete("transacciones", condicion, null);
    }

    @Override
    public Object search(Object obj) {

        Transaccion t = (Transaccion) obj;
        String condicion = "id=" + String.valueOf(t.getId());
        String[] columnas = {
                "id","tipo","fechaoperacion","descripcion","importe","idcuentaorigen", "idcuentadestino"
        };
        Cursor cursor = MiBD.getDB().query("movimientos", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            t.setId(cursor.getInt(0));
            t.setTipo(cursor.getInt(1));
            t.setFecha(new Date(cursor.getLong(2)));
            t.setDescripcion(cursor.getString(3));
            t.setImporte(cursor.getDouble(4));

            // Asignamos la cuenta de origen
            Usuario uo = new Usuario();
            uo.setIdUsuario(cursor.getInt(5));
            t.setUsuarioOrigen((Usuario) MiBD.getInstance(null).getUsuarioDAO().search(uo));

            // Asignamos la cuenta de destino
            int aux = cursor.getInt(6);
            if (aux == -1) {
                uo.setIdUsuario(-1);
            }else {
                uo.setIdUsuario(aux);
                t.setUsuarioOrigen((Usuario) MiBD.getInstance(null).getUsuarioDAO().search(uo));
            }

            return t;
        }else{
            return null;
        }
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Transaccion> listaTransacciones = new ArrayList<Transaccion>();
        String[] columnas = {
                "id","tipo","fechaoperacion","descripcion","importe","idcuentaorigen", "idcuentadestino"
        };
        Cursor cursor = MiBD.getDB().query("movimientos", columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Transaccion t = new Transaccion();
                t.setId(cursor.getInt(0));
                t.setTipo(cursor.getInt(1));
                t.setFecha(new Date(cursor.getLong(2)));
                t.setDescripcion(cursor.getString(3));
                t.setImporte(cursor.getDouble(4));

                // Asignamos la cuenta de origen
                Usuario uo = new Usuario();
                uo.setIdUsuario(cursor.getInt(5));
                t.setUsuarioOrigen((Usuario) MiBD.getInstance(null).getUsuarioDAO().search(uo));


                // Asignamos la cuenta de destino
                uo = new Usuario();
                int aux = cursor.getInt(6);
                if (aux == -1) {
                    uo.setIdUsuario(-1);
                    t.setUsuarioOrigen(uo);
                }else {
                    uo.setIdUsuario(aux);
                    t.setUsuarioDestino((Usuario) MiBD.getInstance(null).getUsuarioDAO().search(uo));
                }


                listaTransacciones.add(t);

            } while(cursor.moveToNext());
        }
        return listaTransacciones;
    }
}
