package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class PhoneRegisterForOtp extends AppCompatActivity{
    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    EditText phoneNumber;
    Button nextBtn;
    CountryCodePicker codePicker;




    @Override
    protected <Register> void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_register_for_otp);

        fAuth = FirebaseAuth.getInstance();
        phoneNumber = findViewById(R.id.phone);
        nextBtn = findViewById(R.id.nextBtn);
        codePicker = findViewById(R.id.ccp);


        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                if(phoneNumber.getText().toString().isEmpty() && phoneNumber.getText().toString().length() == 10){
                    requestOTP(phoneNumber)

                }else {
                    phoneNumber.setError("Phone Number is not valid")
                }
            }

            private void requestOTP(EditText phoneNumber) {
                String phoneNum = "+"+codePicker.getSelectedCountryCode()+phoneNumber.getText().toString();
                Log.d(TAG, "Onclick: Phone NO->" + phoneNum);
                PhoneAuthProvider.getInstance().verifyPhoneNumber((phoneNum, 60L, TimeUnit.SECONDS, this
                        , new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);


                            }

                            @Override
                            public void onCodeAutoRetrievalTimeOut(String s) {
                                super.onCodeAutoRetrievalTimeOut(s);
                            }

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                            }


                        }));
            }
        });
    }
}