package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edbeca.simarro.marketv1.DAO.UsuarioDAO;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Usuario;

public class CambiarClaveActivity extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuario;
    private EditText edtVieja, edtNueva;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_clave);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");
        edtVieja = (EditText)findViewById(R.id.editVieja);
        edtNueva = (EditText)findViewById(R.id.editNueva);

        btnGuardar = (Button)findViewById(R.id.btnCambiarClave);
        btnGuardar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        UsuarioDAO Udao = new UsuarioDAO();
        String passVieja = edtVieja.getText().toString();
        String passNueva = edtNueva.getText().toString();

        if (passVieja.compareToIgnoreCase(usuario.getClaveSeguridad())==0){
            usuario.setClaveSeguridad(passNueva);
            Udao.update(usuario);
        }

        Intent i = new Intent(CambiarClaveActivity.this, LoginActivity.class);
        startActivity(i);



    }
}