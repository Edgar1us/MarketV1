package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edbeca.simarro.marketv1.BD.MiTiendaOperacional;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.fragments.VentasFragment;
import edbeca.simarro.marketv1.pojo.Usuario;

public class VentasActivity extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuario;
    private MiTiendaOperacional mto;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);

        mto = MiTiendaOperacional.getInstance(this);
        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");

        btnAdd = (Button)findViewById(R.id.btnadd);
        btnAdd.setOnClickListener(this);

        VentasFragment fVentas = (VentasFragment)getSupportFragmentManager().findFragmentById(R.id.fVentas);

        Bundle bundle = new Bundle();
        bundle.putSerializable("Usuario", usuario);
        bundle.putSerializable("Tienda", mto);

        fVentas.setArguments(bundle);
        fVentas.mostrarProductos();


    }

    @Override
    public void onClick(View view) {

        Intent i = new Intent(this, NuevoActivity.class);
        i.putExtra("Usuario", usuario);
        startActivity(i);

    }
}