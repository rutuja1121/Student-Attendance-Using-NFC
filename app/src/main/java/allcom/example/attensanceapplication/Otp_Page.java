package allcom.example.attensanceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Otp_Page extends AppCompatActivity {
    TextView ResendOtp6,EnterOtp6;
    EditText Otp6;
    Button SubmitButton6;
    String nuber;
    String otpid;
    FirebaseAuth mAuth;


    @SuppressWarnings("unchecked")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp__page);
        ResendOtp6=findViewById(R.id.ResendOtp6);
        EnterOtp6=findViewById(R.id.EnterOtp6);
        Otp6=findViewById(R.id.Otp6);
        SubmitButton6=findViewById(R.id.SubmitButton6);

        nuber=getIntent().getStringExtra("mobile").toString();
        mAuth= FirebaseAuth.getInstance();

        getOtp();


        SubmitButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Otp6.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "blank", Toast.LENGTH_SHORT).show();
                }else if (Otp6.getText().toString().length()!=6){
                    Toast.makeText(getApplicationContext(), "invalid otp", Toast.LENGTH_SHORT).show();
                }else
                {
                    PhoneAuthCredential credential=PhoneAuthProvider.getCredential(otpid,Otp6.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }



            }
        });

    }
    private void getOtp(){


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                nuber,
                60,
                TimeUnit.SECONDS,

               this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                {
                    @Override
                    public void onCodeSent( String s,  PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        otpid=s;
                    }

                    @Override
                    public void onVerificationCompleted( PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed( FirebaseException e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

        );




    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {
       mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>()
                        {

                            @Override
                            public void onComplete( Task<AuthResult> task)
                            {
                                if (task.isSuccessful())
                                {

                                    startActivity(new Intent(Otp_Page.this,Login_Page.class));
                                    finish();

                                } else {

                                    Toast.makeText(getApplicationContext(),"again enter phone no",Toast.LENGTH_LONG).show();
                                 }
                            }
                        }

                );}

}
