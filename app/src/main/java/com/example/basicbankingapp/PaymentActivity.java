package com.example.basicbankingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Model> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);



        recyclerView = (RecyclerView) findViewById(R.id.paymentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        showData();
    }

    private void showData() {
        modelList.clear();
        Cursor cursor = new sqlDatabaseHelper(this).readAllData();

        while (cursor.moveToNext()) {
            String balancefromdb = cursor.getString(2);


            Model model = new Model(cursor.getString(0), cursor.getString(1), balancefromdb);
            modelList.add(model);
        }
        PaymentAdapter adapter = new PaymentAdapter(PaymentActivity.this,this, modelList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }
}