package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edbeca.simarro.marketv1.BD.MiBD;
import edbeca.simarro.marketv1.DAO.ProductoDAO;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Producto;
import edbeca.simarro.marketv1.pojo.Usuario;

public class DetalleActivity extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuario;
    private Producto producto;
    private TextView dNombre, dPrecio, dDesc, dTiempo, dEstado;
    private Button comprar;
    private MiBD miBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        miBD = MiBD.getInstance(getApplicationContext());

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

        Usuario origen = usuario;
        Usuario destino = producto.getUsuario();

        if (usuario.getDinero()>=producto.getPrecio()){
            /*usuario.setDinero(usuario.getDinero() - producto.getPrecio());
            producto.setUsuario(usuario);*/

            origen.setDinero(origen.getDinero()-producto.getPrecio());
            destino.setDinero(destino.getDinero()+producto.getPrecio());

            miBD.actualizarDinero(origen);
            miBD.actualizarDinero(destino);

            ProductoDAO paDAO = new ProductoDAO();
            paDAO.delete(producto);

           // Toast.makeText(this, " usuario origen" + usuario.getNombre() + " usuario destino" + producto.getUsuario().getNombre(), Toast.LENGTH_SHORT).show();

            Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
        }



    }
}