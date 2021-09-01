package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Producto;
import edbeca.simarro.marketv1.pojo.Usuario;

public class DetalleActivity extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuario;
    private Producto producto;
    private TextView dNombre, dPrecio, dDesc, dTiempo, dEstado;
    private Button comprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");
        producto = (Producto)getIntent().getSerializableExtra("Producto");

        dNombre = (TextView)findViewById(R.id.detalleNombre);
        dPrecio = (TextView)findViewById(R.id.detallePrecio);
        dDesc = (TextView)findViewById(R.id.detalleDesc);
        dTiempo = (TextView)findViewById(R.id.detalleTiempo);
        dEstado = (TextView)findViewById(R.id.detalleEstado);

        dNombre.setText(producto.getNombre());
        dPrecio.setText(Double.toString(producto.getPrecio()));
        dDesc.setText(producto.getDescripcion());
        dTiempo.setText(Integer.toString(producto.getTiempo()));
        dEstado.setText(producto.getEstado());

        comprar = (Button)findViewById(R.id.comprar);
        comprar.setOnClickListener(this);

        //Toast.makeText(this, producto.getNombre(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {

    }
}