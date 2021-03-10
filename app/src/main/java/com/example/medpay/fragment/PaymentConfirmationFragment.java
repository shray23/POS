package com.example.medpay.fragment;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.medpay.MainActivity;
import com.example.medpay.MainActivityViewModel;
import com.example.medpay.MainEntity;
import com.example.medpay.MainRepository;
import com.example.medpay.PaymentConfirmationActivity;
import com.example.medpay.R;
import com.example.medpay.RoomDb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentConfirmationFragment extends Fragment {

    private static final String TAG = "PaymentConfirmFragment";

    public MainActivityViewModel mainActivityViewModel;
    EditText et_phone;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.paymentconfirmation_fragment, container, false);

        Button back = view.findViewById(R.id.buttonback);
        final Button confirm = view.findViewById(R.id.buttonconfirm);
        et_phone = view.findViewById(R.id.et_phoneno);

        final String amount = ((MainActivity) getActivity()).getResult();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValidPhone(et_phone.getText().toString())) {

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    MainEntity mainEntity = new MainEntity();
                    mainEntity.setAmount(Integer.parseInt(amount));  //setting amount
                    String time = formatter.format(date);
                    mainEntity.setTime(time);  //setting date
                    Intent confirmIntent = new Intent(getActivity(), PaymentConfirmationActivity.class);
                    confirmIntent.putExtra("amount", amount);
                    confirmIntent.putExtra("phone", et_phone.getText().toString());
                    confirmIntent.putExtra("time", time);
                    startActivity(confirmIntent);


                    mainActivityViewModel.insert(mainEntity);  //inserting the entry

                } else {
                    Toast.makeText(getActivity(), "Please enter a valid number", Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }

    public static boolean isValidPhone(String phone) {
        String expression = "^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{9,14}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
