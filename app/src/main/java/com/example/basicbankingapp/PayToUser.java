package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PayToUser extends AppCompatActivity {

    EditText payerName,recieverName,amount;
    Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_to_user);

        payerName = (EditText) findViewById(R.id.editTextTextPersonName);
        recieverName = (EditText) findViewById(R.id.editTextTextPersonName2);
        amount = (EditText) findViewById(R.id.editTextNumber);
        payButton = (Button) findViewById(R.id.payButton);



        String recievername = null;
        Bundle mbundle = getIntent().getExtras();
        if (mbundle != null) {
            recievername = mbundle.getString("recieverName");
        }

        payerName.setText(Global.user);
        recieverName.setText(recievername);


        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(amount.getText().toString().equals("")){
                    Toast.makeText(PayToUser.this, "Please Enter Some Amount", Toast.LENGTH_SHORT).show();
                }else {
                    Double dAmout = Double.parseDouble(amount.getText().toString());
                    if (dAmout > Global.balance){
                        Toast.makeText(PayToUser.this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                    }else {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                        String currentDateandTime = sdf.format(new Date());

                        String db = new sqlDatabaseHelper(PayToUser.this)
                                .transferData(currentDateandTime,payerName.getText().toString(),
                                        recieverName.getText().toString(),amount.getText().toString(),
                                        "Success");

                        Global.currentAmount = Global.balance - dAmout;
                        Global.remainingAmount = Global.recBalance + dAmout;



                        Cursor cursor = new sqlDatabaseHelper(PayToUser.this).updateAmount(Global.phoneNo,Double.toString(Global.currentAmount));
                        Cursor mCursor = new sqlDatabaseHelper(PayToUser.this).updateAmount(Global.RecPhoneNo,Double.toString(Global.remainingAmount));

                        Global.remainingAmount = 00.00;
                        Global.currentAmount = 00.00;
                        Global.balance = 00.00;
                        Global.recBalance = 00.00;

                        Intent i = new Intent(PayToUser.this, UserList.class);
                        startActivity(i);

                        Toast.makeText(PayToUser.this, "Transaction Successful", Toast.LENGTH_SHORT).show();



                    }
                }
            }
        });
    }
}