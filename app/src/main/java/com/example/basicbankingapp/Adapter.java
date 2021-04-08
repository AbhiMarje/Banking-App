package com.example.basicbankingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.basicbankingapp.R.layout.usertile;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder>{

    ArrayList<Model> dataHolder;
    Activity activity;
    Context context;

    public Adapter(Activity activity, Context context, ArrayList<Model> dataHolder) {
        this.context = context;
        this.activity = activity;
        this.dataHolder = dataHolder;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(usertile, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.userName.setText(dataHolder.get(position).getName());
        holder.phoneNo.setText(dataHolder.get(position).getPhoneNo());
        holder.balance.setText(dataHolder.get(position).getBalance());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, UserProfile.class);
                Global.phoneNo = dataHolder.get(position).getPhoneNo();
                Global.user = dataHolder.get(position).getName();
                activity.startActivityForResult(i,1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        TextView userName,phoneNo,balance;
        LinearLayout linearLayout;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.mainlayout);
            userName = (TextView) itemView.findViewById(R.id.username);
            phoneNo = (TextView) itemView.findViewById(R.id.phoneno);
            balance = (TextView) itemView.findViewById(R.id.balance);
        }
    }
}
