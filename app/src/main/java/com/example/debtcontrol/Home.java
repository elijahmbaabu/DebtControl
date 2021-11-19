//package com.example.debtcontrol;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.media.Image;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//
//public class Home extends AppCompatActivity {
//
//    ImageView edit, add, remove;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//
//        edit = findViewById(R.id.imageView4);
//        add = findViewById(R.id.imageView3);
//        remove = findViewById(R.id.imageView5);
//
//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Home.this, Edit.class));
//            }
//        });
//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Home.this, AddDebtor.class));
//            }
//        });
//
//        remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Home.this, RemoveDebtor.class));
//            }
//        });
//
//    }
//}

package com.example.debtcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
   // Button addDebtors, EditDebtors,logout;
   TextView displaySessionvar, logout;
    SharedPreferences prf; ImageView addDebtors, EditDebtors,img2,img5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addDebtors = findViewById(R.id.imageView3);
        EditDebtors = findViewById(R.id.imageView4);
        img2 = findViewById(R.id.imageView);
        img5 = findViewById(R.id.imageView5);

        logout = findViewById(R.id.logout);
        displaySessionvar = findViewById(R.id.DisplaySession);
//
//        SessionManagement sessionManagement = new SessionManagement(Home.this);
//        String sellerPhone = sessionManagement.getSession();
        prf = getSharedPreferences("user_details",MODE_PRIVATE);

        displaySessionvar.setText(" Hi Your accId is: "+prf.getString("phoneNumber",null));

        addDebtors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, AddDebtor.class);
                startActivity(intent);
            }

        });

        EditDebtors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Records.class);
                startActivity(intent);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Records.class);
                startActivity(intent);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Records.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();
                //Direct To Main Activity

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Log out success", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
