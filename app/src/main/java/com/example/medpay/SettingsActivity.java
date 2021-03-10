package com.example.medpay;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    TextView tv_buildno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        tv_buildno = findViewById(R.id.tv_settings);

        tv_buildno.setText(String.format(getString(R.string.version), BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.nav_settings);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_support:
                        //todo: tbd
                        Toast.makeText(SettingsActivity.this, "Coming Soon", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.nav_report:
                        //todo: tbd
                        Toast.makeText(SettingsActivity.this, "Coming Soon", Toast.LENGTH_LONG).show();
                        return true;

                    case R.id.nav_home:

                        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                        finish();


                }
                return false;
            }
        });


    }
}
