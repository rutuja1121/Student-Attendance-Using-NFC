package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewUserAdmin extends AppCompatActivity {

    private static final String TAG1 = "TAG";
    EditText aname, Email3, PhoneNumber3, Password3, ReEnterPassword3;
    Button submitbutton;
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_admin);
        aname = findViewById(R.id.aname);
        Email3 = findViewById(R.id.editTextTextEmailAddress);
        PhoneNumber3 = findViewById(R.id.editTextPhone);
        Password3 = findViewById(R.id.editTextTextPassword);
        ReEnterPassword3 = findViewById(R.id.editTextTextPassword2);
        submitbutton = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();

        submitbutton.setOnClickListener(view -> {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("users");

            reference.setValue("");
            final String name = aname.getText().toString();
            final String email = Email3.getText().toString().trim();
            final String phoneNo = PhoneNumber3.getText().toString().trim();
            String password = Password3.getText().toString().trim();
            String repassword = ReEnterPassword3.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                aname.setError("Enter Name");
                return;
            } else if (TextUtils.isEmpty(email)) {
                Email3.setError("Enter Email");
                return;
            } else if (phoneNo.length() < 10) {
                PhoneNumber3.setError("Enter Valid Phone Number");
                return;
            } else if (phoneNo.length() > 10) {
                PhoneNumber3.setError("Enter Valid Phone Number");
                return;
            } else if (TextUtils.isEmpty(password)) {
                Password3.setError("Enter Password");
                return;
            } else if (password.length() < 6) {
                Password3.setError("Password must be greater or Equal to 6 characters");
                return;
            } else if (!password.equals(repassword)) {
                ReEnterPassword3.setError("Entered Password Does not Match");
                return;

            } else {

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(NewUserAdmin.this, "User created.", Toast.LENGTH_LONG).show();
                        String userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = db.collection("users").document(userID);
                        Map<String, Object> user = new HashMap<>();
                        user.put("aname", name);
                        user.put("Email3", email);
                        user.put("PhoneNumber3", phoneNo);
                        user.put("userType","admin");

                        documentReference.set(user).addOnSuccessListener(aVoid -> Log.d(TAG1, "onSuccess: user profile is created for" + userID));
                        Intent intent = new Intent(NewUserAdmin.this, Login_Page.class);
                        intent.putExtra("val",1);
                        startActivity(intent);
                    } else {
                        Toast.makeText(NewUserAdmin.this, "ERROR!!! " + task.getException(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}