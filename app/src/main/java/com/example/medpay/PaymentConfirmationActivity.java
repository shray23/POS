package com.example.medpay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PaymentConfirmationActivity extends AppCompatActivity {

    TextView phone, amount, time;
    Button ereceipt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_confirmation);

        time= findViewById(R.id.tv_time);
        amount = findViewById(R.id.tv_amount);
        phone = findViewById(R.id.tv_number);
        ereceipt = findViewById(R.id.buttonrecepit);

        if (getIntent().getExtras() != null){
            String timeextra = getIntent().getStringExtra("time");
            String phoneextra = getIntent().getStringExtra("phone");
            String amountextra =  getIntent().getStringExtra("amount");

            time.setText(timeextra);
            if (phoneextra != null) {
                phone.setText(phoneextra);
            }
            amount.setText(String.format("₹%s", amountextra));
        }

        ereceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentConfirmationActivity.this, "Receipt has been sent on your registered mail", Toast.LENGTH_LONG).show();
            }
        });




        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setTitle("Payment Status");
        myToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));


        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PaymentConfirmationActivity.this, MainActivity.class));
        finish();
    }
}
