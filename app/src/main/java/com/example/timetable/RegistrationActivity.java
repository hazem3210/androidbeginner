package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
 EditText UserName,Password,RePassword;
 Button SingIn,SingUp;
 DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        UserName=findViewById(R.id.username1);
        Password=findViewById(R.id.password1);
        RePassword=findViewById(R.id.rePassword);
        SingIn=findViewById(R.id.btn_signin1);
        SingUp=findViewById(R.id.btn_register);
         DB=new DBHelper(RegistrationActivity.this);

        SingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=UserName.getText().toString().trim();
                String password=Password.getText().toString().trim();
                String repassword=RePassword.getText().toString().trim();

                if(username.equals("")||password.equals("")||repassword.equals(""))
                    Toast.makeText(RegistrationActivity.this, "please fill all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if(password.equals(repassword)){
                       Boolean checkuser= DB.checkUserName(username);
                       if(!checkuser){
                           Boolean insert=DB.inData(username,password);
                           if(insert){
                               Toast.makeText(RegistrationActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                               startActivity(intent);
                           }
                           else {
                               Toast.makeText(RegistrationActivity.this, "Registered failed!", Toast.LENGTH_SHORT).show();
                           }
                       }
                       else {
                           Toast.makeText(RegistrationActivity.this, "this User already exists", Toast.LENGTH_SHORT).show();
                       }
                    }
                    else {
                        Toast.makeText(RegistrationActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });

        SingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}