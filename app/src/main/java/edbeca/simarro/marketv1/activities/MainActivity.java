package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edbeca.simarro.marketv1.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAcceder, btnRegistrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        btnAcceder = (Button)findViewById(R.id.btnAcceder);
        btnRegistrame = (Button)findViewById(R.id.btnRegistrame);

        btnAcceder.setOnClickListener(this);
        btnRegistrame.setOnClickListener(this);

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
}