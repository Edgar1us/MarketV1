package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edbeca.simarro.marketv1.BD.Constantes;
import edbeca.simarro.marketv1.DAO.UsuarioDAO;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Usuario;

public class UsuarioActivity extends AppCompatActivity {

    private Cursor cursor;
    private int modo;
    private long id;

    private Usuario usuario;
    private UsuarioDAO usuarioDAO;

    private TextView tNombre, tPassword, tEmail, tDinero;

    private EditText editNombre;
    private EditText editPassword;
    private EditText editEmail, editDinero;

    private Button boton_cancelar, boton_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        if (extra == null) return;

        usuario = (Usuario)getIntent().getSerializableExtra("usuario");

        tNombre = (TextView)findViewById(R.id.tNombre);
        tPassword = (TextView)findViewById(R.id.tPassword);
        tEmail = (TextView)findViewById(R.id.tEmail);
        tDinero = (TextView)findViewById(R.id.tDinero);

        editNombre = (EditText)findViewById(R.id.editNombre);
        editPassword = (EditText)findViewById(R.id.editPassword);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editDinero = (EditText)findViewById(R.id.editDinero);

        boton_guardar = (Button) findViewById(R.id.boton_guardar);
        boton_cancelar = (Button) findViewById(R.id.boton_cancelar);

        usuarioDAO = new UsuarioDAO(this);

        try {
            usuarioDAO.abrir();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (extra.containsKey(usuarioDAO.FIELD_USUARIO_ID)){
            id = extra.getInt(usuarioDAO.FIELD_USUARIO_ID);
            consultar(id);
        }

        establecerModo(extra.getInt(Constantes.C_MODO));

        boton_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                guardar();
            }
        });

        boton_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

    }

    private void cancelar() {
        setResult(RESULT_CANCELED, null);
        finish();
    }

    private void guardar() {
        ContentValues reg = new ContentValues();

        if (modo == Constantes.C_EDITAR) {
            reg.put(UsuarioDAO.FIELD_USUARIO_ID, id);
        }
        reg.put(UsuarioDAO.FIELD_NOMBRE, editNombre.getText().toString());
        reg.put(UsuarioDAO.FIELD_CLAVE, editPassword.getText().toString());
        reg.put(UsuarioDAO.FIELD_EMAIL, editEmail.getText().toString());
        reg.put(UsuarioDAO.FIELD_DINERO, editDinero.getText().toString());

        if (modo == Constantes.C_EDITAR) {
            Toast.makeText(UsuarioActivity.this, R.string.usuario_editar_confirmacion,
                    Toast.LENGTH_SHORT).show();
            usuarioDAO.updateBD(reg);
        }

        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_usuario_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_usuario_edit:
                establecerModo(Constantes.C_EDITAR);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void consultar(long id) {

        cursor = usuarioDAO.getRegistro(id);
        editNombre.setText(cursor.getString(
                cursor.getColumnIndex(usuarioDAO.FIELD_NOMBRE)));
        editPassword.setText(cursor.getString(
                cursor.getColumnIndex(usuarioDAO.FIELD_CLAVE)));
        editEmail.setText(cursor.getString(
                cursor.getColumnIndex(usuarioDAO.FIELD_EMAIL)));
        editDinero.setText(cursor.getString(
                cursor.getColumnIndex(usuarioDAO.FIELD_DINERO)));

    }

    private void setEdicion(boolean opcion) {
        editNombre.setEnabled(opcion);
        editPassword.setEnabled(opcion);
        editEmail.setEnabled(opcion);
        editDinero.setEnabled(opcion);
    }

    private void establecerModo(int m) {
        this.modo = m;

        if (modo == Constantes.C_VISUALIZAR) {
            this.setEdicion(false);
        }else if (modo == Constantes.C_EDITAR) {
            this.setTitle(R.string.usuario_editar_titulo);
            this.setEdicion(true);
        }
    }

}