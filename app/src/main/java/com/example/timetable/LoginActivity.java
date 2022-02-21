package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText UserName,Password;
    Button SingIn;
    TextView SingUp;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserName=findViewById(R.id.username2);
        Password=findViewById(R.id.password2);
        SingIn=findViewById(R.id.btn_signin2);
        SingUp=findViewById(R.id.singup2);
        DB=new DBHelper(this);

        SingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=UserName.getText().toString().trim();
                String pass=Password.getText().toString().trim();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(LoginActivity.this, "please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass=DB.checkUserNamePassword(user,pass);
                    if (checkuserpass){
                        Toast.makeText(LoginActivity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "The username or password is not correct/found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        SingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(intent);

            }
        });
    }
}