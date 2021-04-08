package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.text.NumberFormat;
import java.util.ArrayList;

public class TransactionHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Model> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        recyclerView = (RecyclerView) findViewById(R.id.transactionRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        showData();
    }

    private void showData() {

        Cursor cursor = new sqlDatabaseHelper(this).readTransferData();

        while (cursor.moveToNext()) {
           String date = cursor.getString(0);
           String payer = cursor.getString(1);
           String receiver = cursor.getString(2);
           String amount = cursor.getString(3);
           String status = cursor.getString(4);

            Model model = new Model(date,payer,receiver,amount,status);
            modelList.add(model);
        }
        TransactionAdapter adapter = new TransactionAdapter(this, modelList);
        recyclerView.setAdapter(adapter);
    }
}