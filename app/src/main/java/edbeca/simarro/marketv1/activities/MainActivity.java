package edbeca.simarro.marketv1.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Locale;

import edbeca.simarro.marketv1.R;

import static edbeca.simarro.marketv1.activities.PrincipalActivity.botones;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAcceder, btnRegistrame;
    SharedPreferences prefsManager;
    Locale localizacion;
    MediaPlayer mediaPlayer;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefsManager = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        if (!prefsManager.getString("idioma", "").isEmpty()){
            String idioma = prefsManager.getString("idioma", "");

            if (idioma.equalsIgnoreCase("ESP"))
                localizacion = new Locale("es", "ES");
            else if (idioma.equalsIgnoreCase("ENG"))
                localizacion = new Locale("en", "US");

            Locale.setDefault(localizacion);
            Configuration config = new Configuration();
            config.locale = localizacion;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        }
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if (pref.getBoolean("reproducirMusica", false)){
            mediaPlayer = MediaPlayer.create(this, R.raw.sound_relax);
            mediaPlayer.start();
        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        btnAcceder = (Button)findViewById(R.id.btnAcceder);
        btnRegistrame = (Button)findViewById(R.id.btnRegistrame);

        btnAcceder.setOnClickListener(this);
        btnRegistrame.setOnClickListener(this);

        botones = new ArrayList<>();
        botones.add(btnAcceder = findViewById(R.id.btnAcceder));
        botones.add(btnRegistrame = findViewById(R.id.btnRegistrame));

        prefsManager = PreferenceManager.getDefaultSharedPreferences(this);
        colorFondoBotones(prefsManager);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.btnAcceder:
                i = new Intent(this, LoginActivity.class);
                startActivity(i);
                break;
            case R.id.btnRegistrame:
                i = new Intent(this, CrearCuentaActivity.class);
                startActivity(i);
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