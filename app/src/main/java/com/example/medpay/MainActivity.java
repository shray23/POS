package com.example.medpay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medpay.adapter.GridViewAdapter;
import com.example.medpay.adapter.TransactionAdapter;
import com.example.medpay.fragment.PaymentConfirmationFragment;
import com.example.medpay.fragment.TransactionModeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public List<MainEntity> datalist = new ArrayList<>();
    RoomDb database;
    RecyclerView allTransactionRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    TransactionAdapter transactionAdapter;
    EditText et_amount;
    int todayTotal = 0;
    int yesterdayTotal = 0;
    TextView todaysValue, yesterdaysValue;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainActivityViewModel mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        initializeIcons();

        et_amount.setText(mainActivityViewModel.amount);

        storeValues(mainActivityViewModel);  //set values of todays and yesterdays amount

        database = RoomDb.getInstance(this);  //initializing database

        mainActivityViewModel.getAllEntities().observe(this, new Observer<List<MainEntity>>() {
            @Override
            public void onChanged(List<MainEntity> mainEntities) {
                transactionAdapter.setEntities(mainEntities);   //setting the list whenever it changes
                for (MainEntity mainEntity : mainEntities) {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy ");
                    Date date = new Date();
                    Log.d(TAG, "onChanged: today's date" + formatter.format(date));

                    if (mainEntity.getTime().contains(formatter.format(date))) {
                        todayTotal = todayTotal + mainEntity.getAmount();
                    } else {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.DATE, -1);
                        dateFormat.format(cal.getTime()); //your formatted date here

                        if (mainEntity.getTime().contains(dateFormat.format(cal.getTime()))) {
                            yesterdayTotal = yesterdayTotal + mainEntity.getAmount();
                        }
                    }
                }
                mainActivityViewModel.yesterdaysValue = "\u20B9 " + (yesterdayTotal);
                mainActivityViewModel.todaysValue = "\u20B9" + (todayTotal);

                storeValues(mainActivityViewModel);

            }


        });
        //   datalist = database.mainDao().getAll();  //storing all values of db in list

        allTransactionRecyclerView.setHasFixedSize(true);
        transactionAdapter = new TransactionAdapter(MainActivity.this);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        allTransactionRecyclerView.setLayoutManager(mLayoutManager);

        allTransactionRecyclerView.setAdapter(transactionAdapter);

        setFragment(new TransactionModeFragment());  //setting the fragment


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_support:
                        //todo: tbd
                        Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.nav_report:
                        //todo: tbd
                        Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_LONG).show();
                        return true;

                    case R.id.nav_settings:
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        return true;
                }
                return false;
            }
        });
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout_main, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void storeValues(MainActivityViewModel mainActivityViewModel) {
        todaysValue.setText(mainActivityViewModel.todaysValue);
        yesterdaysValue.setText(mainActivityViewModel.yesterdaysValue);
    }

    public String getResult() {
        return et_amount.getText().toString();
    }

    public void initializeIcons() {
        allTransactionRecyclerView = findViewById(R.id.transactions_recyclerview);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        et_amount = findViewById(R.id.et_amount);
        todaysValue = findViewById(R.id.tv_todays_value);
        yesterdaysValue = findViewById(R.id.tv_yesterdays_value);

    }

}