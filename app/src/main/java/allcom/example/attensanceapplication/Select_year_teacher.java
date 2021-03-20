package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Select_year_teacher extends AppCompatActivity {
    TextView Teachers7,SelectTeachers7;
    RadioGroup radioGroup2 ;
    RadioButton FeButton7,SeButton7,TeButton7,BeButton7;
    Button SubmitButton7;
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
        SubmitButton7= findViewById(R.id.SubmitButton7);
        radioGroup2=findViewById(R.id.radiogroup);
        SubmitButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Select_year_teacher.this,Select_new_branch.class);
                startActivity(intent);
            }
        });

    }
}