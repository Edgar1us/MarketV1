package edbeca.simarro.marketv1.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import edbeca.simarro.marketv1.BD.MiTiendaOperacional;
import edbeca.simarro.marketv1.DAO.ProductoDAO;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.adapters.AdapterProductos;
import edbeca.simarro.marketv1.pojo.Producto;
import edbeca.simarro.marketv1.pojo.Usuario;

public class VentasFragment extends Fragment {

    private ListView lAvender;
    private Usuario usuario;
    private MiTiendaOperacional mto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ventas, container, false);
    }

    public void mostrarProductos(){

        usuario = (Usuario)getArguments().get("Usuario");
        mto = (MiTiendaOperacional)getArguments().get("Tienda");
        lAvender = (ListView) getView().findViewById(R.id.lAvender);

        ProductoDAO productoDAO = new ProductoDAO();

        //Toast.makeText(this.getActivity(), ao.getPeliculas(usuario).get(0).getTitulo(), Toast.LENGTH_SHORT).show();
        lAvender.setAdapter(new AdapterProductos<>(this, productoDAO.getProductos(usuario)));

    }


}