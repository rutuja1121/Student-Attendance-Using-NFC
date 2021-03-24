package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.security.PrivateKey;

public class Teacher_list extends AppCompatActivity {
private Button submit,logout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);
        submit=findViewById(R.id.submit);
        logout1=findViewById(R.id.log);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent child0Intent = new Intent(Teacher_list.this, Day_date_student.class);
                startActivity(child0Intent);
                finish();
            }
        });
        logout1.setOnClickListener(view -> {

            Intent i=new Intent(Teacher_list.this,Login_Page.class);
            i.putExtra("val",1);
            startActivity(i);
            finish();
        });


    }

}