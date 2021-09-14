package edbeca.simarro.marketv1.fragments;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import edbeca.simarro.marketv1.R;
import edbeca.simarro.marketv1.activities.PrincipalActivity;


public class PreferenciasFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    Locale localizacion;
    Configuration config;
    MediaPlayer mediaPlayer;
    PreferenciasFragment context = this;

    @Override
    public void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.opciones);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Registrar escucha
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Eliminar registro de la escucha
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("idioma")){
            Preference preference = findPreference(key);
            preference.setSummary(sharedPreferences.getString(key,""));

            if (sharedPreferences.getString(key,"").equalsIgnoreCase("ESP")){
                localizacion = new Locale("es", "ES");
            } else if(sharedPreferences.getString(key,"").equalsIgnoreCase("ENG")){
                localizacion = new Locale("en", "US");
            }
            Locale.setDefault(localizacion);
            config = new Configuration();
            config.locale = localizacion;
            getResources().updateConfiguration(config, null);
        }

        if(key.equals("reproducirMusica")){
            mediaPlayer = MediaPlayer.create(context.getActivity(), R.raw.sound_relax);
            if (sharedPreferences.getBoolean(key,false) == true){
                mediaPlayer.start();
                Log.i("Start", "");
            }else {
                mediaPlayer.stop();
                Log.i("Stop", "");
            }
        }

        if(key.equals("color_fondo_botones")){

            PrincipalActivity.colorFondoBotones(sharedPreferences);
        }
    }
}