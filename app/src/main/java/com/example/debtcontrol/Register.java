package com.example.debtcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText phoneNumber, securityKey, password, passwordConfirm,securityKeyConfirm;
    Button registerBtn; TextView backToLoginBtn;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //getting elements(binding)
        registerBtn = findViewById(R.id.btn_register);
        backToLoginBtn = findViewById(R.id.sign_redirect);
        phoneNumber = findViewById(R.id.phone_number);
        securityKey = findViewById(R.id.security_key);
        password = findViewById(R.id.password_register);
        passwordConfirm = findViewById(R.id.password_confirm);
        securityKeyConfirm = findViewById(R.id.confirm_key);
        DB = new DbHelper(this);

        //direct to login page
        backToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //Add event Listerner to the Register Btn
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Register code
                String pass = password.getText().toString();
                String security = securityKey.getText().toString();
                String securityConfirm = securityKeyConfirm.getText().toString();
                String passConfirm = passwordConfirm.getText().toString();
                String phone = phoneNumber.getText().toString();

                if(pass == null && security == null && securityConfirm == null && passConfirm == null && phone == null) {
                    Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
                } else if(!pass.equals(passConfirm)){
                    Toast.makeText(getApplicationContext(), "Password and its confirmation must match", Toast.LENGTH_SHORT).show();
                }else if(!security.equals(securityConfirm)) {
                    Toast.makeText(getApplicationContext(), "Security Key and its confirmation must match", Toast.LENGTH_SHORT).show();
                }else {
                    //perform code to insert data to database
                    Boolean checkInsertUserData = DB.insertUserData(phone,security,pass);
                    if(checkInsertUserData == true){
                        Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
                        //direct to login activity

                        securityKeyConfirm.setText("");
                        securityKey.setText("");
                        phoneNumber.setText("");
                        password.setText("");
                        passwordConfirm.setText("");

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }
}