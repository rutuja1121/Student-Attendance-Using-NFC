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


        LoginTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EmailTwo.getText().toString().trim();
                String password = PasswordTwo.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    EmailTwo.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    PasswordTwo.setError("Password is required");
                    return;
                }

                if (password.length() < 6) {
                    PasswordTwo.setError("Password Must be >=6 Characters");
                    return;
                }


                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login_Page.this, "Logged in succesfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Select_year_teacher.class));
                        } else {
                            Toast.makeText(Login_Page.this, " Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT);
                        }
                    }
                });
            }
        });

        SignUpTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Page.this, New_user_signUp.class);
                startActivity(intent);


            }
        });
        ForgotpasswordTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter your Email to Recieved Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //extract the email and send reset link

                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login_Page.this, "Reset Link Sent To your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login_Page.this, "Error ! Reset Link is not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Close the dialog
                    }
                });

                passwordResetDialog.create().show();
            }
        });

    }



        @Override
    protected void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            DocumentReference df = FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.getString("isTeacher")!=null){
                        startActivity(new Intent(getApplicationContext(),Select_year_teacher.class));
                        finish();
                    }
                    if(documentSnapshot.getString("isStudent")!=null){
                        startActivity(new Intent(getApplicationContext(),Subject_selet_student.class));
                        finish();
                    }
                    if(documentSnapshot.getString("isAdmin")!=null){
                        startActivity(new Intent(getApplicationContext(),Admin.class));
                        finish();
                    }


                }
            });


        }
        }
    }

