package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edbeca.simarro.marketv1.BD.MiTiendaOperacional;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.fragments.ComprasFragment;
import edbeca.simarro.marketv1.pojo.Usuario;

public class ComprasActivity extends AppCompatActivity {

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

    }
}