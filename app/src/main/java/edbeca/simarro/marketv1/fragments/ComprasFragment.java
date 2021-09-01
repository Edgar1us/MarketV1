package edbeca.simarro.marketv1.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import edbeca.simarro.marketv1.BD.MiTiendaOperacional;
import edbeca.simarro.marketv1.DAO.ProductoDAO;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.adapters.AdapterProductos;
import edbeca.simarro.marketv1.pojo.Producto;
import edbeca.simarro.marketv1.pojo.Usuario;


public class ComprasFragment extends Fragment implements AdapterView.OnItemClickListener {

    private Usuario usuario;
    private MiTiendaOperacional mto;
    private ListView lAComprar;
    private ComprasListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compras, container, false);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void mostrarProductosEnVenta(){

        usuario = (Usuario)getArguments().get("Usuario");
        mto = (MiTiendaOperacional)getArguments().get("Tienda");

        lAComprar = (ListView)getView().findViewById(R.id.lACompras);

        ProductoDAO productoDAO = new ProductoDAO();

        //Toast.makeText(this.getActivity(), ao.getPeliculas(usuario).get(0).getTitulo(), Toast.LENGTH_SHORT).show();
        lAComprar.setAdapter(new AdapterProductos<>(this, productoDAO.getAll()));
        lAComprar.setOnItemClickListener(this);

    }

    public void setComprasListener(ComprasListener listener){
        this.listener = listener;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (listener!=null)
            listener.onProductoSeleccionado((Producto)lAComprar.getAdapter().getItem(i));
    }
}