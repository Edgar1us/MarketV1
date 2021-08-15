package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Usuario;

public class PrincipalActivity extends AppCompatActivity {

    private TextView txtBienvenida;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");

        txtBienvenida = (TextView)findViewById(R.id.txtBienvenida);
        txtBienvenida.setText(usuario.getNombre());

    }
}