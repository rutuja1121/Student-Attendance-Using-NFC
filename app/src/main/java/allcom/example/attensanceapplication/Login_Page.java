package allcom.example.attensanceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Login_Page extends AppCompatActivity {
    Button LoginTwo;
    TextView Or, SignUpTwo, ForgotpasswordTwo;
    EditText EmailTwo, PasswordTwo;
    ImageView Logo;
    RadioGroup radio;
    private FirebaseAuth mAuth;

    @SuppressWarnings("unchecked")

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);

        LoginTwo = findViewById(R.id.LoginTwo);
        Or = findViewById(R.id.Or);
        SignUpTwo = findViewById(R.id.SignUpTwo);
        ForgotpasswordTwo = findViewById(R.id.ForgotpasswordTwo);
        EmailTwo = findViewById(R.id.EmailTwo);
        PasswordTwo = findViewById(R.id.PasswordTwo);
        Logo = findViewById(R.id.Logo);
        radio = findViewById(R.id.radio);

        mAuth = FirebaseAuth.getInstance();


        LoginTwo.setOnClickListener(view -> {
            String email = EmailTwo.getText().toString().trim();
            String password = PasswordTwo.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                EmailTwo.setError("Email is Required.");
                return;
            }

            else if (TextUtils.isEmpty(password)) {
                PasswordTwo.setError("Password is required");
                return;
            }

            else{
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login_Page.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                        DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        df.get().addOnSuccessListener(documentSnapshot -> {
                            if(documentSnapshot.getString("userType").equals("teacher")){
                                startActivity(new Intent(Login_Page.this,Select_year_teacher.class));
                                finish();
                            }
                            else if(documentSnapshot.getString("userType").equals("student")){
                                startActivity(new Intent(Login_Page.this,Subject_selet_student.class));
                                finish();
                            }

                            else if(documentSnapshot.getString("userType").equals("admin")){
                                startActivity(new Intent(Login_Page.this,Admin.class));
                                finish();
                            }
                            else{
                                Toast.makeText(Login_Page.this, " Error! ", Toast.LENGTH_SHORT);
                            }


                        });


                    } else {
                        Toast.makeText(Login_Page.this, " Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT);
                    }
                });
            }



        });

        SignUpTwo.setOnClickListener(view -> {
            Intent intent = new Intent(Login_Page.this, GetOtp.class);
            startActivity(intent);


        });
        ForgotpasswordTwo.setOnClickListener(view -> {

            final EditText resetMail = new EditText(view.getContext());
            AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
            passwordResetDialog.setTitle("Reset Password");
            passwordResetDialog.setMessage("Enter your Email to Recieved Reset Link.");
            passwordResetDialog.setView(resetMail);

            passwordResetDialog.setPositiveButton("Yes", (dialogInterface, i) -> {
                //extract the email and send reset link

                String mail = resetMail.getText().toString();
                mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(aVoid -> Toast.makeText(Login_Page.this, "Reset Link Sent To your Email", Toast.LENGTH_SHORT).show()).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login_Page.this, "Error ! Reset Link is not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            });

            passwordResetDialog.setNegativeButton("No", (dialogInterface, i) -> {
                //Close the dialog
            });

            passwordResetDialog.create().show();
        });

    }



    @Override
    protected void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            df.get().addOnSuccessListener(documentSnapshot -> {
                if(documentSnapshot.getString("userType").equals("teacher")){
                    startActivity(new Intent(Login_Page.this,Select_year_teacher.class));
                    finish();
                }
                else if(documentSnapshot.getString("userType").equals("student")){
                    startActivity(new Intent(Login_Page.this,Subject_selet_student.class));
                    finish();
                }

                else if(documentSnapshot.getString("userType").equals("admin")){
                    startActivity(new Intent(Login_Page.this,Admin.class));
                    finish();
                }
                else{
                    Toast.makeText(Login_Page.this, " Error! ", Toast.LENGTH_SHORT);
                }


            });


        }
    }
}