package com.akash.tamashaproject;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeDetails extends AppCompatActivity {
ImageView profilepicture;
TextView name;
TextView identityNum;
TextView salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        profilepicture=findViewById(R.id.profilepic);
        name=findViewById(R.id.name);
        identityNum=findViewById(R.id.identityNum);
        salary=findViewById(R.id.salary);

        Intent intent = getIntent();
        String name1 = intent.getStringExtra("EXTRA_MESSAGE1");
        String identityNum1 = intent.getStringExtra("EXTRA_MESSAGE2");
        String salary1 = intent.getStringExtra("EXTRA_MESSAGE3");
        Integer image1 = intent.getIntExtra("EXTRA_MESSAGE4",0);

        name.setText("Name : "+name1);
        identityNum.setText("Id : "+identityNum1);
        salary.setText("Salary : "+salary1);
        profilepicture.setImageResource(image1);


    }
}