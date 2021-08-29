package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import edbeca.simarro.marketv1.R;

public class NuevoActivity extends AppCompatActivity {

    private EditText edtNombre, edtPrecio, edtDescrip, edtEstado, edtTiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        edtNombre = (EditText)findViewById(R.id.edtNombre);

    }
}