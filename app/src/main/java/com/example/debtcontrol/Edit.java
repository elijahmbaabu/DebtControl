package com.example.debtcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit extends AppCompatActivity {
    TextView displayList, displayTotal,displayName;
    String name;
    EditText list,DebtorName;
    EditText total;DatabaseHelper DB;
    Button edit; TextView ToMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        displayName = findViewById(R.id.displayName);
        displayList = findViewById(R.id.displayList);
        displayTotal = findViewById(R.id.displayTotal);
        DebtorName = findViewById(R.id.DebtorName);

        list = findViewById(R.id.mylist);
        total = findViewById(R.id.mytotal);
        edit = findViewById(R.id.edit);
        ToMain = findViewById(R.id.toMain);



        DB = new DatabaseHelper(this);

        //Code to display Record by Id and match to front End
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");

        Cursor cursor = DB.CheckData(id);
        if(cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                String name = cursor.getString(2);
                String list = cursor.getString(3);
                String total = cursor.getString(4);
                //Map data to textViews
                displayName.setText(name);
                displayList.setText(list);
                displayTotal.setText(total);

            }
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get phonenumber from session
                SharedPreferences prf = getSharedPreferences("user_details",MODE_PRIVATE);
                String phone = prf.getString("phoneNumber",null);
                //Get Input values
                String Total = total.getText().toString();
                String List = list.getText().toString();
                String NName = DebtorName.getText().toString();

                //Logic to Edit
                if(Total.equals(null) || List.equals(null)) {
                    Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean updateUserData = DB.updateUserData(id,phone,NName,List,Total);
                    if(updateUserData == true) {
                        Toast.makeText(getApplicationContext(), "Edit Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Records.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Edit Failed", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        //direct user To Main
        ToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

    }
}