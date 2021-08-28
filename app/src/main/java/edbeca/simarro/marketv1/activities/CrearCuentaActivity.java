package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edbeca.simarro.marketv1.BD.MiTiendaOperacional;
import edbeca.simarro.marketv1.DAO.UsuarioDAO;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Usuario;

public class CrearCuentaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText regclave, regusuario, regemail;
    private Button btnregistrarme;
    private MiTiendaOperacional mto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        mto = MiTiendaOperacional.getInstance(this);

        regclave = (EditText)findViewById(R.id.regclave);
        regusuario = (EditText)findViewById(R.id.regusuario);
        regemail = (EditText)findViewById(R.id.regemail);

        btnregistrarme = (Button)findViewById(R.id.btnRegistrarme);
        btnregistrarme.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String usuario = regusuario.getText().toString();
        String clave = regclave.getText().toString();
        String email = regemail.getText().toString();

        Usuario u = new Usuario(usuario, clave, email);
        UsuarioDAO uDAO = new UsuarioDAO();

        uDAO.add(u);

        Toast.makeText(this, "Usuario " + u.getNombre() + " registrado correctamente", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}