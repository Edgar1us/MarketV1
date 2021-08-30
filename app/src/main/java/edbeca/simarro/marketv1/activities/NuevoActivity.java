package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edbeca.simarro.marketv1.DAO.ProductoDAO;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Producto;
import edbeca.simarro.marketv1.pojo.Usuario;

public class NuevoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNombre, edtPrecio, edtDescrip, edtEstado, edtTiempo;
    private Button btnNuevo;
    private Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        u = (Usuario)getIntent().getSerializableExtra("Usuario");

        edtNombre = (EditText)findViewById(R.id.edtNombre);
        edtPrecio = (EditText)findViewById(R.id.edtPrecio);
        edtDescrip = (EditText)findViewById(R.id.edtDescripcion);
        edtEstado = (EditText)findViewById(R.id.edtEstado);
        edtTiempo = (EditText)findViewById(R.id.edtTiempo);

        btnNuevo = (Button)findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        String name = edtNombre.getText().toString();

        String p = edtPrecio.getText().toString();
        Double precio = Double.parseDouble(p);

        String descripcion = edtDescrip.getText().toString();

        String estado = edtEstado.getText().toString();

        String t = edtTiempo.getText().toString();
        int tiempo = Integer.parseInt(t);

        Producto producto = new Producto(name, precio, descripcion, estado, tiempo, u);

        ProductoDAO proDAO = new ProductoDAO();
        proDAO.add(producto);

        Intent i = new Intent(this, NuevoActivity.class);
        i.putExtra("Usuario", u);
        startActivity(i);

    }
}