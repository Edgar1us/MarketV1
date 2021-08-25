package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Usuario;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtBienvenida;
    private Usuario usuario;
    private ImageButton btnVender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");

        txtBienvenida = (TextView)findViewById(R.id.txtBienvenida);
        txtBienvenida.setText(usuario.getNombre());

        btnVender = (ImageButton)findViewById(R.id.btnVender);
        btnVender.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent i = new Intent(this, VentasActivity.class);
        i.putExtra("Usuario", usuario);
        startActivity(i);

    }
}