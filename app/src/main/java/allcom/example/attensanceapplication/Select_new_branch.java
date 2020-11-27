package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Select_new_branch extends AppCompatActivity {
RadioGroup radioGroup;
RadioButton radioButton5,radioButton4,radioButton3,radioButton,radioButton2,radioButton6;
TextView textView2;
Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_new_branch);
        radioGroup=findViewById(R.id.radioGroup);
        radioButton2=findViewById(R.id.radioButton2);
        radioButton3=findViewById(R.id.radioButton3);
       radioButton4=findViewById(R.id.radioButton4);
        radioButton=findViewById(R.id.radioButton);
        radioButton6=findViewById(R.id.radioButton6);
        textView2=findViewById(R.id.textView2);
        radioButton5=findViewById(R.id.radioButton5);
       button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Select_new_branch.this,Select_Division.class);
                startActivity(intent);
            }
        });
    }
}