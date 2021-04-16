package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Select_year_teacher extends AppCompatActivity {
    TextView Teachers7,SelectTeachers7,teachename;
    RadioGroup radioGroup2 ;
    RadioButton FeButton7,SeButton7,TeButton7,BeButton7;
    Button SubmitButton7;
    RadioButton FindButton;
    int radioid;
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_year_teacher);
        Teachers7 = findViewById(R.id.Teachers7 );
        SelectTeachers7= findViewById(R.id.SelectTeachers7);

        FeButton7 = findViewById(R.id.FeButton7);
        SeButton7 = findViewById(R.id.SeButton7);
        TeButton7 = findViewById(R.id.TeButton7);
        BeButton7= findViewById(R.id.BeButton7);
        SubmitButton7= findViewById(R.id.attendance);
        teachename=findViewById(R.id.Tname);
        radioGroup2=findViewById(R.id.radiogroup);

       // String teachename=getIntent().getStringExtra("Teacher name");
       // Toast.makeText(this, ""+teachename, Toast.LENGTH_SHORT).show();
        SubmitButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioid=radioGroup2.getCheckedRadioButtonId();
                FindButton=findViewById(radioid);
                String Year=FindButton.getText().toString();
                Intent intent =new Intent(Select_year_teacher.this,Select_Division.class);
                intent.putExtra("Teacher name",teachename.getText().toString());
                intent.putExtra("Teacher Year",Year);
                startActivity(intent);
            }
        });

    }
}