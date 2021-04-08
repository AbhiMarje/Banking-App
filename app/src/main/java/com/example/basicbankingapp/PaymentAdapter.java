package com.example.basicbankingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.gsm.GsmCellLocation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.basicbankingapp.R.layout.usertile;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.viewHolder> {

    ArrayList<Model> dataHolder;
    Activity activity;
    Context context;

    public PaymentAdapter(Activity activity,Context context, ArrayList<Model> dataHolder) {
        this.context = context;
        this.activity = activity;
        this.dataHolder = dataHolder;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(usertile, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.userName.setText(dataHolder.get(position).getName());
        holder.phoneNo.setText(dataHolder.get(position).getPhoneNo());
        holder.balance.setText(dataHolder.get(position).getBalance());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,PayToUser.class);
                Global.RecPhoneNo = dataHolder.get(position).getPhoneNo();
                Global.RecUser = dataHolder.get(position).getName();
                Global.recBalance = Double.parseDouble(dataHolder.get(position).getBalance());
                i.putExtra("recieverName", dataHolder.get(position).getName());
                activity.startActivityForResult(i,1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView userName,phoneNo,balance;
        LinearLayout linearLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.mainlayout);
            userName = (TextView) itemView.findViewById(R.id.username);
            phoneNo = (TextView) itemView.findViewById(R.id.phoneno);
            balance = (TextView) itemView.findViewById(R.id.balance);
        }
    }
}
