package com.example.medpay.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.medpay.MainActivity;
import com.example.medpay.R;
import com.example.medpay.adapter.GridViewAdapter;


public class TransactionModeFragment extends Fragment {

    String[] transactionModes = {"Card Sale", "QR Sale", "Cash Sale", "Insurance/Membership"};
    int[] transactionModesImages = {R.drawable.cardtransaction, R.drawable.qrcode_transaction, R.drawable.cashtransac, R.drawable.insurance};

    GridView transactionGridView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transactionmode_fragment, container, false);

        transactionGridView = view.findViewById(R.id.gridview_saleoptions);

        transactionGridView.setAdapter(new GridViewAdapter(getActivity(), transactionModes, transactionModesImages));

        transactionGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 2) {
                    if (((MainActivity) getActivity()).getResult() != null && !((MainActivity) getActivity()).getResult().equals("")) {
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.framelayout_main, new PaymentConfirmationFragment());   //sending to payment confirmation fragment
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else {
                        Toast.makeText(getActivity(), "Please enter the amount", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Coming Soon", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;


    }
}
