package com.example.basicbankingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.basicbankingapp.R.layout.transactiontile;
import static com.example.basicbankingapp.R.layout.usertile;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.viewHolder> {

    ArrayList<Model> dataHolder;
    Context context;

    public TransactionAdapter(Context context, ArrayList<Model> dataHolder) {
        this.context = context;
        this.dataHolder = dataHolder;

    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(transactiontile, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.viewHolder holder, int position) {
        holder.name1.setText(dataHolder.get(position).getToName());
        holder.date.setText(dataHolder.get(position).getDate());
        holder.name2.setText(dataHolder.get(position).getFromName());
        holder.status.setText(dataHolder.get(position).getTrans_status());
        holder.balance.setText(dataHolder.get(position).getBalance());
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView name1,name2,date,status,balance;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name1 = (TextView) itemView.findViewById(R.id.name1);
            date = (TextView) itemView.findViewById(R.id.name2);
            name2 = (TextView) itemView.findViewById(R.id.date);
            balance = (TextView) itemView.findViewById(R.id.transaction_status);
            status = (TextView) itemView.findViewById(R.id.balance);
        }
    }
}
