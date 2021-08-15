package edbeca.simarro.marketv1.BD;

import android.content.Context;

import java.io.Serializable;

import edbeca.simarro.marketv1.pojo.Usuario;

public class MiTiendaOperacional implements Serializable {

    private MiBD miBD;

    protected MiTiendaOperacional(Context context){
        miBD = MiBD.getInstance(context);
    }

    private static MiTiendaOperacional instance = null;

    public static MiTiendaOperacional getInstance(Context context) {
        if(instance == null) {
            instance = new MiTiendaOperacional(context);
        }
        return instance;
    }

    public Usuario login(Usuario u){
        Usuario aux = (Usuario) miBD.getUsuarioDAO().search(u);
        if(aux==null){
            return null;
        }else if (aux.getClaveSeguridad().equals(u.getClaveSeguridad())){
            return aux;
        }else{
            return null;
        }
    }



}
