package com.example.medpay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medpay.R;


public class GridViewAdapter extends BaseAdapter {

    private Context context;
    public String[] transactionModes;
    public int[] transactionImages;
    public LayoutInflater mInflator;

    public GridViewAdapter(Context context, String[] transactionModes, int[] transactionImages) {
        this.context = context;
        this.transactionModes = transactionModes;
        this.transactionImages = transactionImages;
    }

    @Override
    public int getCount() {
        return transactionModes.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (mInflator == null) {
            mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null) {

            view = mInflator.inflate(R.layout.gridview_rowitem,null);
        }

        ImageView iv_transaction = view.findViewById(R.id.iv_transac_mode);
        TextView tv_transaction = view.findViewById(R.id.tv_transaction_mode);

        iv_transaction.setImageResource(transactionImages[i]);
        tv_transaction.setText(transactionModes[i]);
        return view;
    }
}
