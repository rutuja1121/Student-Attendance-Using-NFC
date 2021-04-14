package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherMainPage extends AppCompatActivity {
    Button b1,b2,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main_page);
        b1=(Button)findViewById(R.id.attendance);
        b2=(Button)findViewById(R.id.List);
        logout=findViewById(R.id.View1);
        String teachername=getIntent().getStringExtra("Teacher name");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Select_year_teacher.class);
                i.putExtra("Teacher name",teachername);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),ViewAttendanceList.class);
                i.putExtra("Teacher name",teachername);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Login_Page.class);
                i.putExtra("val",1);
                startActivity(i);
                finish();
            }
        });
    }
}