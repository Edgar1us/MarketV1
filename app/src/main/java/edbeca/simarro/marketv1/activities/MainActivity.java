package edbeca.simarro.marketv1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edbeca.simarro.marketv1.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAcceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAcceder = (Button)findViewById(R.id.btnAcceder);

        btnAcceder.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}