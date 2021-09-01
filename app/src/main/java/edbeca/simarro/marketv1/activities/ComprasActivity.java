package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import edbeca.simarro.marketv1.BD.MiTiendaOperacional;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.fragments.ComprasFragment;
import edbeca.simarro.marketv1.fragments.ComprasListener;
import edbeca.simarro.marketv1.pojo.Producto;
import edbeca.simarro.marketv1.pojo.Usuario;

public class ComprasActivity extends AppCompatActivity implements ComprasListener {

    private Usuario usuario;
    private MiTiendaOperacional mto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        mto = MiTiendaOperacional.getInstance(this);
        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");

        ComprasFragment fCompras = (ComprasFragment)getSupportFragmentManager().findFragmentById(R.id.fCompras);

        Bundle bundle = new Bundle();
        bundle.putSerializable("Usuario", usuario);
        bundle.putSerializable("Tienda", mto);

        fCompras.setArguments(bundle);
        fCompras.mostrarProductosEnVenta();
        fCompras.setComprasListener(this);

    }

    @Override
    public void onProductoSeleccionado(Producto producto) {
        //Toast.makeText(this, "Vas a comprar " + producto.getNombre(), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, DetalleActivity.class);
        i.putExtra("Usuario", usuario);
        i.putExtra("Producto", producto);
        startActivity(i);

    }
}