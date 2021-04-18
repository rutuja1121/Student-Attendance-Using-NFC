package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Scan extends AppCompatActivity {
    Button sunmit;
    String student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        String str=getIntent().getStringExtra("teacher id");
        student=getIntent().getStringExtra("student name");
       // Toast.makeText(this, ""+student, Toast.LENGTH_SHORT).show();
        sunmit=findViewById(R.id.log);
        sunmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Scan.this,Marked.class);
                startActivity(i);
                DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Attendence").child(str).child("attendee list");
                String id=reference.push().getKey();
                Classes classes=new Classes(student);
                reference.child(id).setValue(classes);
                Toast.makeText(Scan.this, "Attendance Taken", Toast.LENGTH_SHORT).show();
            }
        });
    }
}