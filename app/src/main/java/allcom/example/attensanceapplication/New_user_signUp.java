package allcom.example.attensanceapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;




public class New_user_signUp extends AppCompatActivity {

    public static final String TAG1 = "TAG";
    TextView CreateNew,Category3;
    RadioGroup radiogroup;
    EditText Name3,Email3,PhoneNumber3,Password3,ReEnterPassword3,AcademicYear3,NfcTag3;
    RadioButton AdminButton3,TeacherButton3,StudentButton3;
    Spinner Academicyear;
    Button SubmitButton3;
    String userID;


    private FirebaseAuth mAuth;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseDatabase rootNode;
    DatabaseReference reference;



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
        mAuth = FirebaseAuth.getInstance();

        SubmitButton3.setOnClickListener(view -> {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("users");

            reference.setValue("");
            final String name = Name3.getText().toString();
            final String email = Email3.getText().toString().trim();
            final String phoneNo = PhoneNumber3.getText().toString().trim();
            String password = Password3.getText().toString().trim();
            String repassword = ReEnterPassword3.getText().toString().trim();

            if (TextUtils.isEmpty(name)){
                Name3.setError("Enter Name");
                return;
            }
            if (TextUtils.isEmpty(email)){
                Email3.setError("Enter Email");
                return;
            }
            if ( phoneNo.length() < 10 ){
                PhoneNumber3.setError("Enter Valid Phone Number");
                return;
            }
            if ( phoneNo.length() > 10 ){
                PhoneNumber3.setError("Enter Valid Phone Number");
                return;
            }
            if (TextUtils.isEmpty(password)){
                Password3.setError("Enter Password");
                return;
            }
            if (password.length() < 6){
                Password3.setError("Password must be greater or Equal to 6 characters");
                return;
            }
            if (!password.equals(repassword)){
                ReEnterPassword3.setError("Entered Password Does not Match");
                return;

            }




            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(New_user_signUp.this, "User created.", Toast.LENGTH_LONG).show();
                    userID = mAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("users").document(userID);
                    Map<String,Object> user = new HashMap<>();
                    user.put("Name3",name);
                    user.put("Email3",email);
                    user.put("PhoneNumber3",phoneNo);

                    if(TeacherButton3.isChecked()){

                        user.put("isteacher","1");
                    }
                    if(StudentButton3.isChecked()){

                        user.put("isStudent","2");
                    }
                    if(AdminButton3.isChecked()){

                        user.put("isAdmin","3");
                    }
                    documentReference.set(user).addOnSuccessListener(aVoid -> Log.d(TAG1,"onSuccess: user profile is created for"  + userID));
                    Intent intent =new Intent(New_user_signUp.this,GetOtp.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(New_user_signUp.this, "ERROR!!!", Toast.LENGTH_LONG).show();
                }
            });


        });


    }

    public void checkButton(View v){
        int radio =radiogroup.getCheckedRadioButtonId();
        SubmitButton3=findViewById(radio);

    }


}
