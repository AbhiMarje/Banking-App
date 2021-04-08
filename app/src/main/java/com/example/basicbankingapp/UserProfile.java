package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

public class UserProfile extends AppCompatActivity {

    ImageView profile;
    TextView name,email,phoneNo,balance,accountNo,ifscCode;
    Button pay;
    Double mBalance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profile = (ImageView) findViewById(R.id.profile);
        name = (TextView) findViewById(R.id.name2);
        email = (TextView) findViewById(R.id.email2);
        phoneNo = (TextView) findViewById(R.id.number2);
        balance = (TextView) findViewById(R.id.amount2);
        accountNo = (TextView) findViewById(R.id.acount2);
        ifscCode = (TextView) findViewById(R.id.ifsc2);
        pay = (Button) findViewById(R.id.profilebtn);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(UserProfile.this, PaymentActivity.class);
                startActivity(i);
            }
        });


        showData(Global.phoneNo);
    }

    private void showData(String phoneNummber) {
        Cursor cursor = new sqlDatabaseHelper(this).readparticulardata(phoneNummber);
        while(cursor.moveToNext()) {
            String balancefromdb = cursor.getString(2);
            mBalance = Double.parseDouble(balancefromdb);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(mBalance);


            phoneNo.setText(cursor.getString(0));
            name.setText(cursor.getString(1));
            email.setText(cursor.getString(3));
            balance.setText(price);
            accountNo.setText(cursor.getString(4));
            ifscCode.setText(cursor.getString(5));

            Global.balance = mBalance;
        }

    }
}