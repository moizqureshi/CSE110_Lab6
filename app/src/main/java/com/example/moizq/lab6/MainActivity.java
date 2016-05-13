package com.example.moizq.lab6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userTxt;
    Button display;
    Button otherBtn;
    TextView userAddr;
    TextView userPhone;
    TextView otherTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, StartedService.class);
        startService(intent);


        SharedPreferences prefs = getSharedPreferences("moizqureshi", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("address", "2015 Morgan Lane, Redondo Beach, CA 90278");
        editor.putString("phone", "310-347-2047");
        editor.apply();

        display = (Button) findViewById(R.id.displayBtn);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display();
            }
        });

        otherBtn = (Button) findViewById(R.id.otherBtn);
        otherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otherTxt = (TextView) findViewById(R.id.otherTxt);
                SharedPreferences prefs = getSharedPreferences("test", MODE_PRIVATE);
                String name = prefs.getString("name", "");
                otherTxt.setText(name);
            }
        });


    }

    public void display(){
        userTxt = (EditText) findViewById(R.id.usernameTxt);
        userAddr = (TextView) findViewById(R.id.userAddress);
        userPhone = (TextView) findViewById(R.id.userPhone);

        SharedPreferences prefs = getSharedPreferences(userTxt.getText().toString(), MODE_PRIVATE);
        if(!prefs.contains("address")){
            Toast.makeText(MainActivity.this, "User does not exist!", Toast.LENGTH_LONG).show();
        } else {
            String address = prefs.getString("address", "");
            String phone = prefs.getString("phone","");
            userAddr.setText(address);
            userPhone.setText(phone);
        }
    }
}
