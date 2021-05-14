package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentMainPage extends AppCompatActivity {
    Button attendance,Logout;
    String sName,sDivision,sRoll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mainpage);
        attendance=findViewById(R.id.attendance);
        Logout=findViewById(R.id.Logout);
        sName= getIntent().getStringExtra("Student name");
        sRoll=getIntent().getStringExtra("Student roll");
        sDivision=getIntent().getStringExtra("Division");
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ViewAttendanceList.class);
                i.putExtra("Student name",sName);
                i.putExtra("Division",sDivision);
                i.putExtra("Student roll",sRoll);
                startActivity(i);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Login_Page.class);
                i.putExtra("val",1);
                startActivity(i);
            }
        });
    }
}