package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import edbeca.simarro.marketv1.BD.MiTiendaOperacional;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.fragments.VentasFragment;
import edbeca.simarro.marketv1.pojo.Usuario;

public class VentasActivity extends AppCompatActivity {

    private Usuario usuario;
    private MiTiendaOperacional mto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);

        mto = MiTiendaOperacional.getInstance(this);
        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");

        VentasFragment fVentas = (VentasFragment)getSupportFragmentManager().findFragmentById(R.id.fVentas);

        Bundle bundle = new Bundle();
        bundle.putSerializable("Usuario", usuario);
        bundle.putSerializable("Tienda", mto);

        fVentas.setArguments(bundle);
        fVentas.mostrarProductos();


    }
}