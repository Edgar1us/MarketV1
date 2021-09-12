package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edbeca.simarro.marketv1.BD.MiTiendaOperacional;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Usuario;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsuario, edtPassword;
    private Button btnLogin;
    private MiTiendaOperacional mto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        mto = MiTiendaOperacional.getInstance(this);

        edtPassword = (EditText)findViewById(R.id.clave);
        edtUsuario = (EditText)findViewById(R.id.usuario);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        edtUsuario.setText("oscar.pitia.es");
        edtPassword.setText("1234");

        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Usuario usuario = new Usuario();

        usuario.setEmail(edtUsuario.getText().toString());
        usuario.setClaveSeguridad(edtPassword.getText().toString());

        usuario = mto.login(usuario);
        if (usuario!=null){

            Intent i = new Intent(this, PrincipalActivity.class);
            i.putExtra("Usuario", usuario);
            startActivity(i);

        } else {
            Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
        }
    }
}