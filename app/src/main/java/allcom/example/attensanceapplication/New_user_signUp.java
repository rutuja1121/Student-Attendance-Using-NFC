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
    EditText Name3,Email3,PhoneNumber3,Password3,ReEnterPassword3,AcademicYear3,Rollno,Divison;
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
        Name3=findViewById(R.id. Name3);
        radiogroup=findViewById(R.id.radiogroup);
        Email3=findViewById(R.id.Email3);
        PhoneNumber3=findViewById(R.id.PhoneNumber3);
        Password3=findViewById(R.id.Password3);
        ReEnterPassword3=findViewById(R.id.ReEnterPassword3);

        Rollno=findViewById(R.id.Rollno3);
        Divison=findViewById(R.id.Division);
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
            String Div=Divison.getText().toString().trim();
            String RollNo= Rollno.getText().toString().trim();
            String password = Password3.getText().toString().trim();
            String repassword = ReEnterPassword3.getText().toString().trim();

            if (TextUtils.isEmpty(name)){
                Name3.setError("Enter Name");
                return;
            }
                else  if (TextUtils.isEmpty(email)){
                Email3.setError("Enter Email");
                return;
            }
            else if ( phoneNo.length() < 10 ){
                PhoneNumber3.setError("Enter Valid Phone Number");
                return;
            }
            else if ( phoneNo.length() > 10 ){
                PhoneNumber3.setError("Enter Valid Phone Number");
                return;
            }
            else if (TextUtils.isEmpty(password)){
                Password3.setError("Enter Password");
                return;
            }
            else  if(TextUtils.isEmpty(Div)) {
                Divison.setError("EnterDivison");
            }
            else if(TextUtils.isEmpty(RollNo)){
                    Rollno.setError("Enter Roll number");
            }
            else if (password.length() < 6){
                Password3.setError("Password must be greater or Equal to 6 characters");
                return;
            }
            else if (!password.equals(repassword)){
                ReEnterPassword3.setError("Entered Password Does not Match");
                return;

            }

            else{

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(New_user_signUp.this, "User created.", Toast.LENGTH_LONG).show();
                        userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = db.collection("users").document(userID);
                        Map<String,Object> user = new HashMap<>();
                        user.put("Name3",name);
                        user.put("Email3",email);
                        user.put("PhoneNumber3",phoneNo);
                        user.put("Division3",Div);
                        user.put("Rollno3",RollNo);
                        user.put("userType","student");
                        documentReference.set(user).addOnSuccessListener(aVoid -> Log.d(TAG1,"onSuccess: user profile is created for"  + userID));
                        Intent intent =new Intent(New_user_signUp.this,Login_Page.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(New_user_signUp.this, "ERROR!!! " +task.getException(), Toast.LENGTH_LONG).show();
                    }
                });
            }







        });


    }

    public void checkButton(View v){
        int radio =radiogroup.getCheckedRadioButtonId();
        SubmitButton3=findViewById(radio);

    }


}