package com.example.medpay.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medpay.MainEntity;
import com.example.medpay.R;
import com.example.medpay.RoomDb;

import org.w3c.dom.Text;

import java.util.List;


public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<MainEntity> databaseList;
    private RoomDb database;
    private Activity context;


    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView tv_Amount;
        public TextView tv_time;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.transaction_type_image);
            tv_Amount = itemView.findViewById(R.id.tv_transaction_amount);
            tv_time = itemView.findViewById(R.id.tv_transaction_time);

        }
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_rowitem, parent, false);
        TransactionViewHolder transactionViewHolder = new TransactionViewHolder(view);
        return transactionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {

        MainEntity entity = databaseList.get(position);

        database = RoomDb.getInstance(context);

        holder.imageView.setImageResource(R.drawable.cashtransac);
        holder.tv_Amount.setText(String.format("₹%s ", (entity.getAmount())));
        holder.tv_time.setText(entity.getTime());

    }

    @Override
    public int getItemCount() {
        if (databaseList != null) {
            return databaseList.size();
        } else {
            return 0;
        }
    }

    public void setEntities(List<MainEntity> list) {
        this.databaseList = list;
        notifyDataSetChanged();
    }

    public TransactionAdapter(Activity context) {
        this.context = context;

    }
}
