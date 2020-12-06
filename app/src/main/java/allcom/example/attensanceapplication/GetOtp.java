package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;


public class GetOtp extends AppCompatActivity {
TextView verify,verifyOtp6,number91;
EditText nuber;
CountryCodePicker ccp;
Button SubmitButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_otp);


        verify=findViewById(R.id.verify);
        verifyOtp6=findViewById(R.id.verifyOtp6);
        ccp=findViewById(R.id.ccp);
        nuber=findViewById(R.id.nuber);
        SubmitButton6=findViewById(R.id.SubmitButton6);

        ccp.registerCarrierNumberEditText(nuber);


        SubmitButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nuber.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(GetOtp.this,"Enter Mobile no",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent =new Intent(GetOtp.this,Otp_Page.class);
                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" "," "));
                startActivity(intent);
            }
        });
    }
}