package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Usuario;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtBienvenida;
    private Usuario usuario;
    private ImageButton btnVender, btnComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");

        txtBienvenida = (TextView)findViewById(R.id.txtBienvenida);
        txtBienvenida.setText(usuario.getNombre());

        btnVender = (ImageButton)findViewById(R.id.btnVender);
        btnComprar = (ImageButton)findViewById(R.id.btnComprar);
        btnVender.setOnClickListener(this);
        btnComprar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent;
        switch (view.getId()){
            case R.id.btnComprar:
                intent = new Intent(view.getContext(), ComprasActivity.class);
                intent.putExtra("Usuario", usuario);
                startActivity(intent);
                break;

            case R.id.btnVender:
                Toast.makeText(this, Double.toString(usuario.getDinero()), Toast.LENGTH_SHORT).show();
                intent = new Intent(view.getContext(), VentasActivity.class);
                intent.putExtra("Usuario", usuario);
                startActivity(intent);
                break;

        }
    }



}