package com.example.debtcontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Records extends AppCompatActivity {
    ImageView Main;
    DatabaseHelper DB;
    SharedPreferences prf;
    ArrayList<ColorSpace.Model> dataHolder;
    RecyclerView recyclerView;

    ArrayList<String> name,list,total,myId;
    //CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        Main = findViewById(R.id.main);
        recyclerView = findViewById(R.id.RecycleView);
        Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Records.this, Home.class);
                startActivity(intent);
            }
        });
        //get the object of the Database helper
        DB = new DatabaseHelper(this);

        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        String phoneNumber = prf.getString("phoneNumber",null);

        Cursor checkData = DB.getdata(phoneNumber);

        if(checkData.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No data Yet in database", Toast.LENGTH_SHORT).show();
        }else {
            while (checkData.moveToNext()) {
                String id = checkData.getString(0);
                String Name = checkData.getString(2);
                String List = checkData.getString(3);
                String Total = checkData.getString(4);

//                name = new ArrayList<>();
//                list = new ArrayList<>();
//                total = new ArrayList<>();
//                myId = new ArrayList<>();
//
//                name.add(Name);
//                list.add(List);
//                myId.add(id);
//                total.add(Total);


//                customAdapter = new CustomAdapter(editDebtors.this,name,list,total,myId);
//
//                recyclerView.setAdapter(customAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(editDebtors.this));
//
//

                Model obj = new Model(id, Name, List, Total);

                ArrayList<Model> dataHolder = new ArrayList<>();
                dataHolder.add(obj);
                LinearLayoutManager llm = new LinearLayoutManager(this);
                llm.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(llm);
                myAdapter adapter = new myAdapter(dataHolder);
                recyclerView.setAdapter(adapter);
            }

        }


    }
}