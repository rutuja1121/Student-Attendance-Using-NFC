package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
    RadioGroup maingrp;
    RadioButton FindButton;
    Button Submit;
    int radioid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        maingrp=findViewById(R.id.maingrp);
        Submit=findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioid=maingrp.getCheckedRadioButtonId();
                FindButton=findViewById(radioid);
                // Toast.makeText(Admin.this, FindButton.getText(), Toast.LENGTH_SHORT).show();
                if(FindButton.getText().equals("Add Teacher")){
                    Intent intent=new Intent(Admin.this,Teacher_Details.class);//Select_year_teacher
                    startActivity(intent);
                }
                else if(FindButton.getText().equals("Add class")){
                    Intent intent=new Intent(Admin.this,Add_Class.class);//Select_year_teacher
                    startActivity(intent);
                }
                else if(FindButton.getText().equals("Add Student")){
                    Intent intent=new Intent(Admin.this,Student_details.class);//Select_year_teacher
                    startActivity(intent);
                }
                else if(FindButton.getText().equals("View/Edit Details")){
                    Intent intent=new Intent(Admin.this,Update_Page.class);//Select_year_teacher
                    startActivity(intent);
                }
            }
        });
    }
}