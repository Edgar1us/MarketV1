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
import android.widget.Toast;

import java.util.ArrayList;

import edbeca.simarro.marketv1.BD.MiTiendaOperacional;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Usuario;

import static edbeca.simarro.marketv1.activities.PrincipalActivity.botones;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsuario, edtPassword;
    private Button btnLogin;
    private MiTiendaOperacional mto;

    @RequiresApi(api = Build.VERSION_CODES.O)
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

        edtUsuario.setText("oscar.gmail.com");
        edtPassword.setText("1234");

        btnLogin.setOnClickListener(this);

        botones = new ArrayList<>();
        botones.add(btnLogin = findViewById(R.id.btnLogin));

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        colorFondoBotones(pref);
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