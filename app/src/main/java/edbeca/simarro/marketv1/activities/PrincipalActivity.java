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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edbeca.simarro.marketv1.BD.Constantes;
import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.pojo.Usuario;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtBienvenida;
    private Usuario usuario;
    private ImageButton btnVender, btnComprar;

    public static ArrayList<Button> botones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        usuario = (Usuario)getIntent().getSerializableExtra("Usuario");

        txtBienvenida = (TextView)findViewById(R.id.txtBienvenida);
        txtBienvenida.setText(usuario.getNombre());

        btnVender = (ImageButton)findViewById(R.id.btnVender);
        btnComprar = (ImageButton)findViewById(R.id.btnComprar);
        btnVender.setOnClickListener(this);
        btnComprar.setOnClickListener(this);

        botones = new ArrayList<>();
        /*botones.add(btnVender = findViewById(R.id.btnVender));

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        colorFondoBotones(pref);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!usuario.getNombre().equals(""))
            getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent();
        switch (item.getItemId()) {
            case R.id.menu_usuario:
                i.setClass(PrincipalActivity.this, UsuarioActivity.class);
                i.putExtra(Constantes.C_MODO, Constantes.C_VISUALIZAR);
                i.putExtra(Constantes.FIELD_USUARIO_ID, usuario.getIdUsuario());
                startActivityForResult(i, Constantes.C_VISUALIZAR);
                break;
            case R.id.action_cambiarClave:
                i.setClass(PrincipalActivity.this, CambiarClaveActivity.class);
                break;
            case R.id.action_configuracion:
                i.setClass(PrincipalActivity.this, PreferenceActivity.class);
                break;
            case R.id.action_mapa:
                //i.setClass(PrincipalActivity.this, MapaActivity.class);
                break;

        }
        if (item.getItemId() != R.id.menu_usuario){
            i.putExtra("Usuario", usuario);
            startActivity(i);
        }

        return true;
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