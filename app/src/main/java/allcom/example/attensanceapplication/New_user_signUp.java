package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


public class New_user_signUp extends AppCompatActivity {

    TextView CreateNew,Category3;
    RadioGroup radiogroup;
    EditText Name3,Email3,PhoneNumber3,Password3,ReEnterPassword3,AcademicYear3,NfcTag3;
    RadioButton AdminButton3,TeacherButton3,StudentButton3;
    Spinner Academicyear;
    Button SubmitButton3;

    @SuppressWarnings("unchecked")




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_sign_up);
        CreateNew=findViewById(R.id.CreateNew);
        Category3=findViewById(R.id.Category3);
        Name3=findViewById(R.id. Name3);
        radiogroup=findViewById(R.id.radiogroup);
        Email3=findViewById(R.id.Email3);
        PhoneNumber3=findViewById(R.id.PhoneNumber3);
        Password3=findViewById(R.id.Password3);
        ReEnterPassword3=findViewById(R.id.ReEnterPassword3);
        AcademicYear3=findViewById(R.id.AcademicYear3);
        NfcTag3=findViewById(R.id.NfcTag3);
        AdminButton3=findViewById(R.id.AdminButton3);
        TeacherButton3=findViewById(R.id.TeacherButton3);
        StudentButton3=findViewById(R.id.StudentButton3);
        SubmitButton3=findViewById(R.id.SubmitButton3);

        Academicyear=findViewById(R.id.Academicyear);
        String[] teacher=getResources().getStringArray(R.array.academcicYear);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item, teacher);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Academicyear.setAdapter(adapter);

        SubmitButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(New_user_signUp.this,Forgot_Password.class);
                startActivity(intent);
            }
        });


    }

    public void checkButton(View v){
        int radio =radiogroup.getCheckedRadioButtonId();
        SubmitButton3=findViewById(radio);

    }


}