package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UsersCategoty extends AppCompatActivity {
    RadioGroup radiogroup;
    RadioButton AdminButton3,TeacherButton3,StudentButton3;
    Button SubmitButton3;
    private Object v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_categoty);
        radiogroup = findViewById(R.id.radiogroup);
        AdminButton3 = findViewById(R.id.radioButton12);
        TeacherButton3 = findViewById(R.id.radioButton11);
        StudentButton3 = findViewById(R.id.radioButton10);
        SubmitButton3 = findViewById(R.id.button2);

        SubmitButton3.setOnClickListener(view -> {

            if (TeacherButton3.isChecked()) {

                Intent intent = new Intent(UsersCategoty.this, NewUserTeacher.class);
                startActivity(intent);



            }

            if (StudentButton3.isChecked()) {

                Intent intent = new Intent(UsersCategoty.this, New_user_signUp.class);
                startActivity(intent);


            }
            if (AdminButton3.isChecked()) {

                Intent intent = new Intent(UsersCategoty.this, NewUserAdmin.class);
                startActivity(intent);


            }


        });}
    public void checkButton(View v){
        int radio =radiogroup.getCheckedRadioButtonId();
        SubmitButton3=findViewById(radio);

    }

}
