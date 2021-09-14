package edbeca.simarro.marketv1.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import edbeca.simarro.marketv1.DAO.UsuarioDAO;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Usuario;

import static edbeca.simarro.marketv1.activities.PrincipalActivity.botones;

public class CambiarClaveActivity extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuario;
    private EditText edtVieja, edtNueva;
    private Button btnGuardar;

    @RequiresApi(api = Build.VERSION_CODES.O)
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

        botones = new ArrayList<>();
        botones.add(btnGuardar = findViewById(R.id.btnCambiarClave));

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        colorFondoBotones(pref);

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

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void colorFondoBotones(SharedPreferences pref){
        if (!pref.getString("color_fondo_botones", "").isEmpty()){
            for (Button boton : botones){
                boton.setBackgroundColor(Color.parseColor(pref.getString("color_fondo_botones", "")));
            }
        }else
            for (Button boton : botones){
                boton.setScrollBarStyle(R.style.BotonesNormal);
            }
    }


}