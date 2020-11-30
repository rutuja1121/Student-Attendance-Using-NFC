package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Otp_Page extends AppCompatActivity {
    TextView ResendOtp6,EnterOtp6;
    EditText Otp6;
    Button SubmitButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp__page);
        ResendOtp6=findViewById(R.id.ResendOtp6);
        EnterOtp6=findViewById(R.id.EnterOtp6);
        Otp6=findViewById(R.id.Otp6);
        SubmitButton6=findViewById(R.id.SubmitButton6);
        SubmitButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Otp_Page.this,Login_Page.class);
                startActivity(intent);
            }
        });
    }
}